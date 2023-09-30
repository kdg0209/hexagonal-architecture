package org.example.buckpal.common;

import javax.validation.*;
import java.util.Set;

public abstract class SelfValidating<T> {

    private Validator validator;

    public SelfValidating() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    protected void validateSelf() {
        Set<ConstraintViolation<T>> validations = validator.validate((T) this);

        if (!validations.isEmpty()) {
            throw new ConstraintViolationException(validations);
        }
    }
}
