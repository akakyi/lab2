package edu.lab.back.db.dao.implementations.postgres;

import edu.lab.back.db.dao.ProfileDao;
import edu.lab.back.db.entity.ProfileEntity;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@NoArgsConstructor
public class ProfileDaoImpl extends BaseCrudDaoImpl<ProfileEntity, Long> implements ProfileDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
}
