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
package ch.wess.ezclipse.tpl.tests.editor.internal.partition;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.ui.part.FileEditorInput;
import org.junit.Assert;
import org.junit.Test;

import ch.wess.ezclipse.tpl.editor.internal.partition.TPLPartitionScanner;
import ch.wess.ezclipse.tpl.tests.editor.internal.MockTPLDocumentProvider;

/**
 * Tests if for a given document the partitions are correct.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class TPLPartitionScannerEclipseTest {

    /**
     * Test if for the document test-part.tpl the correct partition are made.
     */
    @Test
    public void testPartitioning() throws BadLocationException{
        MockTPLDocumentProvider docProvider = new MockTPLDocumentProvider();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProject project = workspace.getRoot().getProject("test"); //$NON-NLS-1$
        IFile file = project.getFile("test-part.tpl"); //$NON-NLS-1$
        FileEditorInput input = new FileEditorInput(file);
        IDocument doc = docProvider.createDocument(input);
        
        ITypedRegion[] partitions = doc.computePartitioning(0, doc
                .getLength());
        
        String[] regionTypes = new String[partitions.length];
        int i = 0;
        for(ITypedRegion region : partitions) {
            regionTypes[i] = region.getType();
            i++;
        }
        
        String[] expectedPart = {
                IDocument.DEFAULT_CONTENT_TYPE,
                TPLPartitionScanner.HTML_COMMENT,
                IDocument.DEFAULT_CONTENT_TYPE,
                TPLPartitionScanner.TPL_COMMENT,
                IDocument.DEFAULT_CONTENT_TYPE,
                TPLPartitionScanner.TPL_CODE,
                IDocument.DEFAULT_CONTENT_TYPE
        };
        
        Assert.assertArrayEquals(expectedPart, regionTypes);
    }
}
