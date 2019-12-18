package edu.lab.back.controller;

import edu.lab.back.json.request.CityRequestJson;
import edu.lab.back.json.response.CityResponseJson;
import edu.lab.back.service.crud.CityCrudService;
import edu.lab.back.service.validator.CityValidator;
import edu.lab.back.util.UrlPatterns;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(CityController.CONTROLLER_BASE_URL)
public class CityController {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/city";

    private static final String CITY_PARAM_NAME = "city";

    private static final String ALL_CITYES_PARAM_NAME = "all_cityes";

    private final CityCrudService cityCrudService;

    private final CityValidator validator;

    @Autowired
    public CityController(@NonNull final CityCrudService cityCrudService, @NonNull final CityValidator validator) {
        this.cityCrudService = cityCrudService;
        this.validator = validator;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CityResponseJson getCity (@PathParam("id") String idString) throws InvalidPayloadException
    {
        final CityResponseJson city = this.cityCrudService.getById(idString);
        return city;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CityResponseJson> getAllCityes ()
    {
        final List<CityResponseJson> cityes = this.cityCrudService.getAll();
        return cityes;
    }

    @RequestMapping(method = RequestMethod.POST)
    public CityResponseJson save(@RequestBody CityRequestJson cityJson) throws InvalidPayloadException {
        this.validator.validateSave(cityJson);
        final CityResponseJson saved = this.cityCrudService.save(cityJson);

        return saved;
    }

    @RequestMapping(method = RequestMethod.PUT)
    protected CityResponseJson udpate(@RequestBody CityRequestJson cityJson) throws InvalidPayloadException {
        this.validator.validateUpdate(cityJson);
        final CityResponseJson updated = this.cityCrudService.update(cityJson);

        return updated;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    protected CityResponseJson delete(@PathParam("id") String idString) throws InvalidPayloadException {
        final CityResponseJson deleted = this.cityCrudService.deleteById(idString);

        return deleted;
    }

}
