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
package ch.wess.ezclipse.ini.editor.internal.scanners;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

import ch.wess.ezclipse.core.ColorManager;
import ch.wess.ezclipse.ini.editor.internal.rules.INIArrayRule;
import ch.wess.ezclipse.ini.editor.internal.rules.INIVariableRule;
import ch.wess.ezclipse.ini.preferences.internal.INIColorConstants;

/**
 * Define more granular rules inside template code partition. 
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class INICodeScanner extends RuleBasedScanner {
    
    /**
     * Color manager.
     */
    private ColorManager manager;
    
    /**
     * Constructor, set the color manager.
     * @param manager
     */
    public INICodeScanner(ColorManager manager) {
        this.manager = manager;
        defineRules();
    }

    /**
     * Define the rules for the INI scanner.
     */
    private void defineRules() {     
        
        Color color = manager.getColor(INIColorConstants.getValue());
        TextAttribute textAttribute = new TextAttribute(color, null, SWT.ITALIC);
        Token token = new Token(textAttribute);
        setDefaultReturnToken(token);
        
        color = manager.getColor(INIColorConstants.getVariable());
        textAttribute = new TextAttribute(color);
        IToken variable = new Token(textAttribute);
        
        color = manager.getColor(INIColorConstants.getSection());
        textAttribute = new TextAttribute(color, null, SWT.BOLD);
        IToken section = new Token(textAttribute);
        
        color = manager.getColor(INIColorConstants.getArray());
        textAttribute = new TextAttribute(color);
        IToken array = new Token(textAttribute);
        
        IRule[] rules = new IRule[3];
        
        rules[0] = new SingleLineRule("[", "]", section); //$NON-NLS-1$ //$NON-NLS-2$
        rules[1] = new INIArrayRule(array);
        rules[2] = new INIVariableRule(variable);
        
        setRules(rules);
    }
    
    /**
     * Method added for testing purpose.
     * 
     * @param attr
     * @return
     */
    public TextAttribute getAttr(Object attr) {
        if (attr instanceof TextAttribute)
            return (TextAttribute) attr;
        if (fDefaultReturnToken.getData() instanceof TextAttribute)
            return (TextAttribute) fDefaultReturnToken.getData();
        return null;
    }
}
