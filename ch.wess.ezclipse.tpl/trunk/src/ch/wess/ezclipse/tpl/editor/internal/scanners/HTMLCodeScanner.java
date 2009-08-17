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
package ch.wess.ezclipse.tpl.editor.internal.scanners;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

import ch.wess.ezclipse.core.ColorManager;
import ch.wess.ezclipse.tpl.editor.internal.rules.HTMLParameterRule;
import ch.wess.ezclipse.tpl.preferences.internal.TPLColorConstants;

/**
 * Define more granular rules inside HTML code.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class HTMLCodeScanner extends RuleBasedScanner {

    /**
     * Constructor, define rules.
     * 
     * @param manager
     */
    public HTMLCodeScanner(ColorManager manager) {
        // Set default token.
        Color color = manager.getColor(TPLColorConstants.getHtmlTag());
        TextAttribute textAttribute = new TextAttribute(color);
        Token token = new Token(textAttribute);
        setDefaultReturnToken(token);
        
        color = manager.getColor(TPLColorConstants.getString());
        textAttribute = new TextAttribute(color);
        IToken string = new Token(textAttribute);
        
        color = manager.getColor(TPLColorConstants.getHtmlAttribute());
        textAttribute = new TextAttribute(color, null, SWT.ITALIC);
        IToken attribute = new Token(textAttribute);
        
        IRule[] rules = new IRule[3];

        // Add rule for double quotes
        rules[0] = new SingleLineRule("\"", "\"", string, '\\'); //$NON-NLS-1$ //$NON-NLS-2$
        // Add a rule for single quotes
        rules[1] = new SingleLineRule("'", "'", string, '\\'); //$NON-NLS-1$ //$NON-NLS-2$
        rules[2] = new HTMLParameterRule(attribute);
        
        setRules(rules);
    }

    /**
     * Method added for testing purpose.
     * @param attr
     * @return
     */
    public TextAttribute getAttr(Object attr) {
        if(attr instanceof TextAttribute)
            return (TextAttribute) attr;
        if(fDefaultReturnToken.getData() instanceof TextAttribute)
            return (TextAttribute) fDefaultReturnToken.getData();
        return null;
    }
}
