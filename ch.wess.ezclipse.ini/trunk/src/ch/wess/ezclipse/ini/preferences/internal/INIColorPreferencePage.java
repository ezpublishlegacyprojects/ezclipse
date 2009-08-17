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
package ch.wess.ezclipse.ini.preferences.internal;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import ch.wess.ezclipse.ini.INIActivator;
import ch.wess.ezclipse.ini.nl1.Messages;

/**
 * Preference page for the syntax highlighting properties.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class INIColorPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

    /**
     * Constructor, set the grid and description.
     */
    public INIColorPreferencePage() {
        super(GRID);
        setPreferenceStore(INIActivator.getDefault().getPreferenceStore());
        setDescription(Messages.getString("INIColorPreferencePage.0") + //$NON-NLS-1$
                Messages.getString("INIColorPreferencePage.1")); //$NON-NLS-1$
    }

    /**
     * Create the interface for choosing colors.
     */
    @Override
    protected void createFieldEditors() {
        addField(new ColorFieldEditor(PreferenceConstants.P_INI_ARRAY, Messages.getString("INIColorPreferencePage.2"), getFieldEditorParent())); //$NON-NLS-1$
        addField(new ColorFieldEditor(PreferenceConstants.P_INI_COMMENT, Messages.getString("INIColorPreferencePage.3"), getFieldEditorParent())); //$NON-NLS-1$
        addField(new ColorFieldEditor(PreferenceConstants.P_INI_SECTION, Messages.getString("INIColorPreferencePage.4"), getFieldEditorParent())); //$NON-NLS-1$
        addField(new ColorFieldEditor(PreferenceConstants.P_INI_VALUE, Messages.getString("INIColorPreferencePage.5"), getFieldEditorParent())); //$NON-NLS-1$
        addField(new ColorFieldEditor(PreferenceConstants.P_INI_VARIABLE, Messages.getString("INIColorPreferencePage.6"), getFieldEditorParent())); //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    @Override
    public void init(IWorkbench arg0) {
        
    }

}
