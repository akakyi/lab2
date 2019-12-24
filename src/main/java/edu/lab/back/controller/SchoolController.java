package edu.lab.back.controller;

import edu.lab.back.json.request.SchoolRequestJson;
import edu.lab.back.json.response.SchoolResponseJson;
import edu.lab.back.service.crud.SchoolService;
import edu.lab.back.service.validator.SchoolValidator;
import edu.lab.back.util.UrlPatterns;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SchoolController.CONTROLLER_BASE_URL)
public class SchoolController {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/school";

    private final SchoolService schoolService;

    private final SchoolValidator validator;

    @Autowired
    public SchoolController(
        @NonNull final SchoolService schoolService,
        @NonNull final SchoolValidator validator
    )
    {
        this.schoolService = schoolService;
        this.validator = validator;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    protected SchoolResponseJson getSchool (
        @PathVariable("id") Long id
    ) throws InvalidPayloadException
    {
        final SchoolResponseJson school = this.schoolService.getById(id);

        return school;
    }

    @RequestMapping(method = RequestMethod.GET)
    protected SchoolResponseJson save(@RequestBody SchoolRequestJson schoolRequestJson) throws InvalidPayloadException {
            this.validator.validateSave(schoolRequestJson);
            final SchoolResponseJson saved = this.schoolService.save(schoolRequestJson);

            return saved;
    }

    @RequestMapping(method = RequestMethod.PUT)
    protected SchoolResponseJson update(
        @RequestBody SchoolRequestJson schoolRequestJson
    ) throws InvalidPayloadException
    {
            this.validator.validateUpdate(schoolRequestJson);
            final SchoolResponseJson updated = this.schoolService.update(schoolRequestJson);

            return updated;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    protected SchoolResponseJson delete(@PathVariable("id") Long id)
    {
            final SchoolResponseJson deleted = this.schoolService.deleteById(id);

            return deleted;
    }
}
