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
package ch.wess.ezclipse.ini.tests.editor.internal.partition;

import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.junit.Test;

import ch.wess.ezclipse.ini.editor.internal.partition.INIPartitioner;

/**
 * Test of the INIPartitioner class.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class INIPartitionerTest {
    
    /**
     * This method only tests if we can create an instance of the class under
     * test.
     */
    @Test
    public void testInstance() {
        IPartitionTokenScanner tokenScanner = null;
        new INIPartitioner(tokenScanner, new String[] {});
    }
}
