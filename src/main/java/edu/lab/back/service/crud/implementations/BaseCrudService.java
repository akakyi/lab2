package edu.lab.back.service.crud.implementations;

import edu.lab.back.util.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import edu.lab.back.util.exception.ResourceNotFound;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class BaseCrudService<Entity, Id> {

    protected abstract Id getId(String id) throws InvalidPayloadException;

    protected abstract CrudRepository<Entity, Id> getRepo();

    protected Entity getEntityById(@NonNull final String idString) throws InvalidPayloadException {
        final Id id = this.getId(idString);
        final Entity entity = this.getEntityById(id);

        return entity;
    }

    protected Entity getEntityById(@NonNull final Id id) {
        final Optional<Entity> entityOptional = this.getRepo().findById(id);
        final Entity entity = entityOptional.orElseThrow(ResourceNotFound::new);

        return entity;
    }

    protected Iterable<Entity> getAllEntityes() {
        final Iterable<Entity> allEntityes = this.getRepo().findAll();

        return allEntityes;
    }

    protected Entity deleteEntityById(@NonNull final String idString) throws InvalidPayloadException {
        final Id id = this.getId(idString);
        final CrudRepository<Entity, Id> repo = this.getRepo();

        final Entity entity = this.getEntityById(id);
        repo.delete(entity);

        return entity;
    }

    protected Long getLongId(@NonNull final String idString) throws InvalidPayloadException {
        try {
            final long id = Long.parseLong(idString);
            return id;
        } catch (NumberFormatException e) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_PATH_VARIABLE);
        }
    }

}
