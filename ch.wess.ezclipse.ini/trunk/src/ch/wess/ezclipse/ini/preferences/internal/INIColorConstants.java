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

import org.eclipse.swt.graphics.RGB;

import ch.wess.ezclipse.core.Logger;
import ch.wess.ezclipse.ini.INIActivator;
import ch.wess.ezclipse.ini.nl1.Messages;

/**
 * Define which color to use for the different content types in the ini files.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class INIColorConstants {

    /**
     * Return the variable color.
     * 
     * @return
     */
    public static RGB getVariable() {
        return getColor(PreferenceConstants.P_INI_VARIABLE);
    }

    /**
     * Return the comment color.
     * 
     * @return
     */
    public static RGB getComment() {
        return getColor(PreferenceConstants.P_INI_COMMENT);
    }

    /**
     * Return the section color.
     * 
     * @return
     */
    public static RGB getSection() {
        return getColor(PreferenceConstants.P_INI_SECTION);
    }

    /**
     * Return the array color.
     * 
     * @return
     */
    public static RGB getArray() {
        return getColor(PreferenceConstants.P_INI_ARRAY);
    }

    /**
     * Return value color.
     * 
     * @return
     */
    public static RGB getValue() {
        return getColor(PreferenceConstants.P_INI_VALUE);
    }

    /**
     * Returns the color in RGB format from the preferences store
     * 
     * @param ID
     * @param defaultColor
     * @return The color in RGB format from the preferences store
     */
    private static RGB getColor(String ID) {
        String color = INIActivator.getDefault().getPreferenceStore()
                .getString(ID);
        String[] colors = color.split(","); //$NON-NLS-1$

        try {
            return new RGB(Integer.parseInt(colors[0]), Integer
                    .parseInt(colors[1]), Integer.parseInt(colors[2]));
        } catch (Exception e) {
            Logger
                    .logError(Messages.getString("INIColorConstants.0") + ID, e,  //$NON-NLS-1$
                            INIActivator.PLUGIN_ID);
        }
        return new RGB(0, 0, 0);
    }
}
