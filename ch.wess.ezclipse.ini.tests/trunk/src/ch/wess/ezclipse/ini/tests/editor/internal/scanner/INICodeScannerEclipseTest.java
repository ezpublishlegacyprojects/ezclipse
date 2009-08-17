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
package ch.wess.ezclipse.ini.tests.editor.internal.scanner;

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
import ch.wess.ezclipse.ini.editor.internal.scanners.INICodeScanner;
import ch.wess.ezclipse.ini.preferences.internal.INIColorConstants;
import ch.wess.ezclipse.ini.tests.editor.internal.MockINIDocumentProvider;

/**
 * Tests if the INI code scanner correctly identify the different part of a INI
 * partition.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class INICodeScannerEclipseTest {

    /**
     * Test if we can instanciate the class.
     */
    @Test
    public void testCreate() {
        new INICodeScanner(new ColorManager());
    }

    /**
     * Test if the correct colors are used for the different regions.
     * 
     * @throws Exception
     */
    @Test
    public void testColors() throws Exception {
        // Creation of the document to test.
        MockINIDocumentProvider docProvider = new MockINIDocumentProvider();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProject project = workspace.getRoot().getProject("test"); //$NON-NLS-1$
        IFile file = project.getFile("test-ini-part.ini"); //$NON-NLS-1$
        FileEditorInput input = new FileEditorInput(file);
        IDocument doc = docProvider.createDocument(input);
        
        int[] offsets = { 28, 29, 46, 51, 58, 59, 66 };
        int[] lengths = { 1, 17, 5, 7, 1, 7, 5 };
        List<Color> colors = new ArrayList<Color>();
        ColorManager colorManager = new ColorManager();
        INICodeScanner codeScanner = new INICodeScanner(colorManager);
        
        for (int i = 0; i < offsets.length; i++) {
            codeScanner.setRange(doc, offsets[i], lengths[i]);
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
        }

        Color[] expectedColors = {
            colorManager.getColor(INIColorConstants.getValue()),
            colorManager.getColor(INIColorConstants.getVariable()),
            colorManager.getColor(INIColorConstants.getValue()),
            colorManager.getColor(INIColorConstants.getValue()),
            colorManager.getColor(INIColorConstants.getValue()),
            colorManager.getColor(INIColorConstants.getValue()),
            colorManager.getColor(INIColorConstants.getValue()),
            colorManager.getColor(INIColorConstants.getArray()),
            colorManager.getColor(INIColorConstants.getValue()),
            colorManager.getColor(INIColorConstants.getArray()),
            colorManager.getColor(INIColorConstants.getValue()),
            colorManager.getColor(INIColorConstants.getValue()),
            colorManager.getColor(INIColorConstants.getValue()),
            colorManager.getColor(INIColorConstants.getValue()),
            colorManager.getColor(INIColorConstants.getValue())
        };
        
        Assert.assertArrayEquals(expectedColors, colors.toArray());
    }
}
