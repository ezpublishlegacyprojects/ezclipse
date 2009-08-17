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
package ch.wess.ezclipse.tpl.tests.editor.internal.detectors;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.wess.ezclipse.tpl.editor.internal.detectors.VariableDetector;

/**
 * Test of the VariableDetector class.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
public class VariableDetectorTest {
    
    private char[] alphabet = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
            'U', 'V', 'W', 'X', 'Y', 'Z'};
    
    private char[] numbers = new char[] {'1', '2', '3', '4', '5', '6', '7', '8', 
            '9', '0', };
    
    private char[] otherChars = new char[] {'-', '_', ':', '.'};
    
    /**
     * This method only tests if we can create an instance of the class under
     * test.
     */
    @Test
    public void testInstance() {
        new VariableDetector();
    }
    
    /**
     * Test if all authorized chars in a parameter are correct.
     */
    @Test
    public void isWordPart() {
        VariableDetector vd = new VariableDetector();
        
        boolean alphaOk = true;
        
        for(char wordPart : alphabet) {
            if(!vd.isWordPart(wordPart))
            {
                alphaOk = false;
            }
        }
        
        assertTrue(alphaOk);
        
        boolean numbOk = true;
        
        for(char number : numbers) {
            if(!vd.isWordPart(number))
            {
                numbOk = false;
            }
        }
        
        assertTrue(numbOk);
        
        boolean otherCharOk = true;
        
        for(char otherChar : otherChars) {
            if(!vd.isWordPart(otherChar))
            {
                otherCharOk = false;
            }
        }
        
        assertTrue(otherCharOk);
        
        // Some other tests which should return false
        assertFalse(vd.isWordPart('~'));
        assertFalse(vd.isWordPart('|'));
        assertFalse(vd.isWordPart('@'));
    }
    
    /**
     * A variable must start with a '$'.
     */
    @Test
    public void testIsWordStart() {
        VariableDetector vd = new VariableDetector();
        
        assertTrue(vd.isWordStart('$'));
        assertFalse(vd.isWordStart('j'));
    }
}
