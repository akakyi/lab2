package edu.lab.back.service.crud;

import edu.lab.back.json.JsonPojo;
import edu.lab.back.util.exception.InvalidPayloadException;
import edu.lab.back.util.exception.ResourceNotFound;

import java.util.List;

public interface BaseCrudService<RequestJsonType extends JsonPojo, ResponseJsonType extends JsonPojo> {

    ResponseJsonType getById(String idString) throws InvalidPayloadException, ResourceNotFound;

    List<ResponseJsonType> getAll();

    ResponseJsonType deleteById(String idString) throws InvalidPayloadException, ResourceNotFound;

    ResponseJsonType save(RequestJsonType cityJson);

    ResponseJsonType update(RequestJsonType cityJson);

}
