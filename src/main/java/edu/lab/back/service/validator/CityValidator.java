package edu.lab.back.service.validator;

import edu.lab.back.json.request.CityRequestJson;
import edu.lab.back.util.exception.InvalidPayloadException;

public interface CityValidator {

    void validateSave(CityRequestJson requestJson) throws InvalidPayloadException;

    void validateUpdate(CityRequestJson requestJson) throws InvalidPayloadException;

}
