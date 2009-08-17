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

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import ch.wess.ezclipse.communication.internal.EzPublishCommunication;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class CommunicationActivator extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "ch.wess.ezclipse.communication";//$NON-NLS-1$

    // The shared instance
    private static CommunicationActivator plugin;

    /**
     * The constructor
     */
    public CommunicationActivator() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static CommunicationActivator getDefault() {
        return plugin;
    }

    /**
     * This method offer the possiblity to other plugins to user the
     * communication object.
     * 
     * @return a communication object
     */
    public static ISoapCommunication getNewCommunication() {
        return new EzPublishCommunication();
    }
}
