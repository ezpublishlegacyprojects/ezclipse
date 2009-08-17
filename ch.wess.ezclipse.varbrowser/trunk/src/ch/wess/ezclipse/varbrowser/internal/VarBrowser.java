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
package ch.wess.ezclipse.varbrowser.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import ch.wess.ezclipse.core.IFileProperties;
import ch.wess.ezclipse.varbrowser.internal.model.Variable;
import ch.wess.ezclipse.varbrowser.internal.model.VariableTreeContentProvider;
import ch.wess.ezclipse.varbrowser.internal.model.VariableTreeInput;
import ch.wess.ezclipse.varbrowser.nl1.Messages;

/**
 * Show a tree with all variables of the specified node.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class VarBrowser extends ViewPart implements ISelectionListener {

    /**
     * Contains the TreeViewer component.
     */
    private TreeViewer treeViewer;

    /**
     * Contains the content provider for the TreeViewer.
     */
    private VariableTreeContentProvider contentProvider = null;

    /**
     * Method called at start to create the GUI of the view.
     */
    @Override
    public void createPartControl(final Composite parent) {
        IFile file = null;
        try {
            file = (IFile) getSite().getPage().getActiveEditor()
                    .getEditorInput().getAdapter(IFile.class);
        } catch (Exception e) {
            // Nothing to do
        }
        
        String nodeId = ""; //$NON-NLS-1$
        IProject project = null;
        
        if(file != null) {
            try {
                nodeId = file.getPersistentProperty(IFileProperties.NODE_ID);
                project = file.getProject();
            } catch (CoreException ce) {
                // Nothing to do.
            }
        }
        
        contentProvider = new VariableTreeContentProvider(project, nodeId);

        treeViewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL
                | SWT.VIRTUAL);
        treeViewer.setContentProvider(contentProvider);
        
        if(file != null)
            treeViewer.setInput(new VariableTreeInput(file.getProject(), nodeId));
        
        TreeViewerColumn column1 = new TreeViewerColumn(treeViewer, SWT.NONE);
        column1.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (!(element instanceof Variable))
                    return Messages.getString("VarBrowser.0"); //$NON-NLS-1$
                return ((Variable) element).getName();
            }
        });
        column1.getColumn().setText(Messages.getString("VarBrowser.1")); //$NON-NLS-1$
        column1.getColumn().setWidth(200);

        TreeViewerColumn column2 = new TreeViewerColumn(treeViewer, SWT.NONE);
        column2.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (!(element instanceof Variable))
                    return ""; //$NON-NLS-1$
                return ((Variable) element).getType();
            }
        });
        column2.getColumn().setText(Messages.getString("VarBrowser.2")); //$NON-NLS-1$
        column2.getColumn().setWidth(200);

        TreeViewerColumn column3 = new TreeViewerColumn(treeViewer, SWT.NONE);
        column3.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (!(element instanceof Variable))
                    return ""; //$NON-NLS-1$
                return ((Variable) element).getValue();
            }
        });
        column3.getColumn().setText(Messages.getString("VarBrowser.3")); //$NON-NLS-1$
        column3.getColumn().setWidth(300);

        treeViewer.getTree().setHeaderVisible(true);
        treeViewer.getTree().setLinesVisible(true);

        getSite().getWorkbenchWindow().getSelectionService()
                .addSelectionListener(this);

        createContextMenu();
    }

    /**
     * This method is called when the focus is on the view.
     */
    @Override
    public void setFocus() {
        // Nothing to do.
    }

    /**
     * This method is called everytime when the selection changed. We are only
     * interested to know when the TPL editor selection is changing because we
     * need to update the node id of the actual file.
     */
    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (selection instanceof StructuredSelection) {
            StructuredSelection ss = (StructuredSelection) selection;
            Object element = ss.getFirstElement();
            if (element instanceof IFile) {
                IFile file = (IFile) element;
                IProject project = file.getProject();
                try {
                    String nodeId = file
                            .getPersistentProperty(IFileProperties.NODE_ID);
                    if (nodeId != null && !nodeId.equals("")) { //$NON-NLS-1$
                        VariableTreeInput input = new VariableTreeInput(
                                project, nodeId);
                        treeViewer.setInput(input);
                    } else {
                        VariableTreeInput input = new VariableTreeInput(null,
                                ""); //$NON-NLS-1$
                        treeViewer.setInput(input);
                    }
                } catch (CoreException coreEx) {
                    VariableTreeInput input = new VariableTreeInput(null, ""); //$NON-NLS-1$
                    treeViewer.setInput(input);
                }

            }
        }
    }

    /**
     * Return the treeViewer attribute.
     * 
     * @return the treeViewer attribute.
     */
    public TreeViewer getTreeViewer() {
        return treeViewer;
    }

    /**
     * This method remove the view from the selection service.
     */
    @Override
    public void dispose() {
        super.dispose();
        getSite().getWorkbenchWindow().getSelectionService()
                .removeSelectionListener(this);
    }

    /**
     * Create the context menu.
     */
    private void createContextMenu() {
        MenuManager menuManager = new MenuManager();
        Menu menu = menuManager.createContextMenu(treeViewer.getTree());

        // Set the MenuManager
        treeViewer.getTree().setMenu(menu);
        getSite().registerContextMenu(menuManager, treeViewer);

        // Make the selection available
        getSite().setSelectionProvider(treeViewer);
    }
}
