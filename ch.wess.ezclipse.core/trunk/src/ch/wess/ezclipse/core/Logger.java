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
package ch.wess.ezclipse.core;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import ch.wess.ezclipse.core.nl1.Messages;

/**
 * This class was copied from book samples :
 * http://www.qualityeclipse.com/projects/index.html
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class Logger {

    /**
     * Log the specified information.
     * 
     * @param message
     *            , a human-readable message, localized to the current locale.
     */
    public static void logInfo(String message, String pluginId) {
        log(IStatus.INFO, IStatus.OK, message, null, pluginId);
    }

    /**
     * Log the specified error.
     * 
     * @param exception
     *            , a low-level exception.
     */
    public static void logError(Throwable exception, String pluginId) {
        logError(Messages.getString("Logger.0"), exception, pluginId); //$NON-NLS-1$
    }

    /**
     * Log the specified error.
     * 
     * @param message
     *            , a human-readable message, localized to the current locale.
     * @param exception
     *            , a low-level exception, or <code>null</code> if not
     *            applicable.
     */
    public static void logError(String message, Throwable exception,
            String pluginId) {
        log(IStatus.ERROR, IStatus.OK, message, exception, pluginId);
    }

    /**
     * Log the specified information.
     * 
     * @param severity
     *            , the severity; one of the following: <code>IStatus.OK</code>,
     *            <code>IStatus.ERROR</code>, <code>IStatus.INFO</code>, or
     *            <code>IStatus.WARNING</code>.
     * @param pluginId
     *            . the unique identifier of the relevant plug-in.
     * @param code
     *            , the plug-in-specific status code, or <code>OK</code>.
     * @param message
     *            , a human-readable message, localized to the current locale.
     * @param exception
     *            , a low-level exception, or <code>null</code> if not
     *            applicable.
     */
    public static void log(int severity, int code, String message,
            Throwable exception, String pluginId) {
        log(createStatus(severity, code, message, exception, pluginId));
    }

    /**
     * Create a status object representing the specified information.
     * 
     * @param severity
     *            , the severity; one of the following: <code>IStatus.OK</code>,
     *            <code>IStatus.ERROR</code>, <code>IStatus.INFO</code>, or
     *            <code>IStatus.WARNING</code>.
     * @param pluginId
     *            , the unique identifier of the relevant plug-in.
     * @param code
     *            , the plug-in-specific status code, or <code>OK</code>.
     * @param message
     *            , a human-readable message, localized to the current locale.
     * @param exception
     *            , a low-level exception, or <code>null</code> if not
     *            applicable.
     * @return, the status object (not <code>null</code>).
     */
    public static IStatus createStatus(int severity, int code, String message,
            Throwable exception, String pluginId) {
        return new Status(severity, pluginId, code, message, exception);
    }

    /**
     * Log the given status.
     * 
     * @param status
     *            , the status to log.
     */
    public static void log(IStatus status) {
        CoreActivator.getDefault().getLog().log(status);
    }
}
