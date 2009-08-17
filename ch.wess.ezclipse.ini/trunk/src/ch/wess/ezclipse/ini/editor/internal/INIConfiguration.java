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
package ch.wess.ezclipse.ini.editor.internal;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import ch.wess.ezclipse.core.ColorManager;
import ch.wess.ezclipse.ini.editor.internal.partition.INIPartitionScanner;
import ch.wess.ezclipse.ini.editor.internal.scanners.INICodeScanner;
import ch.wess.ezclipse.ini.preferences.internal.INIColorConstants;

/**
 * Main class to configure the INI editor's features.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class INIConfiguration extends SourceViewerConfiguration {
    
    private ColorManager colorManager;
    private INICodeScanner iniCodeScanner = null;
    
    /**
     * Constructor, set the color manager.
     * 
     * @param manager
     */
    public INIConfiguration(ColorManager manager) {
        this.colorManager = manager;
    }
    
    /**
     * Returns the configured content types which are defined in the
     * INIPartitionerScanner class.
     * 
     * @see ch.wess.ezclipse.editors.internal.partition.TPLPartitionScanner#PARTITION_TYPES
     * @param sourceViewer
     */
    @Override
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return INIPartitionScanner.PARTITION_TYPES;
    }
    
    /**
     * Method used for syntax highlighting.
     * 
     * @param sourceViewer
     */
    @Override
    public IPresentationReconciler getPresentationReconciler (
            ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new PresentationReconciler();
        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(
                getINICodeScanner());
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
        
        TextAttribute textAttribute = new TextAttribute(colorManager.getColor(INIColorConstants.getComment()));
        NonRuleBasedDamagerRepairer ndr = new NonRuleBasedDamagerRepairer(textAttribute);
        reconciler.setDamager(ndr, INIPartitionScanner.INI_COMMENT);
        reconciler.setRepairer(ndr, INIPartitionScanner.INI_COMMENT);
        
        return reconciler;
    }

    /**
     * Return the INI file scanner.
     * 
     * @return
     */
    private ITokenScanner getINICodeScanner() {
        if(iniCodeScanner == null)
            iniCodeScanner = new INICodeScanner(colorManager);
        return iniCodeScanner;
    }
}
