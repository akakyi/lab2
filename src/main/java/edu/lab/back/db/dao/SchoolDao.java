package edu.lab.back.db.dao;

import edu.lab.back.db.entity.SchoolEntity;

import java.util.List;

public interface SchoolDao extends BaseCrudDao<SchoolEntity, Long> {

    List<SchoolEntity> getByIds(List<Long> ids);

}
