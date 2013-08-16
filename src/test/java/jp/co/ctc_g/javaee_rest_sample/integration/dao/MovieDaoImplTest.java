package jp.co.ctc_g.javaee_rest_sample.integration.dao;

import java.util.List;
import jp.co.ctc_g.javaee_rest_sample.util.integration.dao.EntityManagerResource;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;
import jp.ddo.chiroru.utils.junit.resource.DBUnitTestResource;
import jp.ddo.chiroru.utils.junit.resource.DataSetFixture;
import mockit.Deencapsulation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

@DataSetFixture({"/fixtures.xml", "/fixtures_1.xml"})
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
    public DBUnitTestResource dataResource = new DBUnitTestResource(this.getClass()) {

        @Override
        protected void before()
                throws Exception {
            executeQuery("DROP TABLE MOVIE_CRITERIA");
            executeQuery("CREATE TABLE MOVIE_CRITERIA(ID INTEGER not null primary key, NAME VARCHAR2(50) not null, ACTORS VARCHAR2(200) not null)");
        }
//        @Override
//        protected IDataSet createDataSet()
//                throws Exception {
//            return new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream("/fixtures.xml"));
//        }
    };

    @Test
    @DataSetFixture({"/fixtures.xml", "/fixtures_1.xml"})
    public void DBに登録済みの全データを検索できる()
            throws Exception {
        List<Movie> actual = dao.findAll();
        assertThat(actual.size(), is(1));
    }

    @Test
    @DataSetFixture({"/fixtures.xml", "/fixtures_1.xml"})
    public void DBに登録済みの全データを検索できる2()
            throws Exception {
        List<Movie> actual = dao.findAll();
        assertThat(actual.size(), is(2));
    }
}
