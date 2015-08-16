/*
 * Created on 14/set/2011
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
package org.sejda.model.parameter.base;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.sejda.model.output.MultipleTaskOutput;

/**
 * Provides a skeletal implementation for parameter classes having multiple pdf source as input and generating multiple output.
 * 
 * @author Andrea Vacondio
 * 
 */
public class MultiplePdfSourceMultipleOutputParameters extends MultiplePdfSourceParameters implements
        MultipleOutputTaskParameters {

    private String outputPrefix = "";
    @Valid
    @NotNull
    private MultipleTaskOutput<?> output;

    public String getOutputPrefix() {
        return outputPrefix;
    }

    public void setOutputPrefix(String outputPrefix) {
        this.outputPrefix = outputPrefix;
    }

    public MultipleTaskOutput<?> getOutput() {
        return output;
    }

    public void setOutput(MultipleTaskOutput<?> output) {
        this.output = output;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(outputPrefix).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MultiplePdfSourceMultipleOutputParameters)) {
            return false;
        }
        MultiplePdfSourceMultipleOutputParameters parameter = (MultiplePdfSourceMultipleOutputParameters) other;
        return new EqualsBuilder().appendSuper(super.equals(other)).append(outputPrefix, parameter.getOutputPrefix())
                .isEquals();
    }

}
