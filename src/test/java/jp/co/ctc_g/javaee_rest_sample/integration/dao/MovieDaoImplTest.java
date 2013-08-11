package jp.co.ctc_g.javaee_rest_sample.integration.dao;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class MovieDaoImplTest {

    private static EJBContainer ec=null;
    private static Context ctx=null;

    @BeforeClass
    public static void initContainer() throws Exception {
        Map<String, Object> props=new HashMap<String, Object>();
        props.put(EJBContainer.MODULES, new File("target/embedded-classes"));
//        props.put("org.glassfish.ejb.embedded.glassfish.instance.root","./src/test/testing-domain");
//        props.put("org.glassfish.ejb.embedded.glassfish.web.http.port","");
        ec = EJBContainer.createEJBContainer(props);
        ctx = ec.getContext();
    }


    @AfterClass
    public static void closeContainer() throws Exception {
        if(ctx!=null)
            ctx.close();
        if(ec!=null)
            ec.close();
    }

    @Test
    public void findAllTest() throws Exception {
//        MovieDao dao = (MovieDao) ctx.lookup("java:global/embedded-classes/MovieDaoImpl!jp.co.ctc_g.javaee_rest_sample.integration.dao.MovieDaoImpl");
        MovieDao dao = (MovieDao) ctx.lookup("java:global/embedded-classes/MovieDaoImpl");
        List<Movie> actual = dao.findAll();
        assertThat(actual.size(), is(4));
    }

}
