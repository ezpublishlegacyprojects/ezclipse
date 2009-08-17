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
package ch.wess.ezclipse.communication.properties;

import org.eclipse.core.runtime.QualifiedName;

import ch.wess.ezclipse.communication.CommunicationActivator;

/**
 * Contains the qualifiers for the properties of a project.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public interface ICommunicationProperties {
    
    /**
     * Identifier for the soap url attribute.
     */
    public static final QualifiedName SOAP_URL = new QualifiedName(
            CommunicationActivator.PLUGIN_ID, "url"); //$NON-NLS-1$
    
    /**
     * Identifier for the login attribute.
     */
    public static final QualifiedName SOAP_LOGIN = new QualifiedName(
            CommunicationActivator.PLUGIN_ID, "login"); //$NON-NLS-1$
    
    /**
     * Identifier for the password attribute.
     */
    public static final QualifiedName SOAP_PASSWORD = new QualifiedName(
            CommunicationActivator.PLUGIN_ID, "password"); //$NON-NLS-1$
}
