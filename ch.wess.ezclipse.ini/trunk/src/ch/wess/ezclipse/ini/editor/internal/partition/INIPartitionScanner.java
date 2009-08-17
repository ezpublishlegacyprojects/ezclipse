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
package ch.wess.ezclipse.ini.editor.internal.partition;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

/**
 * Scans the document and made partition.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class INIPartitionScanner extends RuleBasedPartitionScanner {
    
    /**
     * INI file comment, looks like this : #comment
     */
    public static final String INI_COMMENT = "__ini_comment"; //$NON-NLS-1$
    
    /**
     * List of all partition types
     */
    public static final String[] PARTITION_TYPES = {
            IDocument.DEFAULT_CONTENT_TYPE, INI_COMMENT };
    
    /**
     * Constructor, set the rules for INI file partitioning.
     */
    public INIPartitionScanner() {
        IToken iniComment = new Token(INI_COMMENT);
        
        IPredicateRule[] rules = new IPredicateRule[1];
        
        rules[0] = new SingleLineRule("#", " ", iniComment, ' ', true); //$NON-NLS-1$ //$NON-NLS-2$

        setPredicateRules(rules);
    }
}
