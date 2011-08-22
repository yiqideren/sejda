/*
 * Created on 02/jul/2011
 *
 * Copyright 2010 by Andrea Vacondio (andrea.vacondio@gmail.com).
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.sejda.core.manipulation.model.parameter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.sejda.core.manipulation.model.output.TaskOutput;
import org.sejda.core.manipulation.model.pdf.transition.PdfPageTransition;
import org.sejda.core.validation.constraint.ValidSingleOutput;

/**
 * Parameter class for the set pages transition manipulation.
 * 
 * @author Andrea Vacondio
 * 
 */
// TODO verify one among transitions and defaultTransition is set
@ValidSingleOutput
public class SetPagesTransitionParameters extends SinglePdfSourceParameters implements SingleOutputDocumentParameter {

    @Valid
    private final Map<Integer, PdfPageTransition> transitions = new HashMap<Integer, PdfPageTransition>();
    @Valid
    private PdfPageTransition defaultTransition;
    private boolean fullScreen = false;
    private String outputName;
    @Valid
    @NotNull
    private TaskOutput output;

    public SetPagesTransitionParameters() {
        // no default transition
    }

    /**
     * @param defaultTransition
     *            the default transition
     * @param outputName
     *            to be used when the output is not a file destination
     */
    public SetPagesTransitionParameters(PdfPageTransition defaultTransition, String outputName) {
        this.defaultTransition = defaultTransition;
        this.outputName = outputName;
    }

    @Override
    public TaskOutput getOutput() {
        return output;
    }

    @Override
    public void setOutput(TaskOutput output) {
        this.output = output;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public String getOutputName() {
        return outputName;
    }

    /**
     * Associates the given transition to the given page number. If a transition was already associated to the given page, it is replaced with the new one.
     * 
     * @param page
     * @param transition
     * @return the previously associated transition or null.
     */
    public PdfPageTransition putTransition(Integer page, PdfPageTransition transition) {
        return transitions.put(page, transition);
    }

    /**
     * Clears the collection of transitions stored in this parameter instance.
     */
    public void clearTransitions() {
        transitions.clear();
    }

    /**
     * @return an unmodifiable view of the transitions in this parameter.
     */
    public Map<Integer, PdfPageTransition> getTransitions() {
        return Collections.unmodifiableMap(transitions);
    }

    public PdfPageTransition getDefaultTransition() {
        return defaultTransition;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(transitions).append(defaultTransition)
                .append(fullScreen).append(outputName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SetPagesTransitionParameters)) {
            return false;
        }
        SetPagesTransitionParameters parameter = (SetPagesTransitionParameters) other;
        return new EqualsBuilder().appendSuper(super.equals(other)).append(transitions, parameter.getTransitions())
                .append(defaultTransition, parameter.getDefaultTransition())
                .append(fullScreen, parameter.isFullScreen()).append(outputName, parameter.getOutputName()).isEquals();
    }
}