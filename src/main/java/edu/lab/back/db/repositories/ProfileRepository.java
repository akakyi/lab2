package edu.lab.back.db.repositories;

import edu.lab.back.db.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Long> {
}
