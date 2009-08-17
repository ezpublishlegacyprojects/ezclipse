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
package ch.wess.ezclipse.ini.wizards.internal;

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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

import ch.wess.ezclipse.ini.nl1.Messages;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (ini or ini.append.php).
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class NewINIWizardPage extends WizardPage {
    
    /**
     * Container text field. 
     */
    private Text containerText;

    /**
     * File text field.
     */
    private Text fileText;
    
    /**
     * Contains the ini extension file extension.
     */
    public static final String INI_EXTENSION_FILE = "ini.append.php"; //$NON-NLS-1$
    
    /**
     * Contains the ini file extension.
     */
    public static final String INI_FILE = "ini"; //$NON-NLS-1$
    
    /**
     * Contains the current extension that must be used for validation.
     */
    private String extension = INI_FILE;
    
    /**
     * Contains the current selected container.
     */
    private ISelection selection;
    
    /**
     * Get the extension attribute.
     * 
     * @return the current extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Set the extension attribute.
     * 
     * @param extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
        if (extension.equals(INI_FILE)) {
            fileText.setText(fileText.getText().substring(0,
                    fileText.getText().length() - INI_EXTENSION_FILE.length())
                    .concat(INI_FILE));
        } else if(extension.equals(INI_EXTENSION_FILE)) {
           fileText.setText(fileText.getText().substring(0,
                    fileText.getText().length() - INI_FILE.length())
                    .concat(INI_EXTENSION_FILE));
        }
    }

    /**
     * Constructor for SampleNewWizardPage.
     * 
     * @param pageName
     */
    public NewINIWizardPage(ISelection selection) {
        super(Messages.getString("NewINIWizardPage.0")); //$NON-NLS-1$
        setTitle(Messages.getString("NewINIWizardPage.1")); //$NON-NLS-1$
        setDescription(Messages.getString("NewINIWizardPage.2") //$NON-NLS-1$
                + Messages.getString("NewINIWizardPage.3") //$NON-NLS-1$
                + Messages.getString("NewINIWizardPage.4")); //$NON-NLS-1$
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
        label.setText(Messages.getString("NewINIWizardPage.5")); //$NON-NLS-1$

        containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        containerText.setLayoutData(gd);
        containerText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });

        Button button = new Button(container, SWT.PUSH);
        button.setText(Messages.getString("NewINIWizardPage.6")); //$NON-NLS-1$
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                handleBrowse();
            }
        });
        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("NewINIWizardPage.7")); //$NON-NLS-1$

        fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        fileText.setLayoutData(gd);
        fileText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });

        Button radioNew = new Button(container, SWT.RADIO);
        radioNew.setLayoutData(gd);
        radioNew.setText(Messages.getString("NewINIWizardPage.8")); //$NON-NLS-1$
        radioNew.setSelection(true);
        radioNew.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                if (!getExtension().equals(INI_FILE))
                    setExtension(INI_FILE);
            }

        });

        Button radioExisting = new Button(container, SWT.RADIO);
        radioExisting.setLayoutData(gd);
        radioExisting.setText(Messages.getString("NewINIWizardPage.9")); //$NON-NLS-1$
        radioExisting.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                if (!getExtension().equals(INI_EXTENSION_FILE))
                    setExtension(INI_EXTENSION_FILE);
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
        fileText.setText("new_file." + getExtension()); //$NON-NLS-1$
    }

    /**
     * Uses the standard container selection dialog to choose the new value for
     * the container field.
     */

    private void handleBrowse() {
        ContainerSelectionDialog dialog = new ContainerSelectionDialog(
                getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
                Messages.getString("NewINIWizardPage.10")); //$NON-NLS-1$
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
            updateStatus(Messages.getString("NewINIWizardPage.11")); //$NON-NLS-1$
            return;
        }
        if (container == null
                || (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
            updateStatus(Messages.getString("NewINIWizardPage.12")); //$NON-NLS-1$
            return;
        }
        if (!container.isAccessible()) {
            updateStatus(Messages.getString("NewINIWizardPage.13")); //$NON-NLS-1$
            return;
        }
        if (fileName.length() == 0) {
            updateStatus(Messages.getString("NewINIWizardPage.14")); //$NON-NLS-1$
            return;
        }
        if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
            updateStatus(Messages.getString("NewINIWizardPage.15")); //$NON-NLS-1$
            return;
        }
        
        /*int dotLoc = fileName.lastIndexOf('.');
        if (dotLoc != -1) {
            String ext = fileName.substring(dotLoc + 1);
            if (ext.equalsIgnoreCase(getExtension()) == false) {
                updateStatus("File extension must be " + getExtension());
                return;
            }
        }*/
        if (!fileName.endsWith(getExtension())) {
            updateStatus(Messages.getString("NewINIWizardPage.16") + getExtension()); //$NON-NLS-1$
            return;
        }
        updateStatus(null);
    }

    /**
     * Update the error message status.
     * 
     * @param message
     */
    private void updateStatus(String message) {
        setErrorMessage(message);
        setPageComplete(message == null);
    }

    /**
     * Get the content of the containerText text field.
     * 
     * @return the container name
     */
    public String getContainerName() {
        return containerText.getText();
    }
    
    /**
     * Get the content of the fileText text field.
     * 
     * @return the file name
     */
    public String getFileName() {
        return fileText.getText();
    }
}
