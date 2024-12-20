package com.admin.catalogo.domain.validation.handler;

import com.admin.catalogo.domain.exception.DomainException;
import com.admin.catalogo.domain.validation.Error;
import com.admin.catalogo.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(ValidationHandler.Validation aValidation) {
        try {
            aValidation.validate();
        } catch (Exception e) {
            throw  DomainException.with(new Error(e.getMessage()));
        }
        return this;
    }

    @Override
    public boolean hasErrors() {
        return false;
    }

    @Override
    public List<Error> getErrors() {
        return null;
    }

    public interface Validation {
        boolean validate();
    }
}
