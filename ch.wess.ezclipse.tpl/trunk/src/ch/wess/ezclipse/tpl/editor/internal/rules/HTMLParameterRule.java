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
package ch.wess.ezclipse.tpl.editor.internal.rules;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WordPatternRule;

import ch.wess.ezclipse.tpl.editor.internal.detectors.ParameterDetector;

/**
 * Rule which define if a word is a parameter or not.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class HTMLParameterRule extends WordPatternRule {
    
    /**
     * Constructor, define the kind of word to find
     * @param token
     */
    public HTMLParameterRule(IToken token) {
        super(new ParameterDetector(), " ", "=", token); //$NON-NLS-1$ //$NON-NLS-2$
    }
}
