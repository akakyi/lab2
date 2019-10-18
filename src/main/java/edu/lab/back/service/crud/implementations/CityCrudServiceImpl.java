package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.dao.CityDao;
import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.json.request.CityRequestJson;
import edu.lab.back.json.response.CityResponseJson;
import edu.lab.back.service.crud.CityCrudService;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Transactional(rollbackOn = Exception.class)
public class CityCrudServiceImpl extends BaseCrudService implements CityCrudService {

    private final CityDao cityDao;

    @Inject
    public CityCrudServiceImpl(@NonNull final CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public CityResponseJson getById(@NonNull final String idString) throws InvalidPayloadException {
        final Long id = this.getId(idString);
        final CityEntity city = this.cityDao.getById(id, CityEntity.class);
        final CityResponseJson cityResponseJson = CityResponseJson.convert(city);

        return cityResponseJson;
    }

    @Override
    public List<CityResponseJson> getAll() {
        final List<CityEntity> allCities = this.cityDao.getAll(CityEntity.class);
        final List<CityResponseJson> allCitiesJson = allCities
            .stream()
            .map(CityResponseJson::convert)
            .collect(Collectors.toList());
        return allCitiesJson;
    }

    @Override
    public CityResponseJson deleteById(@NonNull final String idString) throws InvalidPayloadException {
        final Long id = this.getId(idString);
        final CityEntity deletedEntity = this.cityDao.deleteById(id, CityEntity.class);
        final CityResponseJson deletedJson = CityResponseJson.convert(deletedEntity);

        return deletedJson;
    }

    @Override
    public CityResponseJson save(@NonNull final CityRequestJson cityJson) {
        final CityEntity cityEntity = CityEntity.convert(cityJson);

        final CityEntity saved = this.cityDao.save(cityEntity);
        final CityResponseJson result = CityResponseJson.convert(saved);

        return result;
    }

    @Override
    public CityResponseJson update(@NonNull final CityRequestJson cityJson) {
        final Long cityId = cityJson.getId();
        final CityEntity entity = this.cityDao.getById(cityId, CityEntity.class);

        entity.setName(cityJson.getName());

        final CityEntity added = this.cityDao.update(entity);
        final CityResponseJson result = CityResponseJson.convert(added);

        return result;
    }

}
