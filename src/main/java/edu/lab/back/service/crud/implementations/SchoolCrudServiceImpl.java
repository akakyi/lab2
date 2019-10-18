package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.dao.SchoolDao;
import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.json.request.SchoolRequestJson;
import edu.lab.back.json.response.SchoolResponseJson;
import edu.lab.back.service.crud.SchoolCrudService;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Transactional(rollbackOn = Exception.class)
public class SchoolCrudServiceImpl extends BaseCrudService implements SchoolCrudService {

    private final SchoolDao schoolDao;

    @Inject
    public SchoolCrudServiceImpl(@NonNull final SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public SchoolResponseJson getById(final String idStr) throws InvalidPayloadException {
        final Long id = this.getId(idStr);
        final SchoolEntity school = this.schoolDao.getById(id, SchoolEntity.class);
        final SchoolResponseJson converted = SchoolResponseJson.convert(school);
        return converted;
    }

    @Override
    public List<SchoolResponseJson> getAll() {
        final List<SchoolEntity> allSchools = this.schoolDao.getAll(SchoolEntity.class);
        final List<SchoolResponseJson> result = allSchools.stream()
            .map(SchoolResponseJson::convert)
            .collect(Collectors.toList());

        return result;
    }

    @Override
    public SchoolResponseJson deleteById(@NonNull final String idString) throws InvalidPayloadException {
        final Long id = this.getId(idString);
        final SchoolEntity deletedEntity = this.schoolDao.deleteById(id, SchoolEntity.class);

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

        final SchoolEntity saved = this.schoolDao.save(entity);
        final SchoolResponseJson savedJson = SchoolResponseJson.convert(saved);
        return savedJson;
    }

    @Override
    public SchoolResponseJson update(@NonNull final SchoolRequestJson schoolJson) {
        final Long schoolId = schoolJson.getId();
        final SchoolEntity school = this.schoolDao.getById(schoolId, SchoolEntity.class);

        final CityEntity city = new CityEntity();
        city.setId(schoolJson.getCityId());
        school.setCity(city);

        school.setName(schoolJson.getName());

        final SchoolEntity updated = this.schoolDao.update(school);
        final SchoolResponseJson updatedJson = SchoolResponseJson.convert(updated);
        return updatedJson;
    }

}
