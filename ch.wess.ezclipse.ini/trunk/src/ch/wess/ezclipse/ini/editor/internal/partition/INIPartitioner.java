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
package ch.wess.ezclipse.ini.editor.internal.partition;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

/**
 * This class is only created for debugging purposes.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class INIPartitioner extends FastPartitioner {
    /**
     * Constructor, needed to call the super constructor.
     * 
     * @param scanner
     * @param legalContentTypes
     */
    public INIPartitioner(IPartitionTokenScanner scanner,
            String[] legalContentTypes) {
        super(scanner, legalContentTypes);
    }
    
    
    /**
     * Override for partition printing.
     * 
     * @param document
     * @param delayInitialization
     */
    @Override
    public void connect(IDocument document, boolean delayInitialization) {
        super.connect(document, delayInitialization);
        //printPartitions(document);
    }



    /**
     * This method was copied from
     * <a href="http://www.realsolve.co.uk/site/tech/jface-text.php">
     * Building an Eclipse Text Editor with JFace Text</a>
     * 
     * @param document
     */
    public void printPartitions(IDocument document) {
        StringBuffer buffer = new StringBuffer();

        ITypedRegion[] partitions = computePartitioning(0, document.getLength());
        for (int i = 0; i < partitions.length; i++) {
            try {
                buffer.append("Partition type: " //$NON-NLS-1$
                        + partitions[i].getType()
                        + ", offset: " + partitions[i].getOffset() //$NON-NLS-1$
                        + ", length: " + partitions[i].getLength()); //$NON-NLS-1$
                buffer.append("\n"); //$NON-NLS-1$
                buffer.append("Text:\n"); //$NON-NLS-1$
                buffer.append(document.get(partitions[i].getOffset(),
                        partitions[i].getLength()));
                buffer.append("\n---------------------------\n\n\n"); //$NON-NLS-1$
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
        System.out.print(buffer);
    }
}
