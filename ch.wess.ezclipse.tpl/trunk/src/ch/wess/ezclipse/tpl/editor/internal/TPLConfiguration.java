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
package ch.wess.ezclipse.tpl.editor.internal;

import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import ch.wess.ezclipse.core.ColorManager;
import ch.wess.ezclipse.tpl.editor.internal.autocompletion.OperatorProcessor;
import ch.wess.ezclipse.tpl.editor.internal.partition.TPLPartitionScanner;
import ch.wess.ezclipse.tpl.editor.internal.scanners.HTMLCodeScanner;
import ch.wess.ezclipse.tpl.editor.internal.scanners.TPLCodeScanner;
import ch.wess.ezclipse.tpl.preferences.internal.TPLColorConstants;

/**
 * Main class to configure the TPL editor's features.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class TPLConfiguration extends SourceViewerConfiguration {

    /**
     * Contains the scanner for the TPL operators.
     */
    private TPLCodeScanner operatorScanner = null;

    /**
     * Contains the scanner for HTML code.
     */
    private HTMLCodeScanner htmlScanner = null;

    /**
     * Contains the color manager.
     */
    private ColorManager colorManager = null;

    public TPLConfiguration(ColorManager manager) {
        this.colorManager = manager;
    }

    /**
     * Returns the configured content types which are defined in the
     * TPLPartitionerScanner class.
     * 
     * @see ch.wess.ezclipse.tpl.editor.internal.partition.editors.internal.partition.TPLPartitionScanner#PARTITION_TYPES
     * @param sourceViewer
     */
    @Override
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return TPLPartitionScanner.PARTITION_TYPES;
    }

    /**
     * Method used for syntax highlighting.
     * 
     * @param sourceViewer
     */
    @Override
    public IPresentationReconciler getPresentationReconciler(
            ISourceViewer sourceViewer) {

        PresentationReconciler reconciler = new PresentationReconciler();

        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getHTMLScanner());
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

        dr = new DefaultDamagerRepairer(getTPLCodeScanner());
        reconciler.setDamager(dr, TPLPartitionScanner.TPL_CODE);
        reconciler.setRepairer(dr, TPLPartitionScanner.TPL_CODE);

        TextAttribute textAttribute = new TextAttribute(colorManager
                .getColor(TPLColorConstants.getComment()));
        NonRuleBasedDamagerRepairer ndr = new NonRuleBasedDamagerRepairer(
                textAttribute);
        reconciler.setDamager(ndr, TPLPartitionScanner.TPL_COMMENT);
        reconciler.setRepairer(ndr, TPLPartitionScanner.TPL_COMMENT);

        textAttribute = new TextAttribute(colorManager
                .getColor(TPLColorConstants.getComment()));
        ndr = new NonRuleBasedDamagerRepairer(textAttribute);
        reconciler.setDamager(ndr, TPLPartitionScanner.HTML_COMMENT);
        reconciler.setRepairer(ndr, TPLPartitionScanner.HTML_COMMENT);
        return reconciler;
    }

    /**
     * Return the scanner for HTML highlighting.
     * 
     * @return The scanner for HTML highlighting.
     */
    protected HTMLCodeScanner getHTMLScanner() {
        if (htmlScanner == null)
            htmlScanner = new HTMLCodeScanner(colorManager);
        return htmlScanner;
    }

    /**
     * Return the scanner for comments highlighting.
     * 
     * @return The scanner for comments highlighting.
     */
    protected TPLCodeScanner getTPLCodeScanner() {
        if (operatorScanner == null)
            operatorScanner = new TPLCodeScanner(colorManager);
        return operatorScanner;
    }

    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        ContentAssistant contentAssistant = new ContentAssistant();
        contentAssistant.setContentAssistProcessor(new OperatorProcessor(),
                IDocument.DEFAULT_CONTENT_TYPE);
        contentAssistant.setContentAssistProcessor(new OperatorProcessor(),
                TPLPartitionScanner.TPL_CODE);
        contentAssistant
                .setInformationControlCreator(getInformationControlCreator(sourceViewer));

        contentAssistant.enableAutoActivation(true);
        contentAssistant.setAutoActivationDelay(500);
        contentAssistant
                .setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);
        contentAssistant
                .setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);

        return contentAssistant;
    }
}
