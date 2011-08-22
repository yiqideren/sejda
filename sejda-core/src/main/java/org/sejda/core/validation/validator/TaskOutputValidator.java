/*
 * Created on 19/ago/2011
 * Copyright 2011 by Andrea Vacondio (andrea.vacondio@gmail.com).
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
package org.sejda.core.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.sejda.core.manipulation.model.output.OutputType;
import org.sejda.core.manipulation.model.output.TaskOutput;
import org.sejda.core.validation.constraint.ValidTaskOutput;

/**
 * Validates that the type of the given task output is of the expected type.
 * 
 * @author Andrea Vacondio
 * 
 */
public class TaskOutputValidator implements ConstraintValidator<ValidTaskOutput, TaskOutput> {

    private OutputType[] allowedTypes;

    public void initialize(ValidTaskOutput constraintAnnotation) {
        allowedTypes = constraintAnnotation.values();

    }

    public boolean isValid(TaskOutput value, ConstraintValidatorContext context) {
        if (allowedTypes != null && allowedTypes.length > 0 && value.getOutputType() != null) {
            for (OutputType current : allowedTypes) {
                if (value.getOutputType() == current) {
                    return true;
                }
            }
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    String.format("Task output type %s is not allowed", value.getOutputType())).addNode("parameters")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}