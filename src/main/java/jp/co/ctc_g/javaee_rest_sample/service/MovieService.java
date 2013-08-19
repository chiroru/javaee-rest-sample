package jp.co.ctc_g.javaee_rest_sample.service;

import java.util.List;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;

public interface MovieService {

    Movie findById(long id);

    long countAll();

    List<Movie> findAll();

    List<Movie> findAllWithRange(int maxResults, int firstResult);

    void regist(Movie entity);

    void update(Movie entity);

    void remove(Movie entity);

    void removeById(long id);
}
