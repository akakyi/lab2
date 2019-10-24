package edu.lab.back.controller;

import edu.lab.back.json.request.ProfileRequestJson;
import edu.lab.back.json.response.ProfileResponseJson;
import edu.lab.back.service.crud.ProfileCrudService;
import edu.lab.back.service.validator.ProfileValidator;
import edu.lab.back.util.UrlPatterns;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping(ProfileController.CONTROLLER_BASE_URL)
public class ProfileController {

    public static final String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/profile";

    private static final String PROFILE_PARAM_NAME = "profile";

    private static final String ALL_PROFILES_PARAM_NAME = "all_profiles";

    private final ProfileCrudService profileCrudService;

    private final ProfileValidator profileValidator;

    public ProfileController(
        @NonNull final ProfileCrudService profileCrudService,
        @NonNull final ProfileValidator profileValidator
    )
    {
        this.profileCrudService = profileCrudService;
        this.profileValidator = profileValidator;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    protected ProfileResponseJson getProfile(
        @PathParam("id") String idString,
        ModelMap model
    ) throws InvalidPayloadException
    {
        final ProfileResponseJson profile = this.profileCrudService.getById(idString);
        model.addAttribute(PROFILE_PARAM_NAME, profile);

        return profile;
    }

    @RequestMapping(method = RequestMethod.GET)
    protected List<ProfileResponseJson> getAllProfiles(ModelMap model) throws InvalidPayloadException
    {
        final List<ProfileResponseJson> profiles = this.profileCrudService.getAll();
        model.addAttribute(ALL_PROFILES_PARAM_NAME, profiles);

        return profiles;
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ProfileResponseJson save(@RequestBody ProfileRequestJson profileJson) throws InvalidPayloadException
    {
            this.profileValidator.validateSave(profileJson);
            final ProfileResponseJson saved = this.profileCrudService.save(profileJson);

            return saved;
    }

    @RequestMapping(method = RequestMethod.PUT)
    protected ProfileResponseJson update(@RequestBody ProfileRequestJson profileJson) throws InvalidPayloadException {
            this.profileValidator.validateUpdate(profileJson);
            final ProfileResponseJson updated = this.profileCrudService.update(profileJson);

            return updated;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    protected ProfileResponseJson delete(@PathParam("id") String idString) throws InvalidPayloadException
    {
            final ProfileResponseJson deleted = this.profileCrudService.deleteById(idString);

            return deleted;
    }
}
