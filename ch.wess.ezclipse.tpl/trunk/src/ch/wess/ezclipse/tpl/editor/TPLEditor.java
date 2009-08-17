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
package ch.wess.ezclipse.tpl.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.editors.text.TextEditor;

import ch.wess.ezclipse.core.ColorManager;
import ch.wess.ezclipse.tpl.editor.internal.TPLConfiguration;
import ch.wess.ezclipse.tpl.editor.internal.TPLDocumentProvider;
import ch.wess.ezclipse.tpl.marker.internal.TPLMarkingErrorHandler;

/**
 * Template editor.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class TPLEditor extends TextEditor implements ISelectionProvider {

    /**
     * Contains the current input of the editor.
     */
    private IEditorInput input;

    /**
     * Contains a list of the change listeners.
     */
    private List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

    /**
     * Create the editor
     */
    public TPLEditor() {
        super();
        setSourceViewerConfiguration(new TPLConfiguration(new ColorManager()));
        setDocumentProvider(new TPLDocumentProvider());
    }

    /**
     * Method called when the focus is set on the editor.
     */
    @Override
    public void setFocus() {
        super.setFocus();
        getSite().setSelectionProvider(this);

        SelectionChangedEvent event = new SelectionChangedEvent(this,
                getSelection());
        for (ISelectionChangedListener listener : listeners) {
            listener.selectionChanged(event);
        }
    }

    /**
     * Method added for validating the current document.
     */
    @Override
    protected void doSetInput(IEditorInput input) throws CoreException {
        super.doSetInput(input);
        this.input = input;
        validateAndMark();
    }

    /**
     * Method added for validating the current document.
     */
    @Override
    protected void editorSaved() {
        super.editorSaved();
        validateAndMark();
    }

    /**
     * Get the IFile of the current input.
     * 
     * @return the current file.
     */
    private IFile getInputFile() {
        IFileEditorInput ife = (IFileEditorInput) input;
        IFile file = ife.getFile();
        return file;
    }

    /**
     * Set the error markers.
     */
    private void validateAndMark() {
        TPLMarkingErrorHandler marker = new TPLMarkingErrorHandler();
        marker.parse(getDocumentProvider().getDocument(input), getInputFile());
    }

    /**
     * Add a change listener to the list.
     * 
     * @param listener
     */
    @Override
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        listeners.add(listener);
    }

    /**
     * Return the selection that will be given to the selection change
     * listeners.
     * 
     * @return the current input file.
     */
    @Override
    public ISelection getSelection() {
        return new StructuredSelection(getInputFile());
    }

    /**
     * Remove the specified listener from the change listener list.
     * 
     * @param listener
     */
    @Override
    public void removeSelectionChangedListener(
            ISelectionChangedListener listener) {
        listeners.remove(listener);
    }
    
    /**
     * This method is not implemented in this class.
     * 
     * @param selection
     */
    @Override
    public void setSelection(ISelection selection) {

    }
}
