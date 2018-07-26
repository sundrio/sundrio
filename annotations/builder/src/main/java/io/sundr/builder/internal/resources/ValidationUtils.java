/*
 *      Copyright 2018 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.builder.internal.resources;

import io.sundr.resourcecify.annotations.Resourcecify;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Resourcecify
public final class ValidationUtils {

    private static final Object LOCK = new Object();
    private static Validator validator;

    private static Validator createValidator() {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            return factory.getValidator();
        } catch (ValidationException e) {
            return null;
        }
    }

    private static Validator getValidator() {
        Validator v = validator;
        if (v == null) {
            synchronized (LOCK) {
                v = validator;
                if (validator == null) {
                    v = createValidator();
                    validator = v;
                }
            }
        }
        return v;
    }

    public static <T> void validate(T item) {
        validate(item, getValidator());
    }

    public static <T> void validate(T item, Validator v) {
        if (v == null) {
            v = getValidator();
        }
        if (v == null) {
           return;
        }
        Set<ConstraintViolation<T>> violations = v.validate(item);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder("Constraint Validations: ");
            boolean first = true;
            for (ConstraintViolation violation : violations) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }
                Object leafBean = violation.getLeafBean();
                sb.append(violation.getPropertyPath() + " " + violation.getMessage() + " on bean: " + leafBean);
            }
            throw new ConstraintViolationException(sb.toString(), violations);
        }
    }
}

