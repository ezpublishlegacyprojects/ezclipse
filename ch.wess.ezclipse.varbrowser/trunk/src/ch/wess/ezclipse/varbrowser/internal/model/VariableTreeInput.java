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

/**
 * This class is used to set the root input of the TreeViewer
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class VariableTreeInput {
    
    /**
     * Contains the currenct project.
     */
    private IProject project = null;
    
    /**
     * Contains the currenct node id.
     */
    private String nodeId = ""; //$NON-NLS-1$
    
    /**
     * Constructor, set the project and node id.
     * @param project
     * @param nodeId
     */
    public VariableTreeInput(IProject project, String nodeId) {
        super();
        this.project = project;
        this.nodeId = nodeId;
    }
    
    /**
     * Getter for the project attribute.
     * @return
     */
    public IProject getProject() {
        return project;
    }

    /**
     * Setter for the project attribute.
     * @param project
     */
    public void setProject(IProject project) {
        this.project = project;
    }

    /**
     * Getter for the nodeId attribute.
     * @return
     */
    public String getNodeId() {
        return nodeId;
    }
    
    /**
     * Setter for the nodeId attribute.
     * @param nodeId
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
