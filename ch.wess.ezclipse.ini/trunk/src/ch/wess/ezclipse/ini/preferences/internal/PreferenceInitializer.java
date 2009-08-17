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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import ch.wess.ezclipse.ini.INIActivator;

/**
 * Class used to initialize default preference values.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
     * initializeDefaultPreferences()
     */
    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = INIActivator.getDefault().getPreferenceStore();
        store.setDefault(PreferenceConstants.P_INI_ARRAY, "174,48,48"); //$NON-NLS-1$
        store.setDefault(PreferenceConstants.P_INI_COMMENT, "191,191,191"); //$NON-NLS-1$
        store.setDefault(PreferenceConstants.P_INI_SECTION, "0,0,255"); //$NON-NLS-1$
        store.setDefault(PreferenceConstants.P_INI_VALUE, "30,144,255"); //$NON-NLS-1$
        store.setDefault(PreferenceConstants.P_INI_VARIABLE, "237,131,23"); //$NON-NLS-1$
    }
}
