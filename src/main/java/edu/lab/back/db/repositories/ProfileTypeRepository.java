package edu.lab.back.db.repositories;

import edu.lab.back.db.entity.ProfileTypeEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProfileTypeRepository extends CrudRepository<ProfileTypeEntity, Integer> {

    ProfileTypeEntity getByName(String name);

}
