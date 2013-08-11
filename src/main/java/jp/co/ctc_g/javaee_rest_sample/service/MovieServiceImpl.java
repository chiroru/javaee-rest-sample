package jp.co.ctc_g.javaee_rest_sample.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import jp.co.ctc_g.javaee_rest_sample.integration.dao.MovieDao;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;

@Stateless
public class MovieServiceImpl
        implements MovieService {

    @EJB
    private MovieDao dao;

    @Override
    public Movie findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<Movie> findAll() {
        return dao.findAll();
    }

    @Override
    public void update() {
        dao.update();
    }

    @Override
    public void remove() {
        dao.remove();
    }
}
