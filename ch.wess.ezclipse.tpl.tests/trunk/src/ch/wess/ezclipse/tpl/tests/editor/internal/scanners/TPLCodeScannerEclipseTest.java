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
package ch.wess.ezclipse.tpl.tests.editor.internal.scanners;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.part.FileEditorInput;
import org.junit.Assert;
import org.junit.Test;

import ch.wess.ezclipse.core.ColorManager;
import ch.wess.ezclipse.tpl.editor.internal.scanners.TPLCodeScanner;
import ch.wess.ezclipse.tpl.preferences.internal.TPLColorConstants;
import ch.wess.ezclipse.tpl.tests.editor.internal.MockTPLDocumentProvider;

/**
 * Tests if the TPL code scanner correctly identify the different part of a
 * TPL partition.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class TPLCodeScannerEclipseTest {
    
    /**
     * Test if we can instanciate the class.
     */
    @Test
    public void testCreate() {
        new TPLCodeScanner(new ColorManager());
    }
    
    /**
     * Test if the correct colors are used for each region type.
     * 
     * @throws Exception
     */
    @Test
    public void testColors() throws Exception {
        // Creation of the document to test.
        MockTPLDocumentProvider docProvider = new MockTPLDocumentProvider();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProject project = workspace.getRoot().getProject("test"); //$NON-NLS-1$
        IFile file = project.getFile("test-part.tpl"); //$NON-NLS-1$
        FileEditorInput input = new FileEditorInput(file);
        IDocument doc = docProvider.createDocument(input);

        List<Color> colors = new ArrayList<Color>();
        ColorManager colorManager = new ColorManager();
        TPLCodeScanner codeScanner = new TPLCodeScanner(colorManager);

        codeScanner.setRange(doc, 83, 63);
        while (true) {
            IToken token = codeScanner.nextToken();
            if (token.isEOF())
                break;

            TextAttribute attr = codeScanner.getAttr(token.getData());
            if (attr != null) {
                colors.add(attr.getForeground());
            } else {
                throw new Exception("Null attribute"); //$NON-NLS-1$
            }
        }

        Color[] expectedColors = {
                colorManager.getColor(TPLColorConstants.getDefault()),
                colorManager.getColor(TPLColorConstants.getOperator()),
                colorManager.getColor(TPLColorConstants.getDefault()),
                colorManager.getColor(TPLColorConstants.getParameter()),
                colorManager.getColor(TPLColorConstants.getVariable()),
                colorManager.getColor(TPLColorConstants.getDefault()),
                colorManager.getColor(TPLColorConstants.getParameter()),
                colorManager.getColor(TPLColorConstants.getString()),
                colorManager.getColor(TPLColorConstants.getDefault())
        };

        Assert.assertArrayEquals(expectedColors, colors.toArray());
    }
}
