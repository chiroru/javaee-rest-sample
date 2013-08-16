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
    public List<Movie> findAll() {
        List<Movie> movieList = new ArrayList<>();
        Movie m = new Movie(new Long(1), "test", "test");
        movieList.add(m);
        return movieList;
    }

    @Override
    public Movie findById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long countAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> findAllWithRange(int maxResults, int firstResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void regist(Movie entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Movie entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Movie entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
