package com.admin.catalogo.application;

import com.admin.catalogo.domain.category.Category;

public  abstract class UseCase<OUT, IN> {

    public abstract OUT execute(IN input);
}