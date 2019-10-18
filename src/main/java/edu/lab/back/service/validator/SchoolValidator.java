package edu.lab.back.service.validator;

import edu.lab.back.json.request.SchoolRequestJson;
import edu.lab.back.util.exception.InvalidPayloadException;

public interface SchoolValidator {

    void validateSave(SchoolRequestJson requestJson) throws InvalidPayloadException;

    void validateUpdate(SchoolRequestJson requestJson) throws InvalidPayloadException;

}

