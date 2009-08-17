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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

import ch.wess.ezclipse.core.Logger;
import ch.wess.ezclipse.ini.INIActivator;
import ch.wess.ezclipse.ini.editor.internal.partition.INIPartitionScanner;
import ch.wess.ezclipse.ini.editor.internal.partition.INIPartitioner;
import ch.wess.ezclipse.ini.nl1.Messages;

/**
 * Class which provide and partition the ini document to edit.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class INIDocumentProvider extends FileDocumentProvider {
    
    /**
     * Create the document and set the partitioner
     * 
     * @param element
     *            Document source
     */
    @Override
    protected IDocument createDocument(Object element) {
        try {
            IDocument document = super.createDocument(element);
            if (document != null) {
                IDocumentPartitioner partitioner = new INIPartitioner(
                        new INIPartitionScanner(),
                        INIPartitionScanner.PARTITION_TYPES);

                partitioner.connect(document);
                document.setDocumentPartitioner(partitioner);
            }
            return document;
        } catch (CoreException exception) {
            Logger.logError(Messages.getString("INIDocumentProvider.0"), exception, INIActivator.PLUGIN_ID); //$NON-NLS-1$
            return null;
        }
    }
}
