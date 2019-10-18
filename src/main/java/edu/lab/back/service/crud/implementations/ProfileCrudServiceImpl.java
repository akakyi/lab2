package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.dao.ProfileDao;
import edu.lab.back.db.dao.ProfileTypeDao;
import edu.lab.back.db.entity.ProfileEntity;
import edu.lab.back.db.entity.ProfileTypeEntity;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.json.request.ProfileRequestJson;
import edu.lab.back.json.response.ProfileResponseJson;
import edu.lab.back.service.crud.ProfileCrudService;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProfileCrudServiceImpl extends BaseCrudService implements ProfileCrudService {

    private final ProfileDao profileDao;

    private final ProfileTypeDao profileTypeDao;

    @Inject
    public ProfileCrudServiceImpl(@NonNull final ProfileDao profileDao, @NonNull final ProfileTypeDao profileTypeDao) {
        this.profileDao = profileDao;
        this.profileTypeDao = profileTypeDao;
    }

    @Override
    public ProfileResponseJson getById(@NonNull final String idString) throws InvalidPayloadException {
        final Long id = this.getId(idString);
        final ProfileEntity profile = this.profileDao.getById(id, ProfileEntity.class);
        final ProfileResponseJson converted = ProfileResponseJson.convert(profile);
        return converted;
    }

    @Override
    public List<ProfileResponseJson> getAll() {
        final List<ProfileEntity> allSchools = this.profileDao.getAll(ProfileEntity.class);
        final List<ProfileResponseJson> result = allSchools.stream()
            .map(ProfileResponseJson::convert)
            .collect(Collectors.toList());

        return result;
    }

    @Override
    public ProfileResponseJson deleteById(@NonNull final String idString) throws InvalidPayloadException {
        final Long id = this.getId(idString);
        final ProfileEntity deletedEntity = this.profileDao.deleteById(id, ProfileEntity.class);

        final ProfileResponseJson result = ProfileResponseJson.convert(deletedEntity);
        return result;
    }

    @Override
    public ProfileResponseJson save(@NonNull final ProfileRequestJson profileJson) {
        final ProfileEntity entity = new ProfileEntity();
        this.fillEntity(entity, profileJson);

        final ProfileEntity saved = this.profileDao.save(entity);
        final ProfileResponseJson savedJson = ProfileResponseJson.convert(saved);
        return savedJson;
    }

    @Override
    public ProfileResponseJson update(@NonNull final ProfileRequestJson profileJson) {
        final ProfileEntity entity = this.profileDao.getById(profileJson.getId(), ProfileEntity.class);
        this.fillEntity(entity, profileJson);

        final ProfileEntity saved = this.profileDao.save(entity);
        final ProfileResponseJson savedJson = ProfileResponseJson.convert(saved);
        return savedJson;
    }

    private void fillEntity(@NonNull final ProfileEntity entity, @NonNull final ProfileRequestJson profileJson) {
        entity.setName(profileJson.getName());
        entity.setClassLevel(profileJson.getClassLevel());
        entity.setAge(profileJson.getAge());

        final SchoolEntity school = new SchoolEntity();
        school.setId(profileJson.getSchoolId());
        entity.setSchool(school);

        final String profileTypeName = profileJson.getProfileType().getName();
        final ProfileTypeEntity profileType = this.profileTypeDao.getByName(profileTypeName);
        entity.setProfileType(profileType);
    }
}
