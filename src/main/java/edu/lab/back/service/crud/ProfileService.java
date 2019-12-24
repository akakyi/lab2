package edu.lab.back.service.crud;

import edu.lab.back.json.request.ProfileRequestJson;
import edu.lab.back.json.response.ProfileResponseJson;
import edu.lab.back.util.exception.InvalidPayloadException;

import java.util.List;

public interface ProfileService extends BaseCrudService<ProfileRequestJson, ProfileResponseJson, Long> {

    List<ProfileResponseJson> getProfileBySchoolId(Long schoolId) throws InvalidPayloadException;

}
