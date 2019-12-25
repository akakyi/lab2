package edu.lab.back.controller;

import edu.lab.back.json.ProfileTypeJson;
import edu.lab.back.service.crud.ProfileTypeCrudService;
import edu.lab.back.util.UrlPatterns;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ProfileTypeController.CONTROLLER_BASE_URL)
@RequiredArgsConstructor
public class ProfileTypeController {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/profile_type";

    @NonNull
    private final ProfileTypeCrudService profileTypeCrudService;

    @RequestMapping(value = "/{id}")
    public ProfileTypeJson getById(
        @PathVariable(value = "id") final Integer id
    )
    {
        final ProfileTypeJson profileType = this.profileTypeCrudService.getById(id);

        return profileType;
    }

    @RequestMapping
    public List<ProfileTypeJson> getAll()
    {
        final List<ProfileTypeJson> profileTypes = this.profileTypeCrudService.getAll();

        return profileTypes;
    }

}
