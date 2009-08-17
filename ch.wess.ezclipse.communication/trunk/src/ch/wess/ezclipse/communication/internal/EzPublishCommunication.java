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
package ch.wess.ezclipse.communication.internal;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.eclipse.core.resources.IProject;

import ch.wess.ezclipse.communication.CommunicationActivator;
import ch.wess.ezclipse.communication.ISoapCommunication;
import ch.wess.ezclipse.communication.nl1.Messages;
import ch.wess.ezclipse.communication.properties.ICommunicationProperties;
import ch.wess.ezclipse.core.Logger;

/**
 * Class which communicate via a SOAP web service with eZ Publish.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class EzPublishCommunication implements ISoapCommunication {

    /**
     * Contains the message of the last error;
     */
    private String errorMessage = "";//$NON-NLS-1$

    /**
     * Make a connection to the eZ Publish SOAP webservice and call the
     * specified method with the specified params.
     * 
     * @params url URL of the webservice.
     * @params method Method to call on the webservice.
     * @params params Parameter of the method.
     */
    public Object call(String method, Object[] params, IProject project) {
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();

            call.setTargetEndpointAddress(new java.net.URL(project
                    .getPersistentProperty(ICommunicationProperties.SOAP_URL)));
            call.setOperationName(new QName(
                    "http://www.wess.ch/ezclipse", method)); //$NON-NLS-1$

            Object[] finalParams = new Object[params.length + 2];
            finalParams[0] = project
                    .getPersistentProperty(ICommunicationProperties.SOAP_LOGIN);
            finalParams[1] = project
                    .getPersistentProperty(ICommunicationProperties.SOAP_PASSWORD);
            
            for(int i = 2; i < finalParams.length; i++) {
                finalParams[i] = params[i - 2];
            }

            return call.invoke(finalParams);
        } catch (Exception e) {
            Logger.logError(e, CommunicationActivator.PLUGIN_ID);
        }
        return null;
    }

    /**
     * Check if the connection is ok.
     * 
     * @param url
     * @param login
     * @param password
     * @return
     */
    public boolean testConnection(String url, String login, String password) {
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();

            call.setTargetEndpointAddress(new java.net.URL(url));
            call.setOperationName(new QName(
                    "http://www.wess.ch/ezclipse", "checkAccess")); //$NON-NLS-1$ //$NON-NLS-2$

            Object ret = call.invoke(new Object[] { login, password });

            if (ret instanceof Boolean) {
                errorMessage = Messages.getString("EzPublishCommunication.0"); //$NON-NLS-1$
                return true;
            } else if (ret instanceof String) {
                errorMessage = (String) ret;
                return false;
            }

        } catch (Exception e) {
            errorMessage = Messages.getString("EzPublishCommunication.1"); //$NON-NLS-1$
            return false;
        }
        errorMessage = Messages.getString("EzPublishCommunication.2"); //$NON-NLS-1$
        return false;
    }

    /**
     * Return the error message of the last action.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
