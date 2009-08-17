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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

import ch.wess.ezclipse.tpl.nl1.Messages;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (tpl).
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */

public class NewTemplateWizardPage extends WizardPage {
    
    /**
     * The container text field.
     */
    private Text containerText;
    
    /**
     * The file text field.
     */
    private Text fileText;
    
    /**
     * The node ID text field.
     */
    private Text nodeId;
    
    /**
     * The currenct selected container.
     */
    private ISelection selection;

    /**
     * Constructor for SampleNewWizardPage.
     * 
     * @param pageName
     */
    public NewTemplateWizardPage(ISelection selection) {
        super(Messages.getString("NewTemplateWizardPage.0")); //$NON-NLS-1$
        setTitle(Messages.getString("NewTemplateWizardPage.1")); //$NON-NLS-1$
        setDescription(Messages.getString("NewTemplateWizardPage.2") + //$NON-NLS-1$
        		Messages.getString("NewTemplateWizardPage.3") + //$NON-NLS-1$
        		Messages.getString("NewTemplateWizardPage.4")); //$NON-NLS-1$
        this.selection = selection;
    }

    /**
     * @see IDialogPage#createControl(Composite)
     */
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 3;
        layout.verticalSpacing = 9;
        Label label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("NewTemplateWizardPage.5")); //$NON-NLS-1$

        containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        containerText.setLayoutData(gd);
        containerText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });

        Button button = new Button(container, SWT.PUSH);
        button.setText(Messages.getString("NewTemplateWizardPage.6")); //$NON-NLS-1$
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                handleBrowse();
            }
        });
        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("NewTemplateWizardPage.7")); //$NON-NLS-1$

        fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        fileText.setLayoutData(gd);
        fileText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });
        
        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("NewTemplateWizardPage.8")); //$NON-NLS-1$
        
        nodeId = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        nodeId.setLayoutData(gd);
        nodeId.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                nodeIdChanged();
            }
        });
        
        initialize();
        dialogChanged();
        setControl(container);
    }

    /**
     * Tests if the current workbench selection is a suitable container to use.
     */
    private void initialize() {
        if (selection != null && selection.isEmpty() == false
                && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection) selection;
            if (ssel.size() > 1)
                return;
            Object obj = ssel.getFirstElement();
            if (obj instanceof IResource) {
                IContainer container;
                if (obj instanceof IContainer)
                    container = (IContainer) obj;
                else
                    container = ((IResource) obj).getParent();
                containerText.setText(container.getFullPath().toString());
            }
        }
        fileText.setText("new_file.tpl"); //$NON-NLS-1$
    }

    /**
     * Uses the standard container selection dialog to choose the new value for
     * the container field.
     */
    private void handleBrowse() {
        ContainerSelectionDialog dialog = new ContainerSelectionDialog(
                getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
                Messages.getString("NewTemplateWizardPage.9")); //$NON-NLS-1$
        if (dialog.open() == Window.OK) {
            Object[] result = dialog.getResult();
            if (result.length == 1) {
                containerText.setText(((Path) result[0]).toString());
            }
        }
    }

    /**
     * Ensures that both text fields are set.
     */
    private void dialogChanged() {
        IResource container = ResourcesPlugin.getWorkspace().getRoot()
                .findMember(new Path(getContainerName()));
        String fileName = getFileName();

        if (getContainerName().length() == 0) {
            updateStatus(Messages.getString("NewTemplateWizardPage.10")); //$NON-NLS-1$
            return;
        }
        if (container == null
                || (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
            updateStatus(Messages.getString("NewTemplateWizardPage.11")); //$NON-NLS-1$
            return;
        }
        if (!container.isAccessible()) {
            updateStatus(Messages.getString("NewTemplateWizardPage.12")); //$NON-NLS-1$
            return;
        }
        if (fileName.length() == 0) {
            updateStatus(Messages.getString("NewTemplateWizardPage.13")); //$NON-NLS-1$
            return;
        }
        if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
            updateStatus(Messages.getString("NewTemplateWizardPage.14")); //$NON-NLS-1$
            return;
        }
        int dotLoc = fileName.lastIndexOf('.');
        if (dotLoc != -1) {
            String ext = fileName.substring(dotLoc + 1);
            if (ext.equalsIgnoreCase(Messages.getString("NewTemplateWizardPage.15")) == false) { //$NON-NLS-1$
                updateStatus(Messages.getString("NewTemplateWizardPage.16")); //$NON-NLS-1$
                return;
            }
        }
        updateStatus(null);
    }
    
    /**
     * Test if the new node ID is a valid string.
     */
    private void nodeIdChanged() {
        String node = getNodeId();
        try {
            Integer.parseInt(node);
            updateStatus(null);
        } catch (NumberFormatException ex) {
            updateStatus(Messages.getString("NewTemplateWizardPage.17")); //$NON-NLS-1$
        }
    }
    
    /**
     * Update the error message.
     * 
     * @param message
     */
    private void updateStatus(String message) {
        setErrorMessage(message);
        setPageComplete(message == null);
    }
    
    /**
     * Get the content of the container text field.
     * 
     * @return the name of the container
     */
    public String getContainerName() {
        return containerText.getText();
    }
    
    /**
     * The content of the node ID text field.
     * 
     * @return the node id
     */
    public String getNodeId() {
        return nodeId.getText();
    }
    
    /**
     * The content of the file text field.
     * 
     * @return the file name
     */
    public String getFileName() {
        return fileText.getText();
    }
}
