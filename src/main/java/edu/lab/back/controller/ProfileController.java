package edu.lab.back.controller;

import edu.lab.back.json.request.ProfileRequestJson;
import edu.lab.back.json.response.ProfileResponseJson;
import edu.lab.back.service.crud.ProfileService;
import edu.lab.back.service.validator.ProfileValidator;
import edu.lab.back.util.UrlPatterns;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(ProfileController.CONTROLLER_BASE_URL)
public class ProfileController {

    public static final String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/profile";

    @NonNull
    private final ProfileService profileService;

    @NotNull
    private final ProfileValidator profileValidator;

    @Autowired
    public ProfileController(
        @NonNull final ProfileService profileService,
        @NonNull final ProfileValidator profileValidator
    )
    {
        this.profileService = profileService;
        this.profileValidator = profileValidator;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProfileResponseJson getProfile(
        @PathVariable("id") Long id
    ) throws InvalidPayloadException
    {
        final ProfileResponseJson profile = this.profileService.getById(id);

        return profile;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ProfileResponseJson> getAllProfiles()
    {
        final List<ProfileResponseJson> profiles = this.profileService.getAll();

        return profiles;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ProfileResponseJson save(@RequestBody ProfileRequestJson profileJson) throws InvalidPayloadException
    {
            this.profileValidator.validateSave(profileJson);
            final ProfileResponseJson saved = this.profileService.save(profileJson);

            return saved;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ProfileResponseJson update(@RequestBody ProfileRequestJson profileJson) throws InvalidPayloadException {
            this.profileValidator.validateUpdate(profileJson);
            final ProfileResponseJson updated = this.profileService.update(profileJson);

            return updated;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ProfileResponseJson delete(@PathVariable("id") Long id) throws InvalidPayloadException
    {
            final ProfileResponseJson deleted = this.profileService.deleteById(id);

            return deleted;
    }
}
