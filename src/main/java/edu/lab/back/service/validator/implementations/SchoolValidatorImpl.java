package edu.lab.back.service.validator.implementations;

import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.db.repositories.CityRepository;
import edu.lab.back.json.request.SchoolRequestJson;
import edu.lab.back.service.validator.SchoolValidator;
import edu.lab.back.util.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SchoolValidatorImpl implements SchoolValidator {

    private final CityRepository cityRepository;

    public SchoolValidatorImpl(@NonNull final CityRepository cityRepository) {
        this.cityRepository = cityRepository;
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
        final Optional<CityEntity> city = this.cityRepository.findById(cityId);
        if (!city.isPresent()) {
            throw new InvalidPayloadException(ValidationMessages.REFERRED_ENTITY_NOT_EXIST);
        }

        if (requestJson.getName() == null) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        } else if (requestJson.getName().equals("")) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        }
    }

}
