package jp.co.ctc_g.javaee_rest_sample.integration.dao;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;

@StaticMetamodel(Movie.class)
public class Movie_ { 

    public static volatile SingularAttribute<Movie, Integer> id;
    public static volatile SingularAttribute<Movie, String> name;
    public static volatile SingularAttribute<Movie, String> actors;

}