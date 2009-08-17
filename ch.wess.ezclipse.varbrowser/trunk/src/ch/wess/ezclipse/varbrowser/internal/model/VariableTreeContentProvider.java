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
package ch.wess.ezclipse.varbrowser.internal.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.progress.DeferredTreeContentManager;

import ch.wess.ezclipse.varbrowser.nl1.Messages;

/**
 * This class is responsible of providing content to the Tree Viewer.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class VariableTreeContentProvider implements ITreeContentProvider {
    
    /**
     * Contains the current project.
     */
    private IProject project = null;
    
    /**
     * Contains the current node id.
     */
    private String nodeId = ""; //$NON-NLS-1$
    
    /**
     * Contains the manager for lazy tree.
     */
    private DeferredTreeContentManager manager;

    /**
     * This method is only called for the root elements of the tree. In our case
     * the root element is always "node". But if the project or node id is not
     * set it return an error message for the user.
     * 
     * @param inputElement
     */
    @Override
    public Object[] getElements(Object inputElement) {
        VariableTreeInput vti = (VariableTreeInput) inputElement;
        this.project = vti.getProject();
        this.nodeId = vti.getNodeId();
        if (project != null && nodeId != null && !nodeId.equals("")) //$NON-NLS-1$
            return new Object[] { new Variable(
                    "node", //$NON-NLS-1$
                    "object[eZContentObjectTreeNode]", Messages.getString("VariableTreeContentProvider.0"), null, nodeId, project) }; //$NON-NLS-1$ //$NON-NLS-2$
        return new Object[] { new Variable(
                Messages.getString("VariableTreeContentProvider.1"), "", "", null, nodeId,  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
                project) };
    }

    /**
     * Constructor, set the project and node id.
     * 
     * @param project
     * @param nodeId
     */
    public VariableTreeContentProvider(IProject project, String nodeId) {
        this.project = project;
        this.nodeId = nodeId;
    }
    
    /**
     * Returns the children of a tree node using the lazy tree manager.
     * 
     * @param parentElement
     */
    @Override
    public Object[] getChildren(Object parentElement) {
        return manager.getChildren(parentElement);
    }

    /**
     * Returns the parent node of node.
     * 
     * @param element
     */
    @Override
    public Object getParent(Object element) {
        return ((Variable) element).getParent();
    }
    
    /**
     * Define if a node have children or not. This method is used to show or not
     * a ">" in front of a tree node. If the project or node id is not set, the
     * node cannot have children, so we return false. I the other cases the lazy
     * tree manager will handle this.
     * 
     * @param element
     */
    @Override
    public boolean hasChildren(Object element) {
        if (project != null && nodeId != null && !nodeId.equals("")) //$NON-NLS-1$
            return manager.mayHaveChildren(element);
        return false;
    }

    /**
     * Method called when the object is destroyed.
     */
    @Override
    public void dispose() {
        // Nothing to do.
    }

    /**
     * This method is called when the input changed. For example if we do
     * treeViewer.setInput(). The lazy tree manager have to be set with the new
     * Tree Viewer 
     */
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (viewer instanceof AbstractTreeViewer)
            manager = new DeferredTreeContentManager(
                    (AbstractTreeViewer) viewer);
    }

}
