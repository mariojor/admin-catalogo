package com.admin.catalogo.application;

import com.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {


    @Test
    public void testeCase1() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        final CategoryGateway categoryGateway = Mockito.mock(CategoryGateway.class);
        Mockito.when(categoryGateway.create(Mockito.any())).thenAnswer( returnsFirstArg());

        final var useCase = new CreateCategoryUseCase(categoryGateway);
        final var actual = useCase.execute(aCommand);

        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getId());

        Mockito.verify(categoryGateway, Mockito.times(1))
                .create(Mockito.argThat( c -> {
                            return Objects.equals(expectedName, c.getName())
                            && Objects.equals(expectedDescription, c.getDescription())
                            && Objects.equals(expectedIsActive, c.isActive())
                            && Objects.nonNull(c.getId())
                            && Objects.nonNull(c.getCreatedAt())
                            && Objects.nonNull(c.getUpdatedAt())
                            && Objects.isNull(c.getDeletedAt());
                    }
                ));
    }

}
