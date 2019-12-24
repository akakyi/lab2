package edu.lab.back.service.crud;

import edu.lab.back.json.JsonPojo;
import edu.lab.back.util.exception.ResourceNotFound;

import java.util.List;

public interface BaseCrudService<RequestJsonType extends JsonPojo, ResponseJsonType extends JsonPojo, Id> {

    ResponseJsonType getById(Id id) throws ResourceNotFound;

    List<ResponseJsonType> getAll();

    ResponseJsonType deleteById(Id id) throws ResourceNotFound;

    ResponseJsonType save(RequestJsonType json);

    ResponseJsonType update(RequestJsonType json);

}
