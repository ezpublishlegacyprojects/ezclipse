/*
 * WESS eZ Publish Eclipse plug-in
 * Copyright (C) 2009  Web Engineering Sahli & Stalder (http://www.wess.ch)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.wess.ezclipse.tpl.wizards.internal;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import ch.wess.ezclipse.core.IFileProperties;
import ch.wess.ezclipse.tpl.nl1.Messages;

/**
 * Manage the new template wizard.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class NewTemplateWizard extends Wizard implements INewWizard {

    /**
     * Contains the first page of the wizard.
     */
    private NewTemplateWizardPage page;
    
    /**
     * Contains the currenct container selection.
     */
    private ISelection selection;
    
    /**
     * Constructor, initialize the wizard.
     */
    public NewTemplateWizard() {
        super();
        setNeedsProgressMonitor(true);
    }

    /**
     * Adding the page to the wizard.
     */
    @Override
    public void addPages() {
        page = new NewTemplateWizardPage(selection);
        addPage(page);
    }

    /**
     * When the wizard is finished, the file is create with the specified
     * persistent properties.
     */
    @Override
    public boolean performFinish() {
        final String containerName = page.getContainerName();
        final String fileName = page.getFileName();
        final String nodeId = page.getNodeId();
        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(IProgressMonitor monitor)
                    throws InvocationTargetException {
                try {
                    doFinish(containerName, fileName, nodeId, monitor);
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                } finally {
                    monitor.done();
                }
            }
        };
        try {
            getContainer().run(true, false, op);
        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            Throwable realException = e.getTargetException();
            MessageDialog.openError(getShell(), Messages.getString("NewTemplateWizard.0"), realException //$NON-NLS-1$
                    .getMessage());
            return false;
        }
        return true;
    }

    /**
     * The worker method. It will find the container, create the file if missing
     * or just replace its contents, set the persistent properties and open the
     * editor on the newly created file.
     * 
     * @param fileName
     * @param nodeId
     * @param monitor
     */
    private void doFinish(String containerName, String fileName, String nodeId,
            IProgressMonitor monitor) throws CoreException {
        // create a sample file
        monitor.beginTask(Messages.getString("NewTemplateWizard.1") + fileName, 2); //$NON-NLS-1$
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(new Path(containerName));
        if (!resource.exists() || !(resource instanceof IContainer)) {
            throwCoreException(Messages.getString("NewTemplateWizard.2") + containerName //$NON-NLS-1$
                    + Messages.getString("NewTemplateWizard.3")); //$NON-NLS-1$
        }
        IContainer container = (IContainer) resource;
        final IFile file = container.getFile(new Path(fileName));
        try {
            InputStream stream = openContentStream(file);
            if (file.exists()) {
                file.setContents(stream, true, true, monitor);
            } else {
                file.create(stream, true, monitor);
            }
            stream.close();
            // Add the node id to the file properties.
            file.setPersistentProperty(IFileProperties.NODE_ID, nodeId);
        } catch (IOException e) {
        }
        monitor.worked(1);
        monitor.setTaskName(Messages.getString("NewTemplateWizard.4")); //$NON-NLS-1$
        getShell().getDisplay().asyncExec(new Runnable() {
            public void run() {
                IWorkbenchPage page = PlatformUI.getWorkbench()
                        .getActiveWorkbenchWindow().getActivePage();
                try {
                    IDE.openEditor(page, file, true);
                } catch (PartInitException e) {
                }
            }
        });
        monitor.worked(1);
    }

    /**
     * We will initialize file contents with a sample text.
     * 
     * @param file
     */
    private InputStream openContentStream(IFile file) {
        String charset = ""; //$NON-NLS-1$
        try {
            charset = file.getCharset();
        } catch(CoreException coreEx) {
            charset = "utf-8"; //$NON-NLS-1$
        }
        String contents = "{*?template charset=" + charset + "*}"; //$NON-NLS-1$ //$NON-NLS-2$
        return new ByteArrayInputStream(contents.getBytes());
    }
    
    /**
     * Throw a core exception an set the corresponding Status.
     * 
     * @param message
     * @throws CoreException
     */
    private void throwCoreException(String message) throws CoreException {
        IStatus status = new Status(IStatus.ERROR,
                "ch.wess.ezclipse.tpl", IStatus.OK, message, null); //$NON-NLS-1$
        throw new CoreException(status);
    }

    /**
     * We will accept the selection in the workbench to see if we can initialize
     * from it.
     * 
     * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.selection = selection;
    }

}
