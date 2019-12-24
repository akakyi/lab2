package edu.lab.back.service.validator;

import edu.lab.back.json.ProfileTypeJson;
import edu.lab.back.util.exception.InvalidPayloadException;

public interface ProfileTypeValidator {

    void validateSave(ProfileTypeJson profileTypeJson) throws InvalidPayloadException;

    void validateUpdate(ProfileTypeJson profileTypeJson) throws InvalidPayloadException;

}
