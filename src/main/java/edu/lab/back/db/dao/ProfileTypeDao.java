package edu.lab.back.db.dao;

import edu.lab.back.db.entity.ProfileTypeEntity;

public interface ProfileTypeDao extends BaseCrudDao<ProfileTypeEntity, Integer> {

    ProfileTypeEntity getByName(String name);

}
