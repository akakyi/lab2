package edu.lab.back.service.validator.implementations;

import edu.lab.back.db.dao.CityDao;
import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.json.request.SchoolRequestJson;
import edu.lab.back.service.validator.SchoolValidator;
import edu.lab.back.util.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SchoolValidatorImpl implements SchoolValidator {

    private final CityDao cityDao;

    @Inject
    public SchoolValidatorImpl(@NonNull final CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public void validateSave(final SchoolRequestJson requestJson) throws InvalidPayloadException {
        if (requestJson == null) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        }
        if (requestJson.getId() != null) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        }

        this.baseValidate(requestJson);
    }

    @Override
    public void validateUpdate(final SchoolRequestJson requestJson) throws InvalidPayloadException {
        if (requestJson == null) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        }
        if (requestJson.getId() == null) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        }

        this.baseValidate(requestJson);
    }

    private void baseValidate(@NonNull final SchoolRequestJson requestJson) throws InvalidPayloadException {
        final Long cityId = requestJson.getCityId();
        if (cityId == null) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        }
        final CityEntity city = this.cityDao.getById(cityId, CityEntity.class);
        if (city == null) {
            throw new InvalidPayloadException(ValidationMessages.REFERRED_ENTITY_NOT_EXIST);
        }

        if (requestJson.getName() == null) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        } else if (requestJson.getName().equals("")) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        }
    }

}
