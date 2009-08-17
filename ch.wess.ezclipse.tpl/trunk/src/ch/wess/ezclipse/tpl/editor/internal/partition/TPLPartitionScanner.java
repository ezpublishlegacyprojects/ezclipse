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
package ch.wess.ezclipse.tpl.editor.internal.partition;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

/**
 * Define how to partition the document.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class TPLPartitionScanner extends RuleBasedPartitionScanner {

    /**
     * Template comment, something like this : {* Comment *}
     */
    public final static String TPL_COMMENT = "__tpl_comment"; //$NON-NLS-1$

    /**
     * Template operator, something like this : {operator parm=value}
     */
    public final static String TPL_CODE = "__tpl_code"; //$NON-NLS-1$

    /**
     * HTML comment <!-- comment -->.
     */
    public final static String HTML_COMMENT = "__html_comment"; //$NON-NLS-1$

    /**
     * List of all partition types
     */
    public static final String[] PARTITION_TYPES = {
            IDocument.DEFAULT_CONTENT_TYPE, TPL_COMMENT, TPL_CODE, HTML_COMMENT };

    /**
     * Sets the rules to partition the document.
     */
    public TPLPartitionScanner() {
        IToken tplComment = new Token(TPL_COMMENT);
        IToken tplCode = new Token(TPL_CODE);
        IToken htmlComment = new Token(HTML_COMMENT);

        IPredicateRule[] rules = new IPredicateRule[3];

        rules[0] = new MultiLineRule("{*", "*}", tplComment); //$NON-NLS-1$ //$NON-NLS-2$
        rules[1] = new MultiLineRule("{", "}", tplCode); //$NON-NLS-1$ //$NON-NLS-2$
        rules[2] = new MultiLineRule("<!--", "-->", htmlComment); //$NON-NLS-1$ //$NON-NLS-2$

        setPredicateRules(rules);
    }
}
