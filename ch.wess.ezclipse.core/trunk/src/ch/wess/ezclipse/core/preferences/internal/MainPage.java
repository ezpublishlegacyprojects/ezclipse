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
package ch.wess.ezclipse.core.preferences.internal;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import ch.wess.ezclipse.core.CoreActivator;
import ch.wess.ezclipse.core.nl1.Messages;

/**
 * Parent preference page, this page will present the eZclipse product.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class MainPage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

    /**
     * Constructor, set the grid and description.
     */
    public MainPage() {
        super(GRID);
        setPreferenceStore(CoreActivator.getDefault().getPreferenceStore());
        setDescription(Messages.getString("MainPage.0")); //$NON-NLS-1$
    }

    /**
     * Create the preference fields.
     */
    @Override
    public void createFieldEditors() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
    }

}
