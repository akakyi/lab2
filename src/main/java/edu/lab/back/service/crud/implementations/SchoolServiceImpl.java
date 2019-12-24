package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.db.repositories.SchoolRepository;
import edu.lab.back.json.request.SchoolRequestJson;
import edu.lab.back.json.response.SchoolResponseJson;
import edu.lab.back.service.crud.SchoolService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl extends BaseCrudService<SchoolEntity, Long> implements SchoolService {

    @NonNull
    private final SchoolRepository schoolRepository;

    @Override
    protected SchoolRepository getRepo() {
        return this.schoolRepository;
    }

    @Override
    public SchoolResponseJson getById(final Long id) {
        final SchoolEntity school = this.getEntityById(id);
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
    public SchoolResponseJson deleteById(@NonNull final Long id) {
        final SchoolEntity deletedEntity = this.deleteEntityById(id);

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

    @Override
    public List<SchoolResponseJson> getSchoolsByCityId(@NonNull final Long cityId) {
        final List<SchoolEntity> schoolsByCityId = this.schoolRepository.getSchoolsByCityId(cityId);
        final List<SchoolResponseJson> result = schoolsByCityId.stream()
            .map(SchoolResponseJson::convert)
            .collect(Collectors.toList());

        return result;
    }
}
