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
package ch.wess.ezclipse.ini.editor.internal.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

/**
 * Rule class for detecting INI variables. A INI variable looks like this :
 * variable=
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class INIVariableRule implements IRule {
    
    /**
     * The token to return if the rule match.
     */
    private IToken token = null;
    
    /**
     * Constructor, set the token.
     * 
     * @param token
     */
    public INIVariableRule(IToken token) {
        this.token = token;
    }
    
    /**
     * Evaluate the characters to detect if it matches the variable pattern.
     */
    @Override
    public IToken evaluate(ICharacterScanner charScanner) {
        
        int c = charScanner.read();
        int i = 1;
        while(c != ICharacterScanner.EOF && isWordPart((char)c)) {
            if(c == '=' && i > 1)
                return token;
            c = charScanner.read();
            i++;
        }
        
        while(i > 0) {
            charScanner.unread();
            i--;
        }
        
        return Token.UNDEFINED;
    }
    
    /**
     * Test if the char is part of the word.
     * 
     * @param c
     * @return
     */
    private boolean isWordPart(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '=' || 
                c == '-' || c == '_' || ( c >= '0' && c <= '9'))
            return true;
        return false;
    }
}
