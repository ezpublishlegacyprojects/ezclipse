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

import org.eclipse.jface.text.IDocument;

import ch.wess.ezclipse.tpl.editor.internal.TPLDocumentProvider;

/**
 * Mock object, only used to test the TPLDocumentProvider.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class MockTPLDocumentProvider extends TPLDocumentProvider {
    
    /**
     * Override of this method because the parent method's scope is protected.
     * 
     *  @param element
     */
    @Override
    public IDocument createDocument(Object element) {
        return super.createDocument(element);
    }
}
