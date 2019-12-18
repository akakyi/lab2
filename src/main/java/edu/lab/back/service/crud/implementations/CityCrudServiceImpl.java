package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.db.repositories.CityRepository;
import edu.lab.back.json.request.CityRequestJson;
import edu.lab.back.json.response.CityResponseJson;
import edu.lab.back.service.crud.CityCrudService;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class CityCrudServiceImpl extends BaseCrudService<CityEntity, Long> implements CityCrudService {

    @NonNull
    private final CityRepository cityRepository;

    @Override
    protected CityRepository getRepo() {
        return this.cityRepository;
    }

    @Override
    protected Long getId(@NonNull final String idString) throws InvalidPayloadException {
        return this.getLongId(idString);
    }

    @Override
    public CityResponseJson getById(@NonNull final String idString) throws InvalidPayloadException {
        final CityEntity city = this.getEntityById(idString);
        final CityResponseJson cityResponseJson = CityResponseJson.convert(city);

        return cityResponseJson;
    }

    @Override
    public List<CityResponseJson> getAll() {
        final Iterable<CityEntity> allCities = this.getAllEntityes();
        final List<CityResponseJson> allCitiesJson = StreamSupport.stream(allCities.spliterator(), false)
            .map(CityResponseJson::convert)
            .collect(Collectors.toList());

        return allCitiesJson;
    }

    @Override
    public CityResponseJson deleteById(@NonNull final String idString) throws InvalidPayloadException {
        final CityEntity city = this.deleteEntityById(idString);
        final CityResponseJson deletedJson = CityResponseJson.convert(city);

        return deletedJson;
    }

    @Override
    public CityResponseJson save(@NonNull final CityRequestJson cityJson) {
        final CityEntity cityEntity = CityEntity.convert(cityJson);

        final CityEntity saved = this.cityRepository.save(cityEntity);
        final CityResponseJson result = CityResponseJson.convert(saved);

        return result;
    }

    @Override
    public CityResponseJson update(@NonNull final CityRequestJson cityJson) {
        final Long cityId = cityJson.getId();
        final CityEntity entity = this.getEntityById(cityId);

        entity.setName(cityJson.getName());

        final CityEntity added = this.cityRepository.save(entity);
        final CityResponseJson result = CityResponseJson.convert(added);

        return result;
    }

}
