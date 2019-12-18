package edu.lab.back.db.repositories;

import edu.lab.back.db.entity.ProfileTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProfileTypeRepository extends CrudRepository<ProfileTypeEntity, Integer> {

    // language=HQL
    @Query(value = "select type from ProfileTypeEntity type where type.name = :name")
    ProfileTypeEntity getByName(@Param("name") String name);

}
