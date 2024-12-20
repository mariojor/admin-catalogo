package com.admin.catalogo.domain;

import com.admin.catalogo.domain.validation.ValidationHandler;

public abstract class AggregatorRoot<ID extends Identifier > extends Entity<ID> {

    protected AggregatorRoot(final ID id) {
        super(id);
    }

}
