package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.entity.ProfileEntity;
import edu.lab.back.db.entity.ProfileTypeEntity;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.db.repositories.ProfileRepository;
import edu.lab.back.db.repositories.ProfileTypeRepository;
import edu.lab.back.json.request.ProfileRequestJson;
import edu.lab.back.json.response.ProfileResponseJson;
import edu.lab.back.service.crud.ProfileService;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl extends BaseCrudService<ProfileEntity, Long> implements ProfileService {

    @NonNull
    private final ProfileRepository profileRepository;

    @NonNull
    private final ProfileTypeRepository profileTypeRepository;

    @Override
    protected ProfileRepository getRepo() {
        return this.profileRepository;
    }

    @Override
    protected Long getId(@NonNull final String idString) throws InvalidPayloadException {
        return this.getLongId(idString);
    }

    @Override
    public ProfileResponseJson getById(@NonNull final String idString) throws InvalidPayloadException {
        final ProfileEntity profile = this.getEntityById(idString);

        final ProfileResponseJson converted = ProfileResponseJson.convert(profile);
        return converted;
    }

    @Override
    public List<ProfileResponseJson> getAll() {
        final Iterable<ProfileEntity> allProfiles = this.getAllEntityes();
        final List<ProfileResponseJson> result = StreamSupport.stream(allProfiles.spliterator(), false)
            .map(ProfileResponseJson::convert)
            .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return result;
    }

    @Override
    public ProfileResponseJson deleteById(@NonNull final String idString) throws InvalidPayloadException {
        final ProfileEntity deletedEntity = this.deleteEntityById(idString);

        final ProfileResponseJson result = ProfileResponseJson.convert(deletedEntity);
        return result;
    }

    @Override
    public ProfileResponseJson save(@NonNull final ProfileRequestJson profileJson) {
        final ProfileEntity entity = new ProfileEntity();
        this.fillEntity(entity, profileJson);

        final ProfileEntity saved = this.profileRepository.save(entity);
        final ProfileResponseJson savedJson = ProfileResponseJson.convert(saved);
        return savedJson;
    }

    @Override
    public ProfileResponseJson update(@NonNull final ProfileRequestJson profileJson) {
        final ProfileEntity entity = this.getEntityById(profileJson.getId());
        this.fillEntity(entity, profileJson);

        final ProfileEntity saved = this.profileRepository.save(entity);
        final ProfileResponseJson savedJson = ProfileResponseJson.convert(saved);
        return savedJson;
    }

    @Override
    public List<ProfileResponseJson> getProfileBySchoolId(final String schoolId) throws InvalidPayloadException {
        final Long id = this.getLongId(schoolId);
        final List<ProfileEntity> profilesBySchoolEntities = this.profileRepository.getProfilesBySchoolId(id);

        final List<ProfileResponseJson> result = profilesBySchoolEntities.stream()
            .map(ProfileResponseJson::convert)
            .collect(Collectors.toList());

        return result;
    }

    private void fillEntity(@NonNull final ProfileEntity entity, @NonNull final ProfileRequestJson profileJson) {
        entity.setName(profileJson.getName());
        entity.setClassLevel(profileJson.getClassLevel());
        entity.setAge(profileJson.getAge());

        final SchoolEntity school = new SchoolEntity();
        school.setId(profileJson.getSchoolId());
        entity.setSchool(school);

        final String profileTypeName = profileJson.getProfileType().getName();
        final ProfileTypeEntity profileType = this.profileTypeRepository.getByName(profileTypeName);
        entity.setProfileType(profileType);
    }
}
