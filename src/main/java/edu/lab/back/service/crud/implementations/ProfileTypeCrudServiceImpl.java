package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.entity.ProfileTypeEntity;
import edu.lab.back.db.repositories.ProfileTypeRepository;
import edu.lab.back.json.ProfileTypeJson;
import edu.lab.back.service.crud.ProfileTypeCrudService;
import edu.lab.back.util.exception.ResourceNotFound;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class ProfileTypeCrudServiceImpl
    extends BaseCrudService<ProfileTypeEntity, Integer>
    implements ProfileTypeCrudService
{

    @NonNull
    private final ProfileTypeRepository repository;

    @Override
    protected CrudRepository<ProfileTypeEntity, Integer> getRepo() {
        return this.repository;
    }

    @Override
    public ProfileTypeJson getById(final Integer id) throws ResourceNotFound {
        final ProfileTypeEntity entity = this.getEntityById(id);
        final ProfileTypeJson result = ProfileTypeJson.convert(entity);

        return result;
    }

    @Override
    public List<ProfileTypeJson> getAll() {
        final Iterable<ProfileTypeEntity> allEntityes = this.getAllEntityes();
        final List<ProfileTypeJson> result = StreamSupport.stream(allEntityes.spliterator(), false)
            .map(ProfileTypeJson::convert)
            .collect(Collectors.toList());

        return result;
    }

    @Override
    public ProfileTypeJson deleteById(final Integer id) throws ResourceNotFound {
        final ProfileTypeEntity deleted = this.deleteEntityById(id);
        final ProfileTypeJson result = ProfileTypeJson.convert(deleted);

        return result;
    }

    @Override
    public ProfileTypeJson save(final ProfileTypeJson typeJson) {
        final ProfileTypeEntity converted = ProfileTypeEntity.convert(typeJson);
        final ProfileTypeEntity saved = this.repository.save(converted);
        final ProfileTypeJson result = ProfileTypeJson.convert(saved);

        return result;
    }

    @Override
    public ProfileTypeJson update(final ProfileTypeJson cityJson) {
        final Integer id = cityJson.getId();
        final ProfileTypeEntity entity = this.getEntityById(id);

        entity.setName(cityJson.getName());
        final ProfileTypeEntity updated = this.repository.save(entity);
        final ProfileTypeJson result = ProfileTypeJson.convert(updated);

        return result;
    }
}
