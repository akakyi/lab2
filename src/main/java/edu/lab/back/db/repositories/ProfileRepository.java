package edu.lab.back.db.repositories;

import edu.lab.back.db.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Long> {

    // language=HQL
    @Query(value = "select p from ProfileEntity p where p.school.id = :schoolId")
    public List<ProfileEntity> getProfilesBySchoolId(@Param("schoolId") Long schoolId);

}
