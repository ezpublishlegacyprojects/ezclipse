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

import org.eclipse.swt.graphics.RGB;

import ch.wess.ezclipse.core.Logger;
import ch.wess.ezclipse.tpl.TPLActivator;
import ch.wess.ezclipse.tpl.nl1.Messages;

/**
 * Define which color to use for the different content types.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class TPLColorConstants {

    /**
     * Return the html tag color.
     * 
     * @return the tag color
     */
    public static RGB getHtmlTag() {
        return getColor(PreferenceConstants.P_HTML_TAG_COLOR);
    }

    /**
     * Return the html attribute color.
     * 
     * @return the HTML attribute color
     */
    public static RGB getHtmlAttribute() {
        return getColor(PreferenceConstants.P_HTML_ATTRIBUTE_COLOR);
    }

    /**
     * Return the parameter color.
     * 
     * @return the parameter color
     */
    public static RGB getParameter() {
        return getColor(PreferenceConstants.P_PARAMETER_COLOR);
    }

    /**
     * Return the comment color.
     * 
     * @return the comment color
     */
    public static RGB getComment() {
        return getColor(PreferenceConstants.P_COMMENT);
    }

    /**
     * Return the variable color.
     * 
     * @return the variable color
     */
    public static RGB getVariable() {
        return getColor(PreferenceConstants.P_VARIABLE_COLOR);
    }

    /**
     * Return the string color.
     * 
     * @return
     */
    public static RGB getString() {
        return getColor(PreferenceConstants.P_STRING);
    }

    /**
     * Return the operator color.
     * 
     * @return the operator color
     */
    public static RGB getOperator() {
        return getColor(PreferenceConstants.P_OPERATOR);
    }

    /**
     * Return the default color.
     * 
     * @return the default color
     */
    public static RGB getDefault() {
        return getColor(PreferenceConstants.P_DEFAULT_COLOR);
    }

    /**
     * Returns the color in RGB format from the preferences store
     * 
     * @param ID
     * @param defaultColor
     * @return The color in RGB format from the preferences store
     */
    private static RGB getColor(String ID) {
        try {
            String color = TPLActivator.getDefault().getPreferenceStore()
            .getString(ID);
            
            String[] colors = color.split(","); //$NON-NLS-1$
            return new RGB(Integer.parseInt(colors[0]), Integer
                    .parseInt(colors[1]), Integer.parseInt(colors[2]));
        } catch (Exception e) {
            Logger.logError(Messages.getString("TPLColorConstants.0") + ID, e, //$NON-NLS-1$
                    TPLActivator.PLUGIN_ID);
        }
        return new RGB(0, 0, 0);
    }
}
