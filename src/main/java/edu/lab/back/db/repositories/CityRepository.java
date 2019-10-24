package edu.lab.back.db.repositories;

import edu.lab.back.db.entity.CityEntity;
import org.springframework.data.repository.CrudRepository;


public interface CityRepository extends CrudRepository<CityEntity, Long> {
}
