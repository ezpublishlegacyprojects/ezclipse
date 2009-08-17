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
package ch.wess.ezclipse.communication;

import org.eclipse.core.resources.IProject;

/**
 * This interface defines the function that can be used to communication with
 * the eZ SOAP web service.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public interface ISoapCommunication {

    /**
     * Make a call to the SOAP webservice and return the result.
     * 
     * @param method
     *            The name of the method.
     * @param params
     *            The parameters of the method.
     * @return The result return by the webservice.
     */
    public Object call(String method, Object[] params, IProject project);

    /**
     * Get the error message of the latest call.
     * 
     * @return the error message of latest call.
     */
    public String getErrorMessage();

    /**
     * Make a test call to the eZclipse webservice. If the result is
     * <code>false</code> use the {@link ISoapCommunication#getErrorMessage()}
     * method to retrieve the error.
     * 
     * @param url
     * @param login
     * @param password
     * @return the test result.
     */
    public boolean testConnection(String url, String login, String password);
}
