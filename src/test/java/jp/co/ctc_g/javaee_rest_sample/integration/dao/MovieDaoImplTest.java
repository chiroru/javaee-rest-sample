package jp.co.ctc_g.javaee_rest_sample.integration.dao;

import java.util.List;
import jp.co.ctc_g.javaee_rest_sample.util.integration.dao.EntityManagerResource;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;
import jp.co.ctc_g.javaee_rest_sample.util.junit.DBUnitTestResource;
import mockit.Deencapsulation;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class MovieDaoImplTest {

    private final static String PERSISTENCE_UNIT_NAME = "MoviePUTEST";

    private MovieDao dao;

    @ClassRule
    public static EntityManagerResource emr
            = new EntityManagerResource(PERSISTENCE_UNIT_NAME);

    @Before
    public void setUp() {
        dao = new MovieDaoImpl();
        Deencapsulation.setField(dao, emr.getEntityManager());
    }

    @Rule
    public DBUnitTestResource dataResource = new DBUnitTestResource() {

        @Override
        protected void before()
                throws Exception {
            executeQuery("DROP TABLE MOVIE_CRITERIA");
            executeQuery("CREATE TABLE MOVIE_CRITERIA(ID INTEGER not null primary key, NAME VARCHAR2(50) not null, ACTORS VARCHAR2(200) not null)");
        }

        @Override
        protected IDataSet createDataSet()
                throws Exception {
            return new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream("/fixtures.xml"));
        }
    };

    @Test
    public void DBに登録済みの全データを検索できる()
            throws Exception {
        List<Movie> actual = dao.findAll();
        System.out.println(actual.size());
        assertThat(actual.size(), is(1));
    }
}
