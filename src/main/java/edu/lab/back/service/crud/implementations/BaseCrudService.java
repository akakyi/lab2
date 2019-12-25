package edu.lab.back.service.crud.implementations;

import edu.lab.back.util.exception.ResourceNotFoundException;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class BaseCrudService<Entity, Id> {

    protected abstract CrudRepository<Entity, Id> getRepo();

    protected Entity getEntityById(@NonNull final Id id) {
        final Optional<Entity> entityOptional = this.getRepo().findById(id);
        final Entity entity = entityOptional.orElseThrow(ResourceNotFoundException::new);

        return entity;
    }

    protected Iterable<Entity> getAllEntityes() {
        final Iterable<Entity> allEntityes = this.getRepo().findAll();

        return allEntityes;
    }

    protected Entity deleteEntityById(@NonNull final Id id) {
        final CrudRepository<Entity, Id> repo = this.getRepo();

        final Entity entity = this.getEntityById(id);
        repo.delete(entity);

        return entity;
    }

}
