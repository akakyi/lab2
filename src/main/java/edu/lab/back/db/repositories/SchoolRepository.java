package edu.lab.back.db.repositories;

import edu.lab.back.db.entity.SchoolEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SchoolRepository extends CrudRepository<SchoolEntity, Long> {

    List<SchoolEntity> getByIds(List<Long> ids);

}
