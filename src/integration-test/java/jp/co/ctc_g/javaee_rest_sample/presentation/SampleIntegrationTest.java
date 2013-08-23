package jp.co.ctc_g.javaee_rest_sample.presentation;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.response.Response;
import java.util.List;
import java.util.Map;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;
import jp.co.ctc_g.javaee_rest_sample.util.integration.IntegrationTest;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@Category(IntegrationTest.class)
public class SampleIntegrationTest {

    private final static String BASE_URI = "http://127.0.0.1:8080";
    private final static int PORT = 80;
    private final static String CONTEXT_PATH = "/javaee-rest-sample-1.0-SNAPSHOT";

    static {
        baseURI = BASE_URI;
        port = PORT;
        basePath = CONTEXT_PATH;
    }

    @Test
    public void sampleTest() throws Exception {
        Response resp = given().contentType("application/xml; charset=UTF-8").get("/webresources/movies");
        assertThat(resp.statusCode(), is(200));
        System.out.println(resp.getBody().asString());
        // {"id":1,"name":"The Matrix","actors":"Keanu Reeves, Laurence Fishburne, Carrie-Ann Moss"}
        Map<Object, Object> m = get("/webresources/movies").path("[0]");
        for (Object key : m.keySet()) {
            System.out.println("[" + key + "] : (" + m.get(key) + ")");
        }
        System.out.println(get("/webresources/movies").path("[0].id"));
        int id = get("/webresources/movies").path("[0].id");
        assertThat(id, is(1));
    }

    @Test
    public void データを登録()
            throws Exception {
        Movie m = new Movie(new Long(10), "Regist Test", "Regist Test");
        Response r = given().contentType("application/json; charset=UTF-8").body(m).post("/webresources/movies");
        assertThat(r.getStatusCode(), is(200));
        System.out.println(r.getBody().toString());
    }

    @Test
    public void データを更新()
            throws Exception {
        Movie m = get("/webresources/movies/1").as(Movie.class);
        m.setName(m.getName() + " modified");
        Response r = given().contentType("application/json; charset=UTF-8").body(m).put("/webresources/movies");
        System.out.println(r.getBody().toString());
        assertThat(r.getStatusCode(), is(200));
    }

    @Test
    public void データを削除()
            throws Exception {
        Response r = delete("/webresources/movies/3");
        System.out.println(r.getBody().toString());
        assertThat(r.getStatusCode(), is(200));
    }
}
