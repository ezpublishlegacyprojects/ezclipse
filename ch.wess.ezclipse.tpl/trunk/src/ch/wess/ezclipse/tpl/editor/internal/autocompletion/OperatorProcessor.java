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
package ch.wess.ezclipse.tpl.editor.internal.autocompletion;

import java.util.ArrayList;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import ch.wess.ezclipse.tpl.TPLLanguageConstants;

/**
 * Compute the template file autocompletion
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class OperatorProcessor implements IContentAssistProcessor {

    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
            int offset) {
        String text = getText(viewer, offset);

        return getCompletionPropArray(text, offset);
    }

    private String getText(ITextViewer viewer, int offset) {
        IDocument doc = viewer.getDocument();
        String text = "";
        int counter = 1;
        boolean loop = true;
        while (loop) {
            try {
                text = doc.get(offset - counter, counter);
                if (text.charAt(0) == ' ' || text.charAt(0) == '{'
                        || text.charAt(0) == '=') {
                    loop = false;
                    text = text.substring(1);
                } else {
                    counter++;
                }
            } catch (BadLocationException e) {
                loop = false;
                try {
                    text = doc.get(offset + 1 - counter, -counter + 1);
                } catch (BadLocationException e1) {
                    text = "";
                }
            }
        }

        return text;
    }

    private ICompletionProposal[] getCompletionPropArray(String text, int offset) {
        ArrayList<ICompletionProposal> props = new ArrayList<ICompletionProposal>();
        ContextInformation conInfo = null;
        for (String[] operator : TPLLanguageConstants.OPERATOR_LIST) {
            if (operator[0].startsWith(text))
                conInfo = new ContextInformation("Some help 1 for "
                        + operator[0], "Some help 1 for " + operator[0]);
                props
                        .add(new CompletionProposal(operator[0], offset
                                - text.length(), text.length(), 1, 
                                null, operator[0], conInfo, operator[1]));
        }

        return props.toArray(new ICompletionProposal[props.size()]);
    }

    private IContextInformation[] getContextInfo(String text, int offset) {
        ArrayList<IContextInformation> contextInfo = new ArrayList<IContextInformation>();

        for (String[] operator : TPLLanguageConstants.OPERATOR_LIST) {
            if (operator[0].startsWith(text)) {
                contextInfo.add(new ContextInformation("Some help 1 for "
                        + operator[0], "Some help 1 for " + operator[0]));
            }
        }

        return contextInfo.toArray(new IContextInformation[contextInfo.size()]);
    }

    @Override
    public IContextInformation[] computeContextInformation(ITextViewer viewer,
            int offset) {
        //String text = getText(viewer, offset);

        //return getContextInfo(text, offset);
        return new IContextInformation[] {new ContextInformation("Test", "test2")};
    }

    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', '0', '-', '_', '{' };
    }

    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        /*return new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', '0', '-', '_', '{' };*/
        return new char[] {'#'};
    }

    @Override
    public IContextInformationValidator getContextInformationValidator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getErrorMessage() {
        // TODO Auto-generated method stub
        return null;
    }

}
