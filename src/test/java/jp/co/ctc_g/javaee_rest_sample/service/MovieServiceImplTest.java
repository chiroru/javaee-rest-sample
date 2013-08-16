package jp.co.ctc_g.javaee_rest_sample.service;

import java.util.Arrays;
import java.util.List;
import jp.co.ctc_g.javaee_rest_sample.integration.dao.MovieDao;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.junit.Before;

public class MovieServiceImplTest {

    private MovieService service;

    @Mocked
    MovieDao dao;

    @Before
    public void setUp() {
        service = new MovieServiceImpl();
        Deencapsulation.setField(service, dao);
    }

    @Test
    public void DBに登録済みの全データを検索できる()
            throws Exception {
        new Expectations() {{
            dao.findAll();
            result = Arrays.asList(new Movie(new Long(1), "test", "test"));
        }};
        
        List<Movie> actual = dao.findAll();
        assertThat(actual.size(), is(1));
    }
}
