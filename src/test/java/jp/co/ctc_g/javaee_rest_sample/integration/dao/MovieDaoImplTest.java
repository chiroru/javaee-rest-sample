package jp.co.ctc_g.javaee_rest_sample.integration.dao;

import java.util.List;
import jp.co.ctc_g.javaee_rest_sample.util.integration.dao.EntityManagerResource;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;
import mockit.Deencapsulation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

public class MovieDaoImplTest {

    private final static String PERSISTENCE_UNIT_NAME = "MoviePUTEST";

    private static MovieDao dao;

    @ClassRule
    public static EntityManagerResource emr
            = new EntityManagerResource(PERSISTENCE_UNIT_NAME);

    @BeforeClass
    public static void setUpClass() {
        dao = new MovieDaoImpl();
        Deencapsulation.setField(dao, emr.getEntityManager());
    }

    @Test
    public void DBに登録済みの全データを検索できる()
            throws Exception {
        List<Movie> actual = dao.findAll();
        System.out.println(actual.size());
        assertThat(actual.size(), is(4));
    }
}
