package jp.co.ctc_g.javaee_rest_sample.presentation;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.response.Response;
import java.util.List;
import java.util.Map;
import jp.co.ctc_g.javaee_rest_sample.util.integration.IntegrationTest;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@Category(IntegrationTest.class)
public class SampleTest {

    private final static String BASE_URI = "http://127.0.0.1:8080";
    private final static int PORT = 80;
    private final static String CONTEXT_PATH = "/javaee-rest-sample";

    static {
        baseURI = BASE_URI;
        port = PORT;
        basePath = CONTEXT_PATH;
    }

    @Test
    public void sampleTest() throws Exception {
        Response resp = get("/webresources/movies");
        System.out.println(resp.getBody().asString());
        // {"id":1,"name":"The Matrix","actors":"Keanu Reeves, Laurence Fishburne, Carrie-Ann Moss"}
        List<?> list = get("/webresources/movies").path("");
        for (Object o : list) {
            System.out.println(o.toString());
        }
        Map<Object, Object> m = get("/webresources/movies").path("[0]");
        for (Object key : m.keySet()) {
            System.out.println("[" + key + "] : (" + m.get(key) + ")");
        }
        System.out.println(get("/webresources/movies").path("[0].id"));
        int id = get("/webresources/movies").path("[0].id");
        assertThat(id, is(1));
    }
}
