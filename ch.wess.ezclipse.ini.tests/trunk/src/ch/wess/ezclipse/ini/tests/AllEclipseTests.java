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
package ch.wess.ezclipse.ini.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ch.wess.ezclipse.ini.tests.editor.internal.partition.INIPartitionScannerEclipseTest;
import ch.wess.ezclipse.ini.tests.editor.internal.scanner.INICodeScannerEclipseTest;

/**
 * This class runs all the Eclipse tests. This means that the Eclipse GUI will
 * be launched.
 * 
 * @author Alain Sahli (a.sahli[at]wess.ch)
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    INIPartitionScannerEclipseTest.class,
    INICodeScannerEclipseTest.class
})
public class AllEclipseTests {
    // the class remains completely empty, being used only as a holder for the
    // above annotations. Thanks goes to
    // http://radio.javaranch.com/lasse/2006/07/27/1154024535662.html 
}
