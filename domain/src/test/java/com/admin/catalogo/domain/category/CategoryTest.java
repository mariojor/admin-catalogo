package com.admin.catalogo.domain.category;

import com.admin.catalogo.domain.exception.DomainException;
import com.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void testeCase1() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var atualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(atualCategory);
        Assertions.assertNotNull(atualCategory.getId());
        Assertions.assertEquals(expectedName, atualCategory.getName());
        Assertions.assertEquals(expectedDescription, atualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, atualCategory.isActive());
        Assertions.assertNotNull(atualCategory.getCreatedAt());
        Assertions.assertNotNull(atualCategory.getUpdatedAt());
        Assertions.assertNull(atualCategory.getDeletedAt());
    }

    @Test
    public void testeCase2() {
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var atualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var atualException = Assertions.assertThrows(DomainException.class, () -> {
            atualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(expectedErrorCount, atualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, atualException.getErrors().get(0).message());
    }

    @Test
    public void testeCase3() {
        final String expectedName = " ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var atualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var atualException = Assertions.assertThrows(DomainException.class, () -> {
            atualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(expectedErrorCount, atualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, atualException.getErrors().get(0).message());
    }

    @Test
    public void testeCase4() {
        final String expectedName = "Fi ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var atualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var atualException = Assertions.assertThrows(DomainException.class, () -> {
            atualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(expectedErrorCount, atualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, atualException.getErrors().get(0).message());
    }

    @Test
    public void testeCase5() {
        final String expectedName = """
                Aluno é uma Entidade porque cada aluno tem um id único.
                Email é um Value Object porque não importa qual email é, só o valor dele é relevante.
                AlunoRepository é o lugar onde os alunos são guardados (como uma "caixa" de alunos).
                cada aluno tem um id único.
                Email é um Value Object porque não importa qual email é, só o valor dele é relevante.
                AlunoRepository é o lugar onde os alunos são guardados (como uma "caixa" de alunos)
                """;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var atualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var atualException = Assertions.assertThrows(DomainException.class, () -> {
            atualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(expectedErrorCount, atualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, atualException.getErrors().get(0).message());
    }

    @Test
    public void testeCase6() {
        final String expectedName = "Filmes";
        final var expectedDescription = " ";
        final var expectedIsActive = true;

        final var atualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow( () -> {
            atualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertNotNull(atualCategory);
        Assertions.assertNotNull(atualCategory.getId());
        Assertions.assertEquals(expectedName, atualCategory.getName());
        Assertions.assertEquals(expectedDescription, atualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, atualCategory.isActive());
        Assertions.assertNotNull(atualCategory.getCreatedAt());
        Assertions.assertNotNull(atualCategory.getUpdatedAt());
        Assertions.assertNull(atualCategory.getDeletedAt());

    }

    @Test
    public void testeCase7() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var atualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow( () -> {
            atualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertNotNull(atualCategory);
        Assertions.assertNotNull(atualCategory.getId());
        Assertions.assertEquals(expectedName, atualCategory.getName());
        Assertions.assertEquals(expectedDescription, atualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, atualCategory.isActive());
        Assertions.assertNotNull(atualCategory.getCreatedAt());
        Assertions.assertNotNull(atualCategory.getUpdatedAt());
        Assertions.assertNotNull(atualCategory.getDeletedAt());

    }

    @Test
    public void testeCase8() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, true);

        Assertions.assertDoesNotThrow( () -> {
            aCategory.validate(new ThrowsValidationHandler());
        });

        final var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var createdAt = aCategory.getCreatedAt();
        final var actualCategory = aCategory.deactivate();

        Assertions.assertDoesNotThrow( () -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(aCategory.getId(),  actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(createdAt ,actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());

    }

    @Test
    public void testeCase9() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, false);

        Assertions.assertDoesNotThrow( () -> {
            aCategory.validate(new ThrowsValidationHandler());
        });

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getDeletedAt());

        final var actualCategory = aCategory.activate();

        Assertions.assertDoesNotThrow( () -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(aCategory.getId(),  actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(createdAt ,actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());

    }

    @Test
    public void testeCase10() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory("Film", "A Categoria", expectedIsActive);

        Assertions.assertDoesNotThrow( () -> {
            aCategory.validate(new ThrowsValidationHandler());
        });

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow( () -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(aCategory.getId(),  actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(createdAt ,actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());

    }

    @Test
    public void testeCase11() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var aCategory = Category.newCategory("Film", "A Categoria", true);

        Assertions.assertDoesNotThrow( () -> {aCategory.validate(new ThrowsValidationHandler());});
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);
        Assertions.assertDoesNotThrow( () -> {actualCategory.validate(new ThrowsValidationHandler());});

        Assertions.assertEquals(aCategory.getId(),  actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertEquals(createdAt ,actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(aCategory.getDeletedAt());

    }

    @Test
    public void testeCase12() {
        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory("Filmes", "A Categoria", expectedIsActive);

        Assertions.assertDoesNotThrow( () -> {aCategory.validate(new ThrowsValidationHandler());});

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertEquals(aCategory.getId(),  actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertEquals(createdAt ,actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(aCategory.getDeletedAt());

    }
}