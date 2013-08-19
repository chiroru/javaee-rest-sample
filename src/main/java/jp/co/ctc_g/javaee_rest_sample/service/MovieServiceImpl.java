package jp.co.ctc_g.javaee_rest_sample.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import jp.co.ctc_g.javaee_rest_sample.integration.dao.MovieDao;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;

@Stateless
public class MovieServiceImpl
        implements MovieService {

    @Inject
    private MovieDao dao;

    @Override
    public Movie findById(long id) {
        return dao.findById(id);
    }

    @Override
    public long countAll() {
        return dao.countAll();
    }

    @Override
    public List<Movie> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Movie> findAllWithRange(int maxResults, int firstResult) {
        return dao.findAllWithRange(maxResults, firstResult);
    }

    @Override
    public void regist(Movie entity) {
        dao.regist(entity);
    }

    @Override
    public void update(Movie entity) {
        dao.update(entity);
    }

    @Override
    public void remove(Movie entity) {
        dao.remove(entity);
    }

    @Override
    public void removeById(long id) {
        dao.removeById(id);
    }
}
