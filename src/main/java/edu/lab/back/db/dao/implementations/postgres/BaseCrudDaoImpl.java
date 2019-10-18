package edu.lab.back.db.dao.implementations.postgres;

import edu.lab.back.db.dao.BaseCrudDao;
import lombok.NonNull;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class BaseCrudDaoImpl<EntityType, IdType> implements BaseCrudDao<EntityType, IdType> {
    
    @Override
    public EntityType getById(@NonNull final IdType id, @NonNull final Class<EntityType> entityClass) {
        final EntityType city = this.getEntityManager().find(entityClass, id);
        return city;
    }

    @Override
    public List<EntityType> getAll(@NonNull final Class<EntityType> entityClass) {
        final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<EntityType> query = criteriaBuilder.createQuery(entityClass);
        final Root<EntityType> from = query.from(entityClass);
        final CriteriaQuery<EntityType> allSelection = query.select(from);

        final TypedQuery<EntityType> resultQuery = this.getEntityManager().createQuery(allSelection);
        final List<EntityType> result = resultQuery.getResultList();

        return result;
    }

    @Override
    public EntityType deleteById(final IdType id, Class<EntityType> entityClass) {
        final EntityType city = this.getById(id, entityClass);
        this.getEntityManager().remove(city);
        return city;
    }

    @Override
    public EntityType update(final EntityType city) {
        this.getEntityManager().merge(city);
        return city;
    }

    @Override
    public EntityType save(final EntityType city) {
        final EntityManager entityManager = this.getEntityManager();
        entityManager.persist(city);
        return city;
    }
    
    protected abstract EntityManager getEntityManager();
}
