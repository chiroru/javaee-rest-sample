package jp.co.ctc_g.javaee_rest_sample.integration.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;

@Stateless
public class MovieDaoImpl
        implements MovieDao {

    @PersistenceContext(unitName = "MoviePU")
    EntityManager em;

    @Override
    public Movie findById(Integer id) {
        return em.find(Movie.class, id);
    }

    @Override
    public List<Movie> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery listCriteria = builder.createQuery(Movie.class);
        Root<Movie> listRoot = listCriteria.from(Movie.class);
        listCriteria.select(listRoot);
        TypedQuery<Movie> query = em.createQuery(listCriteria);
        return query.getResultList();
    }

    @Override
    public void update() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate updateCriteria = builder.createCriteriaUpdate(Movie.class);
        Root<Movie> updateRoot = updateCriteria.from(Movie.class);
        updateCriteria.where(builder.equal(updateRoot.get(Movie_.name), "Inception"));
        updateCriteria.set(updateRoot.get(Movie_.name), "INCEPTION");
        Query q = em.createQuery(updateCriteria);
        q.executeUpdate();
        em.flush();
    }
    
    @Override
    public void remove() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete deleteCriteria = builder.createCriteriaDelete(Movie.class);
        Root<Movie> updateRoot = deleteCriteria.from(Movie.class);
        deleteCriteria.where(builder.equal(updateRoot.get(Movie_.name), "The Matrix"));
        Query q = em.createQuery(deleteCriteria);
        q.executeUpdate();
        em.flush();
    }
    
}
