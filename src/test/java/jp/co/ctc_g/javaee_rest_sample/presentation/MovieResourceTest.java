package jp.co.ctc_g.javaee_rest_sample.presentation;

import java.util.Arrays;
import java.util.List;
import jp.co.ctc_g.javaee_rest_sample.service.MovieService;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class MovieResourceTest {

    private static MovieResource resource;

    @Mocked
    MovieService service;

    @Before
    public void setUpClass() {
        resource = new MovieResource();
        Deencapsulation.setField(resource, service);
    }

    @Test
    public void DBに登録済みの全データを検索できる()
            throws Exception {
        new Expectations() {{
            service.findAll();
            result = Arrays.asList(new Movie(1, "test", "test"));
        }};
        
        List<Movie> actual = resource.findAll();
        assertThat(actual.size(), is(1));
    }
}
