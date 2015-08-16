/*
 * Created on 03/lug/2010
 *
 * Copyright 2010 by Andrea Vacondio (andrea.vacondio@gmail.com).
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
package org.sejda.core.support.prefix.processor;

import static org.junit.Assert.assertEquals;
import static org.sejda.core.support.prefix.model.NameGenerationRequest.nameRequest;

import org.junit.Test;

/**
 * Test unit for the {@link PrependPrefixProcessor}
 * 
 * @author Andrea Vacondio
 * 
 */
public class PrependPrefixProcessorTest {

    private PrependPrefixProcessor victim = new PrependPrefixProcessor();

    @Test
    public void testComplexProcess() {
        String prefix = "prefix_";
        String originalName = "name";
        String expected = "prefix_name";
        assertEquals(expected, victim.process(prefix, nameRequest().originalName(originalName)));
    }

    @Test
    public void testComplexProcessWithPageNumber() {
        String prefix = "prefix_";
        String originalName = "name";
        Integer page = Integer.valueOf("34");
        String expected = "34_prefix_name";
        assertEquals(expected, victim.process(prefix, nameRequest().originalName(originalName).page(page)));
    }
}
