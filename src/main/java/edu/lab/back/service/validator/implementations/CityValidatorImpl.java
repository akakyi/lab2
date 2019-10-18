package edu.lab.back.service.validator.implementations;

import edu.lab.back.json.request.CityRequestJson;
import edu.lab.back.service.validator.CityValidator;
import edu.lab.back.util.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;

import javax.ejb.Stateless;

@Stateless
public class CityValidatorImpl implements CityValidator {

    @Override
    public void validateSave(final CityRequestJson requestJson) throws InvalidPayloadException {
        if (requestJson == null) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        }
        if (requestJson.getId() != null) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        }

        this.baseValidation(requestJson);
    }

    @Override
    public void validateUpdate(final CityRequestJson requestJson) throws InvalidPayloadException {
        if (requestJson == null) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        }
        if (requestJson.getId() == null) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        }

        this.baseValidation(requestJson);
    }

    private void baseValidation(@NonNull final CityRequestJson requestJson) throws InvalidPayloadException {
        if (requestJson.getName() == null) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        } else if (requestJson.getName().equals("")) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_REQUEST_JSON);
        }
    }
}
