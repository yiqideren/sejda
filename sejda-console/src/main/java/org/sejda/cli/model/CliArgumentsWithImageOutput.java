/*
 * Created on Oct 2, 2011
 * Copyright 2010 by Eduard Weissmann (edi.weissmann@gmail.com).
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
package org.sejda.cli.model;

import uk.co.flamingpenguin.jewel.cli.Option;

/**
 * Trait for cli tasks that output image files
 * 
 * @author Eduard Weissmann
 * 
 */
public interface CliArgumentsWithImageOutput extends TaskCliArguments {

    @Option(shortName = "r", description = "resolution in dpi. Default is 72 (optional)")
    int getResolution();

    boolean isResolution();

    @Option(shortName = "z", description = "zoom factor for the generated images (EX. 1.5 generates images with a 150% zoom factor). (optional)")
    float getUserZoom();

    boolean isUserZoom();
}
