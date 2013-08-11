package jp.co.ctc_g.javaee_rest_sample.integration.dao;

import java.util.List;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;

public interface MovieDao {

    Movie findById(Integer id);

    List<Movie> findAll();

    void update();
    
    void remove();
}
