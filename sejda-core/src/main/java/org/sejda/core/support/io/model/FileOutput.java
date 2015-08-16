/*
 * Created on 19/giu/2010
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
package org.sejda.core.support.io.model;

import java.io.File;

/**
 * Holds informations about an output file generated by a task. When fully populated holds a reference to the temporary file created by the task and information about the new name
 * for the temporary file. Usage:
 * 
 * <pre>
 * {
 *     PopulatedFileOutput out = file(tmpFile).name(outName);
 * }
 * </pre>
 * 
 * @author Andrea Vacondio
 * 
 */
public final class FileOutput implements OngoingFileOuputCreation, PopulatedFileOutput {

    private File file;
    private String name;

    private FileOutput(File file) {
        this.file = file;
    }

    /**
     * Entry point to populate the {@link FileOutput}
     * 
     * @param file
     * @return the not fully populated instance
     */
    public static OngoingFileOuputCreation file(File file) {
        return new FileOutput(file);
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public PopulatedFileOutput name(String name) {
        this.name = name;
        return this;
    }
}
