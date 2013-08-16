package jp.co.ctc_g.javaee_rest_sample.integration.dao;

import com.google.common.base.Stopwatch;
import java.util.List;
import javax.persistence.EntityTransaction;
import jp.co.ctc_g.javaee_rest_sample.util.integration.dao.EntityManagerResource;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;
import jp.ddo.chiroru.utils.junit.resource.DBUnitTestResource;
import jp.ddo.chiroru.utils.junit.resource.DataSetFixture;
import mockit.Deencapsulation;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

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
        assertThat(actual.size(), is(2));
    }

    @Test
    @DataSetFixture({"/fixtures.xml", "/fixtures_1.xml"})
    public void DBに登録済みの全データを検索できる2()
            throws Exception {
        List<Movie> actual = dao.findAll();
       assertThat(actual.size(), is(2));
    }

    @Test
    public void DBにデータを登録できる()
            throws Exception {
        Stopwatch stopwatch = new Stopwatch().start();
        EntityTransaction transaction = emr.getEntityManager().getTransaction();
        transaction.begin();
        IDataSet insertData = new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream("/insert.xml"));
        ITable insertTable = insertData.getTable("movie_criteria");
        
//        for (int i = 0; i < insertTable.getRowCount(); i++) {
//            Movie m;
//            m = new Movie(
//              Long.valueOf((String)insertTable.getValue(i, "id")),
//              (String)insertTable.getValue(i, "name"),
//              (String)insertTable.getValue(i, "actors"));
//            dao.regist(m);
//        }
        for (int j = 1; j < 10001; j++) {
            Movie m;
            m = new Movie(
              new Long(j),
              "name" + j,
              "actors" + j);
            dao.regist(m);
        }
        transaction.commit();
        stopwatch.stop();
        System.out.println("★★★★★" + stopwatch);
        long actual = dao.countAll();
        assertThat(String.valueOf(actual), is("10000"));
    }
}
