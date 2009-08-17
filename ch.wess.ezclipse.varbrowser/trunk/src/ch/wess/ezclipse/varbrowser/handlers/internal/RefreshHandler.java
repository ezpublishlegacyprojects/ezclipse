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
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import ch.wess.ezclipse.varbrowser.internal.VarBrowser;
import ch.wess.ezclipse.varbrowser.internal.model.Variable;

/**
 * This class is used to execute the refresh command. This command is called
 * when the user clicks on the "refresh" button in the context menu of the var
 * browser view.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class RefreshHandler extends AbstractHandler {

    /**
     * Refresh the subtree of the selected element.
     * 
     * @param event
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        // Retrieve the selected element.
        ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
                .getActivePage().getSelection();
        Object selectedElement = null;
        if (selection != null & selection instanceof IStructuredSelection) {
            IStructuredSelection strucSelection = (IStructuredSelection) selection;
            selectedElement = strucSelection.getFirstElement();
        }

        if (selectedElement != null && selectedElement instanceof Variable) {
            IWorkbenchPart varBrowser = HandlerUtil.getActivePart(event);
            if (varBrowser instanceof VarBrowser) {
                if (((Variable) selectedElement).isContainer()) {
                    ((VarBrowser) varBrowser).getTreeViewer().refresh(
                            selectedElement);
                } else {
                    if(((Variable) selectedElement).getParent() != null) {
                        ((VarBrowser) varBrowser).getTreeViewer().refresh(
                                ((Variable) selectedElement).getParent());
                    } else {
                        ((VarBrowser) varBrowser).getTreeViewer().refresh();
                    }
                }
            }
        }
        return null;
    }
}
