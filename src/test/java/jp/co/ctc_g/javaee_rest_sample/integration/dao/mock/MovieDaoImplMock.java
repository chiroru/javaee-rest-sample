package jp.co.ctc_g.javaee_rest_sample.integration.dao.mock;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import jp.co.ctc_g.javaee_rest_sample.integration.dao.MovieDao;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;

@Alternative
@Stateless
public class MovieDaoImplMock
        implements MovieDao {

    @Override
    public Movie findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movieList = new ArrayList<>();
        Movie m = new Movie(1, "test", "test");
        movieList.add(m);
        return movieList;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
