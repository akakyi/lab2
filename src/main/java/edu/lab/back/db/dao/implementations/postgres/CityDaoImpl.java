package edu.lab.back.db.dao.implementations.postgres;

import edu.lab.back.db.dao.CityDao;
import edu.lab.back.db.entity.CityEntity;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@NoArgsConstructor
public class CityDaoImpl extends BaseCrudDaoImpl<CityEntity, Long> implements CityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
}
