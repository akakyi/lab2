package edu.lab.back.db.dao.implementations.postgres;

import edu.lab.back.db.dao.SchoolDao;
import edu.lab.back.db.entity.SchoolEntity;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@NoArgsConstructor
public class SchoolDaoImpl extends BaseCrudDaoImpl<SchoolEntity, Long> implements SchoolDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SchoolEntity> getByIds(@NonNull final List<Long> ids) {
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
}
