package edu.lab.back.db.repositories;

import edu.lab.back.db.entity.SchoolEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchoolRepository extends CrudRepository<SchoolEntity, Long> {

    // language=HQL
    @Query(value = "select s from SchoolEntity s where s.id in :ids")
    List<SchoolEntity> getByIds(@Param("ids") List<Long> ids);

    // language=HQL
    @Query(value = "select s from SchoolEntity s where s.city.id = :cityId")
    List<SchoolEntity> getSchoolsByCityId(@Param("cityId") final Long cityId);

}
