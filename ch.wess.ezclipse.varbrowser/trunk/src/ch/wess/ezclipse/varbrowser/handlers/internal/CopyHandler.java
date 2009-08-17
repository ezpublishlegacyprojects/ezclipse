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
package ch.wess.ezclipse.varbrowser.handlers.internal;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.handlers.HandlerUtil;

import ch.wess.ezclipse.varbrowser.internal.model.Variable;

/**
 * This class is used to execute the copy command. When the user clicks on the
 * "copy" button, the tree path will be copied to the clipboard.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class CopyHandler extends AbstractHandler {
    
    /**
     * This method copy the tree path of the selected element to the clipboard.
     * 
     * @param event
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        // Retrieve the selected element.
        ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
                .getActivePage().getSelection();
        if (selection != null & selection instanceof IStructuredSelection) {
            IStructuredSelection strucSelection = (IStructuredSelection) selection;
            Object selectedElement = strucSelection.getFirstElement();

            if (selectedElement instanceof Variable) {
                String path = ""; //$NON-NLS-1$
                Variable currentItem = (Variable) selectedElement;
                boolean first = true;
                while (currentItem.getName() != "node") { //$NON-NLS-1$
                    path = currentItem.getName()
                            .concat(first ? "" : "." + path); //$NON-NLS-1$ //$NON-NLS-2$
                    currentItem = currentItem.getParent();
                    first = false;
                }

                String finalPath = "$node." + path; //$NON-NLS-1$
                Clipboard cp = new Clipboard(HandlerUtil.getActivePart(event)
                        .getSite().getShell().getDisplay());
                TextTransfer tt = TextTransfer.getInstance();
                cp.setContents(new String[] {finalPath}, new Transfer[] {tt});
                cp.dispose();
            }
        }
        return null;
    }

}
