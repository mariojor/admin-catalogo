package com.admin.catalogo.domain.validation.handler;

import com.admin.catalogo.domain.exception.DomainException;
import com.admin.catalogo.domain.validation.Error;
import com.admin.catalogo.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {


    private final List<Error> erros;

    private Notification(final List<Error> erros) {
        this.erros = erros;
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }

    public static Notification create(final Error erros) {
        return new Notification(new ArrayList<>()).append(erros);
    }

    public static Notification create(final Throwable t) {
        return create(new Error(t.getMessage()));
    }


    @Override
    public Notification append(Error anError) {
        this.erros.add(anError);
        return this;
    }

    @Override
    public Notification append(final ValidationHandler anHandler) {
        this.erros.addAll(anHandler.getErrors());
        return this;
    }

    @Override
    public Notification validate(Validation aValidation) {
        try{
            aValidation.validate();
        }catch (final DomainException e){
            this.erros.addAll(e.getErrors());
        }catch (final Throwable e){
            this.erros.add(new Error(e.getMessage()));
        }
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return this.erros;
    }
}
