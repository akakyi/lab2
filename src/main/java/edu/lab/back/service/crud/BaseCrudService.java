package edu.lab.back.service.crud;

import edu.lab.back.json.JsonPojo;
import edu.lab.back.util.exception.ResourceNotFoundException;

import java.util.List;

public interface BaseCrudService<RequestJsonType extends JsonPojo, ResponseJsonType extends JsonPojo, Id> {

    ResponseJsonType getById(Id id) throws ResourceNotFoundException;

    List<ResponseJsonType> getAll();

    ResponseJsonType deleteById(Id id) throws ResourceNotFoundException;

    ResponseJsonType save(RequestJsonType json);

    ResponseJsonType update(RequestJsonType json);

}
