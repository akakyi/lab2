package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.db.repositories.SchoolRepository;
import edu.lab.back.json.request.SchoolRequestJson;
import edu.lab.back.json.response.SchoolResponseJson;
import edu.lab.back.service.crud.SchoolCrudService;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SchoolCrudServiceImpl extends BaseCrudService<SchoolEntity, Long> implements SchoolCrudService {

    private final SchoolRepository schoolRepository;

    public SchoolCrudServiceImpl(@NonNull final SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    protected SchoolRepository getRepo() {
        return this.schoolRepository;
    }

    @Override
    protected Long getId(@NonNull final String idString) throws InvalidPayloadException {
        return this.getLongId(idString);
    }

    @Override
    public SchoolResponseJson getById(final String idStr) throws InvalidPayloadException {
        final SchoolEntity school = this.getEntityById(idStr);
        final SchoolResponseJson converted = SchoolResponseJson.convert(school);
        return converted;
    }

    @Override
    public List<SchoolResponseJson> getAll() {
        final Iterable<SchoolEntity> allSchools = this.getAllEntityes();
        final List<SchoolResponseJson> result = StreamSupport.stream(allSchools.spliterator(), false)
            .map(SchoolResponseJson::convert)
            .collect(Collectors.toList());

        return result;
    }

    @Override
    public SchoolResponseJson deleteById(@NonNull final String idString) throws InvalidPayloadException {
        final SchoolEntity deletedEntity = this.deleteEntityById(idString);

        final SchoolResponseJson result = SchoolResponseJson.convert(deletedEntity);
        return result;
    }

    @Override
    public SchoolResponseJson save(@NonNull final SchoolRequestJson schoolRequestJson) {
        final SchoolEntity entity = new SchoolEntity();
        entity.setId(schoolRequestJson.getId());
        entity.setName(schoolRequestJson.getName());

//        final CityEntity city = this.cityDao.getById(schoolRequestJson.getCityId(), CityEntity.class);
        final CityEntity city = new CityEntity();
        city.setId(schoolRequestJson.getCityId());
        entity.setCity(city);

        final SchoolEntity saved = this.schoolRepository.save(entity);
        final SchoolResponseJson savedJson = SchoolResponseJson.convert(saved);
        return savedJson;
    }

    @Override
    public SchoolResponseJson update(@NonNull final SchoolRequestJson schoolJson) {
        final Long schoolId = schoolJson.getId();
        final SchoolEntity school = this.getEntityById(schoolId);

        final CityEntity city = new CityEntity();
        city.setId(schoolJson.getCityId());
        school.setCity(city);

        school.setName(schoolJson.getName());

        final SchoolEntity updated = this.schoolRepository.save(school);
        final SchoolResponseJson updatedJson = SchoolResponseJson.convert(updated);
        return updatedJson;
    }

}
