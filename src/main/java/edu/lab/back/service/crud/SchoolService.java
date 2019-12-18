package edu.lab.back.service.crud;

import edu.lab.back.json.request.SchoolRequestJson;
import edu.lab.back.json.response.SchoolResponseJson;
import edu.lab.back.util.exception.InvalidPayloadException;

import java.util.List;

public interface SchoolService extends BaseCrudService<SchoolRequestJson, SchoolResponseJson> {

    List<SchoolResponseJson> getSchoolsByCityId(String cityId) throws InvalidPayloadException;

}
