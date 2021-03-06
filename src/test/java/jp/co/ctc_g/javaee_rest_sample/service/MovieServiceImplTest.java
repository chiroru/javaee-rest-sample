package jp.co.ctc_g.javaee_rest_sample.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;
import static org.hamcrest.CoreMatchers.is;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import org.junit.BeforeClass;
import org.junit.Test;

public class MovieServiceImplTest {

    private static EJBContainer ec=null;
    private static Context ctx=null;

    @BeforeClass
    public static void initContainer() throws Exception {
        Map<String, Object> props=new HashMap<>();
        props.put(EJBContainer.MODULES, new File("target/embedded-classes"));
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
        MovieService service = (MovieService) ctx.lookup("java:global/embedded-classes/MovieServiceImpl");
        List<Movie> actual = service.findAll();
        assertThat(actual.size(), is(1));
    }
}
