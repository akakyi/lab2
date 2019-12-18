package edu.lab.back.service.crud;

import edu.lab.back.json.request.ProfileRequestJson;
import edu.lab.back.json.response.ProfileResponseJson;
import edu.lab.back.util.exception.InvalidPayloadException;

import java.util.List;

public interface ProfileService extends BaseCrudService<ProfileRequestJson, ProfileResponseJson> {

    List<ProfileResponseJson> getProfileBySchoolId(String schoolId) throws InvalidPayloadException;

}
