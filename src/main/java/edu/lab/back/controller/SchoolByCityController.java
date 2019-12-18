package edu.lab.back.controller;

import edu.lab.back.json.response.SchoolResponseJson;
import edu.lab.back.service.crud.SchoolService;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/school_of_city")
public class SchoolByCityController {

    private final SchoolService schoolService;

    public SchoolByCityController(@NonNull final SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    protected List<SchoolResponseJson> getSchoolsByCity (
        @PathParam("id") String idString
    ) throws InvalidPayloadException
    {
        final List<SchoolResponseJson> schools = this.schoolService.getSchoolsByCityId(idString);

        return schools;
    }

}
