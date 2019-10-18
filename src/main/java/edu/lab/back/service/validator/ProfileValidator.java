package edu.lab.back.service.validator;

import edu.lab.back.json.request.ProfileRequestJson;
import edu.lab.back.util.exception.InvalidPayloadException;

public interface ProfileValidator {

    void validateSave(ProfileRequestJson requestJson) throws InvalidPayloadException;

    void validateUpdate(ProfileRequestJson requestJson) throws InvalidPayloadException;

}
