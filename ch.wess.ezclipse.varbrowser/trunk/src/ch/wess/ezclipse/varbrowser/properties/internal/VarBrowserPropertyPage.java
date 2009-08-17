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
package ch.wess.ezclipse.varbrowser.properties.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;

import ch.wess.ezclipse.core.IFileProperties;
import ch.wess.ezclipse.core.Logger;
import ch.wess.ezclipse.varbrowser.VarBrowserActivator;
import ch.wess.ezclipse.varbrowser.nl1.Messages;

/**
 * This class render and manage the Var Browser property page.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class VarBrowserPropertyPage extends PropertyPage implements
        IWorkbenchPropertyPage {
    
    /**
     * Contains the selected file.
     */
    private IFile file;
    
    /**
     * Contains the text field for the node ID.
     */
    private Text textNodeId;

    /**
     * Create the GUI for the property page.
     * 
     * @param parent
     */
    @Override
    protected Control createContents(Composite parent) {
        IAdaptable element = getElement();
        file = (IFile) element.getAdapter(IFile.class);

        Group nodeGroup = new Group(parent, SWT.NULL);
        nodeGroup.setText(Messages.getString("VarBrowserPropertyPage.0")); //$NON-NLS-1$
        nodeGroup.setBounds(25, 25, 500, 100);

        // URL label
        final Label labelNodeId = new Label(nodeGroup, SWT.NULL);
        labelNodeId.setBounds(25, 25, 70, 20);
        labelNodeId.setText("Node ID :"); //$NON-NLS-1$

        // URL text field
        textNodeId = new Text(nodeGroup, SWT.SINGLE | SWT.LEFT);
        textNodeId.setBounds(125, 25, 50, 20);
        
        restoreValues();
        
        return null;
    }

    /**
     * This method is called when the user clicks on the "Apply" button.
     */
    @Override
    protected void performApply() {
        super.performApply();
        saveValues();
    }

    /**
     * This method is called when the user clicks on the "Ok" button.
     */
    @Override
    public boolean performOk() {
        saveValues();
        return super.performOk();
    }

    /**
     * Save the values in the persistent properties of the file.
     */
    private void saveValues() {
        try {
            file.setPersistentProperty(IFileProperties.NODE_ID,
                    textNodeId.getText());
        } catch (CoreException coreEx) {
            Logger.logError(coreEx, VarBrowserActivator.PLUGIN_ID);
        }
    }

    /**
     * Restore the values from the persistent properties of the file.s
     */
    private void restoreValues() {
        try {
            String nodeId = file.getPersistentProperty(IFileProperties.NODE_ID);
            if(nodeId != null)
                textNodeId.setText(nodeId);
            else
                textNodeId.setText(""); //$NON-NLS-1$
        } catch (CoreException coreEx) {
            textNodeId.setText(""); //$NON-NLS-1$
            Logger.logError(coreEx, VarBrowserActivator.PLUGIN_ID);
        }
    }
}
