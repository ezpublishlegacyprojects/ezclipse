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

import java.util.ArrayList;
import java.util.List;

import org.apache.axis.encoding.ser.ArrayDeserializer.ArrayListExtension;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.progress.IDeferredWorkbenchAdapter;
import org.eclipse.ui.progress.IElementCollector;

import ch.wess.ezclipse.communication.CommunicationActivator;
import ch.wess.ezclipse.communication.ISoapCommunication;
import ch.wess.ezclipse.core.Logger;
import ch.wess.ezclipse.varbrowser.VarBrowserActivator;
import ch.wess.ezclipse.varbrowser.nl1.Messages;

/**
 * This class define the structure of a variable.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class Variable implements IDeferredWorkbenchAdapter {

    /**
     * Contains the name of the variable.
     */
    private String name = ""; //$NON-NLS-1$

    /**
     * Contains the type of the variable.
     */
    private String type = ""; //$NON-NLS-1$

    /**
     * Contains the value of the variable.
     */
    private String value = ""; //$NON-NLS-1$

    /**
     * Contains the node ID.
     */
    private String nodeId = ""; //$NON-NLS-1$

    /**
     * Contains the children of the variable.
     */
    private List<Variable> children = new ArrayList<Variable>();

    /**
     * Contains the project.
     */
    private IProject project = null;

    /**
     * Contains the parent of the variable.
     */
    private Variable parent = null;

    /**
     * Constructor, set the attributes.
     * 
     * @param name
     * @param type
     * @param value
     * @param parent
     * @param nodeId
     * @param project
     */
    public Variable(String name, String type, String value, Variable parent,
            String nodeId, IProject project) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.parent = parent;
        this.nodeId = nodeId;
        this.project = project;
    }

    /**
     * Getter for the name attribute.
     * 
     * @return name of the variable.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the type attribute.
     * 
     * @return the type of the variable.
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for the value attribute.
     * 
     * @return the value of the variable.
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter for the parent attribute.
     * 
     * @return the parent of the variable.
     */
    public Variable getParent() {
        return parent;
    }

    /**
     * Render the object as a <code>String</code>.
     * 
     * @return the variable object as a <code>String</code>.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Make a call to the Webservice and fetch the children of the variable.
     * 
     * @param object
     * @param collector
     * @param monitor
     * @see org.eclipse.ui.progress.IDeferredWorkbenchAdapter#fetchDeferredChildren(Object,
     *      IElementCollector, IProgressMonitor)
     */
    @Override
    public void fetchDeferredChildren(Object object,
            IElementCollector collector, IProgressMonitor monitor) {
        monitor.beginTask(Messages.getString("Variable.0"), 2); //$NON-NLS-1$

        String path = ""; //$NON-NLS-1$
        Variable currentItem = this;
        boolean first = true;
        while (currentItem.getName() != "node") { //$NON-NLS-1$
            path = currentItem.getName().concat(first ? "" : "." + path); //$NON-NLS-1$ //$NON-NLS-2$
            currentItem = currentItem.getParent();
            first = false;
        }

        final ISoapCommunication comm = CommunicationActivator
                .getNewCommunication();

        final Object[] params = new Object[] { nodeId,
                path.equals("") ? "" : path }; //$NON-NLS-1$ //$NON-NLS-2$

        try {
            Object response = comm.call("getAttributes", params, project); //$NON-NLS-1$
            if (response instanceof ArrayListExtension) {
                ArrayListExtension data = (ArrayListExtension) response;
                int j = 0;
                Variable var;
                for (Object item : data) {
                    if (item instanceof String[]) {
                        String[] itemData = (String[]) item;
                        var = new Variable(itemData[0], itemData[1],
                                itemData[2], this, nodeId, project);
                        children.add(var);
                        collector.add(var, monitor);
                        j++;
                    }
                }
            }
        } catch (Exception e) {
            Logger.logError(e, VarBrowserActivator.PLUGIN_ID);
        }
        monitor.done();
    }

    /**
     * This method is not used, always return <code>null</code>.
     * 
     * @return <code>null</code>
     */
    @Override
    public ISchedulingRule getRule(Object object) {
        return null;
    }

    /**
     * If the type is "array" or "object" it have children and this methode will
     * return true, else it's false.
     * 
     * @return <code>true</code> if the variable has children else
     *         <code>false</code>.
     */
    @Override
    public boolean isContainer() {
        if (type.contains("array") || type.contains("object")) //$NON-NLS-1$ //$NON-NLS-2$
            return true;
        return false;
    }

    /**
     * This method is never called but it return the children.
     * 
     * @param o
     * @return the chlidren of the variable.
     */
    @Override
    public Object[] getChildren(Object o) {
        return children.toArray();
    }

    /**
     * This method is never used, it will always return <code>null</code>.
     * 
     * @param object
     * @return <code>null</code>
     */
    @Override
    public ImageDescriptor getImageDescriptor(Object object) {
        return null;
    }

    /**
     * It will return the name of the variable as label.
     * 
     * @param o
     * @return the label of a variable
     */
    @Override
    public String getLabel(Object o) {
        return name;
    }

    /**
     * Get the parent of the variable.
     * 
     * @param o
     * @return the aprent of the variable.
     */
    @Override
    public Object getParent(Object o) {
        return parent;
    }
}
