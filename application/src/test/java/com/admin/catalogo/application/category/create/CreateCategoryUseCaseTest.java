package com.admin.catalogo.application.category.create;

import com.admin.catalogo.domain.category.CategoryGateway;
import com.admin.catalogo.domain.exception.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @InjectMocks
    private DefaultCreateCategoryUseCase useCase;
    @Mock
    private CategoryGateway categoryGateway;

    @Test
    public void testeCase1() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        final CategoryGateway categoryGateway = mock(CategoryGateway.class);
        when(categoryGateway.create(any())).thenAnswer( returnsFirstArg());

        final var useCase = new DefaultCreateCategoryUseCase(categoryGateway);
        final var actual = useCase.execute(aCommand);

        assertNotNull(actual);
        assertNotNull(actual.id());

        verify(categoryGateway, times(1))
                .create(argThat( c -> Objects.equals(expectedName, c.getName())
                            && Objects.equals(expectedDescription, c.getDescription())
                            && Objects.equals(expectedIsActive, c.isActive())
                            && Objects.nonNull(c.getId())
                            && Objects.nonNull(c.getCreatedAt())
                            && Objects.nonNull(c.getUpdatedAt())
                            && Objects.isNull(c.getDeletedAt())
                ));
    }

    @Test
    public void testeCase2() {
        final String expectedName = null;
        final var expectedDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);
        final var actualException = Assertions.assertThrows(DomainException.class, ()-> useCase.execute(aCommand));

        //Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(categoryGateway, times(expectedErrorCount)).create(any());
    }

    @Test
    public void testeCase3() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A Categoria mais assistida";
        final var expectedIsActive = false;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any())).thenAnswer( returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand);

        assertNotNull(actualOutput);
        assertNotNull(actualOutput.id());

        verify(categoryGateway, times(1))
                .create(argThat( c -> Objects.equals(expectedName, c.getName())
                        && Objects.equals(expectedDescription, c.getDescription())
                        && Objects.equals(expectedIsActive, c.isActive())
                        && Objects.nonNull(c.getId())
                        && Objects.nonNull(c.getCreatedAt())
                        && Objects.nonNull(c.getUpdatedAt())
                        && Objects.nonNull(c.getDeletedAt())
                ));
    }

    @Test
    public void testeCase4() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "Gateway Error";

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        verify(categoryGateway, times(1))
                .create(argThat( c -> Objects.equals(expectedName, c.getName())
                        && Objects.equals(expectedDescription, c.getDescription())
                        && Objects.equals(expectedIsActive, c.isActive())
                        && Objects.nonNull(c.getId())
                        && Objects.nonNull(c.getCreatedAt())
                        && Objects.nonNull(c.getUpdatedAt())
                        && Objects.isNull(c.getDeletedAt())
                ));

    }
}
