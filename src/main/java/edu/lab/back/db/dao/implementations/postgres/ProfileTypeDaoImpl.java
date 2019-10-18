package edu.lab.back.db.dao.implementations.postgres;

import edu.lab.back.db.dao.ProfileTypeDao;
import edu.lab.back.db.entity.ProfileTypeEntity;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class ProfileTypeDaoImpl extends BaseCrudDaoImpl<ProfileTypeEntity, Integer> implements ProfileTypeDao {

    //TODO SQL-инъекции!!1! Уууусука
    private final String getByNameQuery = "select type from ProfileTypeEntity type where type.name = :name";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public ProfileTypeEntity getByName(@NonNull final String name) {
        final TypedQuery<ProfileTypeEntity> query = this.getEntityManager().createQuery(getByNameQuery, ProfileTypeEntity.class);
        query.setParameter("name", name);
        final ProfileTypeEntity result = query.getSingleResult();

        return result;
    }
}
