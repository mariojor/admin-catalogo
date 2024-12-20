package com.admin.catalogo.domain.category;

import com.admin.catalogo.domain.AggregatorRoot;
import com.admin.catalogo.domain.validation.ValidationHandler;

import java.time.Instant;

public class Category extends AggregatorRoot<CategoryID> {
   private String name;
   private String description;
   private boolean isActive;
   private Instant createdAt;
   private Instant updatedAt;
   private Instant deletedAt;

   private Category(final CategoryID anId,
                    final String aName,
                    final String aDescription,
                    final boolean isActive,
                    final Instant aCreatedAt,
                    final Instant aUpdatedAt,
                    final Instant aDeletedAt) {
      super(anId);
      this.name = aName;
      this.description = aDescription;
      this.isActive = isActive;
      this.createdAt = aCreatedAt;
      this.updatedAt = aUpdatedAt;
      this.deletedAt = aDeletedAt;
   }

    public static Category newCategory(final String name, final String description, final boolean isActive) {
        return new Category(CategoryID.unique(), name, description, isActive, Instant.now(), Instant.now(), isActive? null : Instant.now());
    }

   @Override
   public void validate(final ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
   }

   public Category deactivate() {
      if(getDeletedAt() == null) {
         this.deletedAt = Instant.now();
      }
      this.isActive = false;
      this.updatedAt = Instant.now();

      return this;
   }

   public Category activate() {
      this.deletedAt = null;
      this.isActive = true;
      this.updatedAt = Instant.now();

      return this;
   }

    public Category update(final String name, final String description, final boolean isActive) {
        if(isActive) {
           activate();
        }else {
           deactivate();
        }

       this.name = name;
       this.description = description;
       this.updatedAt = Instant.now();
        return this;
    }

   public CategoryID getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public boolean isActive() {
      return isActive;
   }

   public Instant getCreatedAt() {
      return createdAt;
   }

   public Instant getUpdatedAt() {
      return updatedAt;
   }

   public Instant getDeletedAt() {
      return deletedAt;
   }
}