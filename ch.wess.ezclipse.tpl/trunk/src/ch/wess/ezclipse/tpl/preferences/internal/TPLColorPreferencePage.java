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
package ch.wess.ezclipse.tpl.preferences.internal;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import ch.wess.ezclipse.tpl.TPLActivator;
import ch.wess.ezclipse.tpl.nl1.Messages;

/**
 * Preference page for the syntax highlighting properties.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class TPLColorPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

    /**
     * Constructor, set the grid and description.
     */
    public TPLColorPreferencePage() {
        super(GRID);
        setPreferenceStore(TPLActivator.getDefault().getPreferenceStore());
        setDescription(Messages.getString("TPLColorPreferencePage.0") + //$NON-NLS-1$
                Messages.getString("TPLColorPreferencePage.1")); //$NON-NLS-1$
    }

    /**
     * Create the interface for choosing colors.
     */
    @Override
    protected void createFieldEditors() {
        addField(new ColorFieldEditor(PreferenceConstants.P_DEFAULT_COLOR, Messages.getString("TPLColorPreferencePage.2"), getFieldEditorParent())); //$NON-NLS-1$
        addField(new ColorFieldEditor(PreferenceConstants.P_OPERATOR, Messages.getString("TPLColorPreferencePage.3"), getFieldEditorParent())); //$NON-NLS-1$
        addField(new ColorFieldEditor(PreferenceConstants.P_VARIABLE_COLOR, Messages.getString("TPLColorPreferencePage.4"), getFieldEditorParent())); //$NON-NLS-1$
        addField(new ColorFieldEditor(PreferenceConstants.P_PARAMETER_COLOR, Messages.getString("TPLColorPreferencePage.5"), getFieldEditorParent())); //$NON-NLS-1$
        addField(new ColorFieldEditor(PreferenceConstants.P_STRING, Messages.getString("TPLColorPreferencePage.6"), getFieldEditorParent())); //$NON-NLS-1$
        addField(new ColorFieldEditor(PreferenceConstants.P_HTML_TAG_COLOR, Messages.getString("TPLColorPreferencePage.7"), getFieldEditorParent())); //$NON-NLS-1$
        addField(new ColorFieldEditor(PreferenceConstants.P_HTML_ATTRIBUTE_COLOR, Messages.getString("TPLColorPreferencePage.8"), getFieldEditorParent())); //$NON-NLS-1$
        addField(new ColorFieldEditor(PreferenceConstants.P_COMMENT, Messages.getString("TPLColorPreferencePage.9"), getFieldEditorParent())); //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    @Override
    public void init(IWorkbench arg0) {
        
    }

}
