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
package ch.wess.ezclipse.tpl.tests.editor.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.part.FileEditorInput;
import org.junit.Assert;
import org.junit.Test;

/**
 * Class which tests the document provider.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class DocumentProviderTest {
    
    /**
     * Test if the return statement is correct when the document equals null.
     */
    @Test
    public void testNullDoc() {
        MockTPLDocumentProvider docProvider = new MockTPLDocumentProvider();
        IDocument doc = docProvider.createDocument(null);
        Assert.assertEquals(null, doc);
    }
    
    /**
     * Test if a correct file is given.
     */
    @Test
    public void testCorrectFile() {
        MockTPLDocumentProvider docProvider = new MockTPLDocumentProvider();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProject project = workspace.getRoot().getProject("test"); //$NON-NLS-1$
        IFile file = project.getFile("test.tpl"); //$NON-NLS-1$
        FileEditorInput input = new FileEditorInput(file);
        IDocument doc = docProvider.createDocument(input);
        Assert.assertNotNull(doc);
    }
    
    /**
     * Test if an incorrect file is given.
     */
    @Test
    public void testIncorrectFile() {
        MockTPLDocumentProvider docProvider = new MockTPLDocumentProvider();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProject project = workspace.getRoot().getProject("test"); //$NON-NLS-1$
        IFile file = project.getFile("any-incorrect-file.tpl"); //$NON-NLS-1$
        FileEditorInput input = new FileEditorInput(file);
        IDocument doc = docProvider.createDocument(input);
        Assert.assertEquals(null, doc);
    }
}
