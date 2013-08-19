package jp.co.ctc_g.javaee_rest_sample.integration.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static com.google.common.base.Preconditions.*;

public class AbstractJPADao<T extends Serializable>
        implements JPADao<T> {

    @PersistenceContext(unitName = "MoviePU")
    protected EntityManager em;

    private Class<T> entityClazz;

    public AbstractJPADao(Class<T> entityClazz) {
        this.entityClazz = entityClazz;
    }

    @Override
    public T findById(long id) {
        return em.find(entityClazz, id);
    }

    @Override
    public long countAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(entityClazz)));
        TypedQuery<Long> query = em.createQuery(cq);
        return query.getSingleResult();
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery listCriteria = builder.createQuery(entityClazz);
        Root<T> listRoot = listCriteria.from(entityClazz);
        listCriteria.select(listRoot);
        TypedQuery<T> query = em.createQuery(listCriteria);
        return query.getResultList();
    }

    @Override
    public List<T> findAllWithRange(int maxResults, int firstResult) {
        // TODO : maxResultsとfirstResultのデフォルト値をどうするか要検討.
        CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClazz);
        cq.select(cq.from(entityClazz));
        TypedQuery<T> q = em.createQuery(cq);
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
        return q.getResultList();
    }

    @Override
    public void regist(T entity) {
        checkNotNull(entity);
        em.persist(entity);
    }

    @Override
    public void update(T entity) {
        checkNotNull(entity);
        em.merge(entity);
    }

    @Override
    public void remove(T entity) {
        checkNotNull(entity);
        em.remove(entity);
    }

    @Override
    public void removeById(long id) {
        em.remove(findById(id));
    }

}
