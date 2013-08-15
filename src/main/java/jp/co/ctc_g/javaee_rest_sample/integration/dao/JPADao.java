package jp.co.ctc_g.javaee_rest_sample.integration.dao;

import java.io.Serializable;
import java.util.List;

public interface JPADao<T extends Serializable> {

    T findById(long id);

    long countAll();

    List<T> findAll();

    List<T> findAllWithRange(int maxResults, int firstResult);

    void regist(T entity);

    void update(T entity);

    void remove(T entity);

    void removeById(long id);
}
