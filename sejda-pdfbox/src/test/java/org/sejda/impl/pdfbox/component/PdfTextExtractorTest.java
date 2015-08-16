/*
 * Created on 25/ago/2011
 * Copyright 2011 by Andrea Vacondio (andrea.vacondio@gmail.com).
 * 
 * This file is part of the Sejda source code
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sejda.impl.pdfbox.component;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.internal.matchers.Contains;
import org.mockito.internal.matchers.StartsWith;
import org.sejda.model.exception.TaskException;

/**
 * @author Andrea Vacondio
 * 
 */
public class PdfTextExtractorTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();
    private PdfTextExtractor victim;
    private PDDocument doc;
    private File file;

    @Before
    public void setUp() throws TaskException {
        victim = new PdfTextExtractor("UTF-8");
        doc = mock(PDDocument.class);
        file = mock(File.class);
    }

    @Test
    public void testNullDocExtract() throws TaskException {
        expected.expectMessage(new Contains("Unable to extract text from a null document."));
        victim.extract(null, null);
    }

    @Test
    public void testNullFileExtract() throws TaskException {
        expected.expectMessage(new StartsWith("Cannot write extracted text"));
        victim.extract(doc, null);
    }

    @Test
    public void testNotFileExtract() throws TaskException {
        expected.expectMessage(new StartsWith("Cannot write extracted text"));
        when(file.isFile()).thenReturn(Boolean.FALSE);
        victim.extract(doc, file);
    }

    @Test
    public void testCannotWriteFileExtract() throws TaskException {
        expected.expectMessage(new StartsWith("Cannot write extracted text"));
        when(file.isFile()).thenReturn(Boolean.TRUE);
        when(file.canWrite()).thenReturn(Boolean.FALSE);
        victim.extract(doc, file);
    }
}
