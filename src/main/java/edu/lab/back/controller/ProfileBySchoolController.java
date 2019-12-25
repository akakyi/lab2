package edu.lab.back.controller;

import edu.lab.back.json.response.ProfileResponseJson;
import edu.lab.back.service.crud.ProfileService;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/profiles_of_school")
public class ProfileBySchoolController {

    private final ProfileService profileService;

    @Autowired
    public ProfileBySchoolController(@NonNull final ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<ProfileResponseJson> getProfilesBySchool(
        @PathVariable("id") Long id
    ) throws InvalidPayloadException
    {
        final List<ProfileResponseJson> profiles = this.profileService.getProfileBySchoolId(id);

        return profiles;
    }

}
