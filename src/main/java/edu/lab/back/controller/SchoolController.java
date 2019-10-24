package edu.lab.back.controller;

import edu.lab.back.json.request.SchoolRequestJson;
import edu.lab.back.json.response.SchoolResponseJson;
import edu.lab.back.service.crud.SchoolCrudService;
import edu.lab.back.service.validator.SchoolValidator;
import edu.lab.back.util.UrlPatterns;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping(SchoolController.CONTROLLER_BASE_URL)
public class SchoolController {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/school";

    private static final String SCHOOL_PARAM_NAME = "school";

    private static final String ALL_SCHOOLS_PARAM_NAME = "all_schools";

    private final SchoolCrudService schoolCrudService;

    private final SchoolValidator validator;

    public SchoolController(
        @NonNull final SchoolCrudService schoolCrudService,
        @NonNull final SchoolValidator validator
    )
    {
        this.schoolCrudService = schoolCrudService;
        this.validator = validator;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    protected SchoolResponseJson getSchool (
        @PathParam("id") String idString,
        ModelMap model
    ) throws InvalidPayloadException
    {
        final SchoolResponseJson school = this.schoolCrudService.getById(idString);
        model.addAttribute(SCHOOL_PARAM_NAME, school);

        return school;
    }

    @RequestMapping(method = RequestMethod.GET)
    protected SchoolResponseJson save(@RequestBody SchoolRequestJson schoolRequestJson) throws InvalidPayloadException {
            this.validator.validateSave(schoolRequestJson);
            final SchoolResponseJson saved = this.schoolCrudService.save(schoolRequestJson);

            return saved;
    }

    @RequestMapping(method = RequestMethod.PUT)
    protected SchoolResponseJson update(
        @RequestBody SchoolRequestJson schoolRequestJson
    ) throws InvalidPayloadException
    {
            this.validator.validateUpdate(schoolRequestJson);
            final SchoolResponseJson updated = this.schoolCrudService.update(schoolRequestJson);

            return updated;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    protected SchoolResponseJson delete(@PathParam("id") String idString) throws InvalidPayloadException
    {
            final SchoolResponseJson deleted = this.schoolCrudService.deleteById(idString);

            return deleted;
    }
}
