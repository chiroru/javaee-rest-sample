package jp.co.ctc_g.javaee_rest_sample.service;

import java.util.List;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;

public interface MovieService {

    Movie findById(Integer id);

    List<Movie> findAll();

    void remove();

    void update();
    
}
