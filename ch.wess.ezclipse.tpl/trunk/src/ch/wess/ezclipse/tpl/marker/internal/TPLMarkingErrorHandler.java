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
package ch.wess.ezclipse.tpl.marker.internal;

import java.util.Map;

import org.apache.axis.encoding.ser.ArrayDeserializer.ArrayListExtension;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.texteditor.MarkerUtilities;

import ch.wess.ezclipse.communication.CommunicationActivator;
import ch.wess.ezclipse.communication.ISoapCommunication;
import ch.wess.ezclipse.core.Logger;
import ch.wess.ezclipse.tpl.nl1.Messages;

/**
 * Handler for managing the error marking. This class make a call to the
 * eZclipse webservice and create a list of {@link MarkerError}.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class TPLMarkingErrorHandler {

    /**
     * ID to identify the marker.
     */
    private final String ERROR_MARKER_ID = "ch.wess.ezclipse.tpl.marker"; //$NON-NLS-1$

    /**
     * Connects to the eZ Publish service and mark the errors.
     * 
     * @param document
     *            The content of the document.
     * @param file
     *            The content of the file.
     */
    public void parse(final IDocument document, final IFile file) {
        Job job = new Job(Messages.getString("TPLMarkingErrorHandler.0")) { //$NON-NLS-1$

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                try {
                    ISoapCommunication eZPComm = CommunicationActivator
                            .getNewCommunication();

                    IProject project = file.getProject();
                    String path = file.getFullPath().toString().substring(
                            project.getFullPath().toString().length() + 1);
                    Object[] params = new Object[] { path };

                    Object result = eZPComm.call(
                            "validateTpl", params, file.getProject()); //$NON-NLS-1$
                    if (result != null)
                        setErrors(result, document, file);
                    return Status.OK_STATUS;
                } catch (Exception e) {
                    Logger.logError(e, CommunicationActivator.PLUGIN_ID);
                    return Status.CANCEL_STATUS;
                }
            }
        };

        job.schedule();
    }

    /**
     * Set the markers.
     * 
     * @param errors
     * @param document
     *            The content of the file.
     * @param file
     *            The content of the file.
     * @throws CoreException
     */
    private void setErrors(Object ret, IDocument document, IFile file)
            throws CoreException {
        MarkerError[] errors = null;

        if (ret instanceof ArrayListExtension) {
            errors = new MarkerError[((ArrayListExtension) ret).size()];
            for (int i = 0; i < ((ArrayListExtension) ret).size(); i++) {
                errors[i] = new MarkerError(((ArrayListExtension) ret).get(i),
                        document);
            }
        }

        file.deleteMarkers(ERROR_MARKER_ID, true, IResource.DEPTH_ZERO);

        if (errors != null) {
            Map<String, Object> attributes;
            for (MarkerError error : errors) {
                attributes = error.getAttributes();
                attributes.put(IMarker.LOCATION, file.getFullPath());
                MarkerUtilities.createMarker(file, attributes, ERROR_MARKER_ID);
            }
        }
    }
}
