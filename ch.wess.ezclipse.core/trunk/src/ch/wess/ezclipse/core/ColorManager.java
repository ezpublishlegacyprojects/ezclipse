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
package ch.wess.ezclipse.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * This class provides colors for syntax highlighting.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class ColorManager {
    
    protected Map<RGB, Color> fColorTable = new HashMap<RGB, Color>(10);

    /**
     * Dispose color manager.
     */
    public void dispose() {
        Iterator<Color> e = fColorTable.values().iterator();
        while (e.hasNext())
             e.next().dispose();
    }
    
    /**
     * Return a Color defined in an RBG format.
     * 
     * @param rgb
     * @return Color defined in an RBG format.
     */
    public Color getColor(RGB rgb) {
        Color color = fColorTable.get(rgb);
        if (color == null) {
            color = new Color(Display.getCurrent(), rgb);
            fColorTable.put(rgb, color);
        }
        return color;
    }
}
