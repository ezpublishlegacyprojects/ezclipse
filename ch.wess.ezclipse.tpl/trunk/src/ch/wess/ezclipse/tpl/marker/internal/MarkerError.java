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

import java.util.HashMap;
import java.util.Map;

import org.apache.axis.encoding.ser.ArrayDeserializer.ArrayListExtension;
import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;

import ch.wess.ezclipse.tpl.nl1.Messages;

/**
 * Class which contains an error.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class MarkerError {

    /**
     * Contains a list of all marker attributes.
     */
    private Map<String, Object> attributes = new HashMap<String, Object>();
    
    /**
     * Contains the document to be marked.
     */
    private IDocument document;
    
    /**
     * Constructor, set the attributes.
     * 
     * @param error
     * @param document
     */
    public MarkerError(Object error, IDocument document) {
        attributes.put(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
        this.document = document;

        if (error instanceof String[])
            setError((String[]) error);
        else if (error instanceof ArrayListExtension)
            setError((ArrayListExtension) error);
    }
    
    /**
     * Mark an error.
     * 
     * @param error
     */
    private void setError(String[] error) {
        attributes.put(IMarker.MESSAGE, error[0]);
    }

    /**
     * Set all errors detected in the document.
     * 
     * @param error
     */
    private void setError(ArrayListExtension error) {
        switch (error.size()) {
        case 3:
            attributes.put(IMarker.MESSAGE, error.get(0));
            attributes.put(IMarker.CHAR_START, error.get(1));
            attributes.put(IMarker.CHAR_END, error.get(2));
            break;
        case 5:
            attributes.put(IMarker.MESSAGE, error.get(0));
            attributes.put(IMarker.CHAR_START, getCharStart((Integer) error
                    .get(1), (Integer) error.get(2)));
            attributes.put(IMarker.CHAR_END, getCharStart((Integer) error
                    .get(3), (Integer) error.get(4)));
            break;
        default:
            attributes.put(IMarker.MESSAGE, Messages.getString("MarkerError.0")); //$NON-NLS-1$
        }
    }
    
    /**
     * Compute the start char position with the line number and column number.
     * 
     * @param lineNumber
     * @param columnNumber
     * @return the start char
     */
    private Integer getCharStart(int lineNumber, int columnNumber) {
        try {
            int lineStartChar = document.getLineOffset(lineNumber - 1);
            Integer charEnd = getCharEnd(lineNumber, columnNumber);
            if (charEnd != null) {
                ITypedRegion typedRegion = document.getPartition(charEnd
                        .intValue() - 2);
                int partitionStartChar = typedRegion.getOffset();
                return new Integer(partitionStartChar);
            } else
                return new Integer(lineStartChar);
        } catch (BadLocationException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Compute the end char position with the line number and column number.
     * 
     * @param lineNumber
     * @param columnNumber
     * @return
     */
    private Integer getCharEnd(int lineNumber, int columnNumber) {
        try {
            return new Integer(document.getLineOffset(lineNumber - 1)
                    + columnNumber);
        } catch (BadLocationException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Get the attributes attribute.
     * 
     * @return the marker attributes
     */
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
