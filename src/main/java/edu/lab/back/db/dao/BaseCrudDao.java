package edu.lab.back.db.dao;

import java.util.List;

//TODO мб заменить на абстрактный класс?
public interface BaseCrudDao <EntityType, IdType> {

    EntityType getById(IdType id, Class<EntityType> entityClass);

    List<EntityType> getAll(Class<EntityType> entityClass);

    EntityType deleteById(IdType id, Class<EntityType> entityClass);

    EntityType update(EntityType entity);

    EntityType save(EntityType entity);

}
