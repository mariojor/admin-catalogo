package com.admin.catalogo.application.category.create;

import com.admin.catalogo.domain.category.Category;
import com.admin.catalogo.domain.category.CategoryGateway;
import com.admin.catalogo.domain.validation.handler.Notification;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateCategoryOutput execute(CreateCategoryCommand command) {

        final var notification = Notification.create();
        final var aCategory = Category.newCategory(command.name(), command.description(), command.isActive());
        aCategory.validate(notification);

        return CreateCategoryOutput.from(this.categoryGateway.create(aCategory));
    }

}
