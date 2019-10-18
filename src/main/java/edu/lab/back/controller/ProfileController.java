package edu.lab.back.controller;

import edu.lab.back.json.request.ProfileRequestJson;
import edu.lab.back.json.response.ProfileResponseJson;
import edu.lab.back.service.crud.ProfileCrudService;
import edu.lab.back.service.validator.ProfileValidator;
import edu.lab.back.util.UrlPatterns;
import edu.lab.back.util.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {
    ProfileController.CONTROLLER_BASE_URL
})
@NoArgsConstructor
public class ProfileController extends BaseHttpServlet {

    public static final String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/profile";

    private static final String ID_PATH_VARIABLE_NAME = "id";

    @Inject
    private ProfileCrudService profileCrudService;

    @Inject
    private ProfileValidator profileValidator;

    @Override
    protected void doGet(
        @NonNull final HttpServletRequest req,
        @NonNull final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String idString = req.getParameter(ID_PATH_VARIABLE_NAME);

        if (idString != null) {
            try {
                final ProfileResponseJson profile = this.profileCrudService.getById(idString);
                this.writeStringResult(profile.toJsonString(), resp);
            } catch (InvalidPayloadException e) {
                this.writeValidationError(e.getMessage(), resp);
            }
        } else {
            final List<ProfileResponseJson> allProfiles = this.profileCrudService.getAll();
            this.writeResult(allProfiles, resp);
        }
    }

    @Override
    protected void doPost(
        @NonNull final HttpServletRequest req,
        @NonNull final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        try {
            final ProfileRequestJson profileJson = this.readRequest(req, ProfileRequestJson.class);
            this.profileValidator.validateSave(profileJson);
            final ProfileResponseJson saved = this.profileCrudService.save(profileJson);
            this.writeStringResult(saved.toJsonString(), resp);
        } catch (IOException e) {
            this.writeValidationError(ValidationMessages.INVALID_REQUEST_JSON, resp);
        } catch (InvalidPayloadException e) {
            this.writeValidationError(e.getMessage(), resp);
        }
    }

    @Override
    protected void doPut(
        @NonNull final HttpServletRequest req,
        @NonNull final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        try {
            final ProfileRequestJson profileJson = this.readRequest(req, ProfileRequestJson.class);
            this.profileValidator.validateUpdate(profileJson);
            final ProfileResponseJson updated = this.profileCrudService.update(profileJson);
            this.writeStringResult(updated.toJsonString(), resp);
        } catch (IOException e) {
            this.writeValidationError(ValidationMessages.INVALID_REQUEST_JSON, resp);
        } catch (InvalidPayloadException e) {
            this.writeValidationError(e.getMessage(), resp);
        }
    }

    @Override
    protected void doDelete(
        @NonNull final HttpServletRequest req,
        @NonNull final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String idString = req.getParameter(ID_PATH_VARIABLE_NAME);
        try {
            final ProfileResponseJson deleted = this.profileCrudService.deleteById(idString);
            this.writeStringResult(deleted.toJsonString(), resp);
        } catch (InvalidPayloadException e) {
            this.writeValidationError(e.getMessage(), resp);
        }
    }
}
