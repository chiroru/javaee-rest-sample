package jp.co.ctc_g.javaee_rest_sample.integration.dao;

import com.google.common.base.Stopwatch;
import java.io.InputStream;
import java.util.List;
import javax.persistence.EntityTransaction;
import jp.co.ctc_g.javaee_rest_sample.util.integration.dao.EntityManagerResource;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;
import jp.ddo.chiroru.utils.junit.resource.DBUnitTestResource;
import jp.ddo.chiroru.utils.junit.resource.DataSetFixture;
import mockit.Deencapsulation;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static jp.co.ctc_g.javaee_rest_sample.util.junit.ITableMatcher.*;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.xml.sax.InputSource;

public class MovieDaoImplTest {

    private final static Logger L = LoggerFactory.getLogger(MovieDaoImplTest.class);

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
    };

    @Test
    @DataSetFixture({"/jp/co/ctc_g/javaee_rest_sample/integration/dao/MovieDaoImplTest-TestData.xml"})
    public void DBに登録済みの全データを検索できる()
            throws Exception {
        List<Movie> actual = dao.findAll();
        assertThat(actual.size(), is(1));
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
        L.debug("経過時間 : " + stopwatch);
        long actual = dao.countAll();
        assertThat(String.valueOf(actual), is("10000"));
    }

    @Test
    @DataSetFixture("/jp/co/ctc_g/javaee_rest_sample/integration/dao/MovieDaoImplTest-TestData.xml")
    public void 登録済みのデータを更新できる()
            throws Exception {
        EntityTransaction transaction = emr.getEntityManager().getTransaction();
        transaction.begin();
        Movie m = dao.findById(1);
        m.setName("Updated Name");
        m.setActors("Updated Actors");
        dao.update(m);
        transaction.commit();
        // expected
        InputStream is = this.getClass().getResourceAsStream("/jp/co/ctc_g/javaee_rest_sample/integration/dao/MovieDaoImplTest-ExpectedTestData.xml");
        IDataSet expectedDataSet =
                new FlatXmlDataSet(new FlatXmlProducer(new InputSource(is)));
        ITable expectedTable = expectedDataSet.getTable("movie_criteria");
        // actual
        IDatabaseConnection conn = dataResource.getConnection();
        IDataSet databaseDataSet = conn.createDataSet();
        ITable actualTable = databaseDataSet.getTable("movie_criteria");
        assertThat(actualTable, tableOf(expectedTable));
    }

    @Test
    @DataSetFixture("/jp/co/ctc_g/javaee_rest_sample/integration/dao/MovieDaoImplTest-TestData.xml")
    public void DBに登録済のデータを削除できる()
            throws Exception {
        EntityTransaction transaction = emr.getEntityManager().getTransaction();
        transaction.begin();
        dao.removeById(1);
        transaction.commit();
        List<Movie> actual = dao.findAll();
        assertThat(actual.size(), is(0));
    }
}
