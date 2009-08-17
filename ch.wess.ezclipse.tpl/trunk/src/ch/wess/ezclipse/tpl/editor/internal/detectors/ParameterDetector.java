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
package ch.wess.ezclipse.tpl.editor.internal.detectors;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * Class who detect if the word is a parameter. A parameter starts with a letter 
 * (a-z or A-Z) and ends with the char '='.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class ParameterDetector implements IWordDetector {
    
    /**
     * Test if the char is part of the word we are searching.
     * 
     * @param currentChar
     * @return result
     */
    @Override
    public boolean isWordPart(char currentChar) {
        if ((currentChar >= 'a' && currentChar <= 'z')
                || (currentChar >= 'A' && currentChar <= 'Z')
                || (currentChar >= '0' && currentChar <= '9')
                || currentChar == '_' || currentChar == '.'
                || currentChar == '=' || currentChar == ':' 
                || currentChar == '-')
            return true;
        return false;
    }

    /**
     * Test if the char is at start of the word we are searching.
     * 
     * @param currentChar
     * @return result
     */
    @Override
    public boolean isWordStart(char currentChar) {
        if ((currentChar >= 'a' && currentChar <= 'z')
                || (currentChar >= 'A' && currentChar <= 'Z'))
            return true;
        return false;
    }
}
