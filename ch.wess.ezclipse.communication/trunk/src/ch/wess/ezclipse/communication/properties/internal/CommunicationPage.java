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
package ch.wess.ezclipse.communication.properties.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

import ch.wess.ezclipse.communication.CommunicationActivator;
import ch.wess.ezclipse.communication.ISoapCommunication;
import ch.wess.ezclipse.communication.nl1.Messages;
import ch.wess.ezclipse.communication.properties.ICommunicationProperties;
import ch.wess.ezclipse.core.Logger;

/**
 * Class which render the form for the project properties. There's 3 attributes,
 * the soap url, login and password.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class CommunicationPage extends PropertyPage {

    /**
     * Text field that's contains the password.
     */
    private Text textPass;

    /**
     * Text field that's contains the login.
     */
    private Text textLogin;

    /**
     * Text field that's contains the soap url.
     */
    private Text textUrl;

    /**
     * Contains the current project.
     */
    private IProject project;

    /**
     * Create the GUI.
     * 
     * @param parent
     */
    @Override
    protected Control createContents(final Composite parent) {
        IAdaptable element = getElement();
        project = (IProject) element.getAdapter(IProject.class);

        Composite panel = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        panel.setLayout(layout);
        Group soapGroup = new Group(panel, SWT.NULL);
        soapGroup.setText(Messages.getString("CommunicationPage.0")); //$NON-NLS-1$
        soapGroup.setBounds(25, 25, 520, 300);

        // URL label
        final Label labelUrl = new Label(soapGroup, SWT.NULL);
        labelUrl.setBounds(25, 25, 70, 20);
        labelUrl.setText("URL :"); //$NON-NLS-1$

        // URL text field
        textUrl = new Text(soapGroup, SWT.SINGLE | SWT.LEFT);
        textUrl.setBounds(125, 25, 450, 20);

        // Login label
        final Label labelLogin = new Label(soapGroup, SWT.NULL);
        labelLogin.setBounds(25, 55, 100, 20);
        labelLogin.setText(Messages.getString("CommunicationPage.1")); //$NON-NLS-1$

        // Login text field
        textLogin = new Text(soapGroup, SWT.SINGLE | SWT.LEFT);
        textLogin.setBounds(125, 55, 250, 20);

        // Password label
        final Label labelPass = new Label(soapGroup, SWT.NULL);
        labelPass.setBounds(25, 85, 100, 20);
        labelPass.setText(Messages.getString("CommunicationPage.2")); //$NON-NLS-1$

        // Password text field
        textPass = new Text(soapGroup, SWT.SINGLE | SWT.LEFT | SWT.PASSWORD);
        textPass.setBounds(125, 85, 250, 20);

        final Button buttonCheck = new Button(soapGroup, SWT.CENTER);
        buttonCheck.setBounds(25, 115, 250, 30);
        buttonCheck.setText(Messages.getString("CommunicationPage.3")); //$NON-NLS-1$
        buttonCheck.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                ISoapCommunication ezComm = CommunicationActivator.getNewCommunication();
                boolean result = ezComm.testConnection(textUrl.getText(),
                        textLogin.getText(), textPass.getText());
                if (result)
                    MessageDialog
                            .openInformation(parent.getShell(),
                                    Messages.getString("CommunicationPage.4"), ezComm //$NON-NLS-1$
                                            .getErrorMessage());
                else
                    MessageDialog
                            .openError(parent.getShell(),
                                    Messages.getString("CommunicationPage.5"), ezComm //$NON-NLS-1$
                                            .getErrorMessage());
            }
        });

        restoreValues();
        return panel;
    }

    /**
     * Save the values when the user clicks on the "apply" button.
     */
    @Override
    protected void performApply() {
        saveValues();
        super.performApply();
    }

    /**
     * Restore defaults values.
     */
    @Override
    protected void performDefaults() {
        textUrl.setText(""); //$NON-NLS-1$
        textLogin.setText(""); //$NON-NLS-1$
        textPass.setText(""); //$NON-NLS-1$
        super.performDefaults();
    }

    /**
     * Save the values when the user clicks on the "Ok" button.
     */
    @Override
    public boolean performOk() {
        saveValues();
        return super.performOk();
    }

    /**
     * Save the values.
     */
    private void saveValues() {
        try {
            project.setPersistentProperty(ICommunicationProperties.SOAP_URL, textUrl
                    .getText());
            project.setPersistentProperty(ICommunicationProperties.SOAP_LOGIN, textLogin
                    .getText());
            project.setPersistentProperty(ICommunicationProperties.SOAP_PASSWORD, textPass
                    .getText());
        } catch (CoreException coreEx) {
            Logger.logError(coreEx,
                    CommunicationActivator.PLUGIN_ID);
        }
    }

    /**
     * Set the saved values
     */
    private void restoreValues() {
        try {
            String url = project.getPersistentProperty(ICommunicationProperties.SOAP_URL);
            
            if(url != null)
                textUrl.setText(url);
            else
                textUrl.setText(""); //$NON-NLS-1$
        } catch (CoreException coreEx) {
            textUrl.setText(""); //$NON-NLS-1$
        }

        try {
            String login = project.getPersistentProperty(ICommunicationProperties.SOAP_LOGIN);
            
            if(login != null)
                textLogin.setText(login);
            else
                textLogin.setText(""); //$NON-NLS-1$
        } catch (CoreException coreEx) {
            textLogin.setText(""); //$NON-NLS-1$
        }

        try {
            String pass = project.getPersistentProperty(ICommunicationProperties.SOAP_PASSWORD);
            
            if(pass != null)
                textPass.setText(pass);
            else
                textPass.setText(""); //$NON-NLS-1$
        } catch (CoreException coreEx) {
            textPass.setText(""); //$NON-NLS-1$
        }
    }
}
