package jp.co.ctc_g.javaee_rest_sample.presentation;

import jp.co.ctc_g.javaee_rest_sample.util.integration.IntegrationTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.response.Response;

@Category(IntegrationTest.class)
public class sampleIntegrationTest {

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
        Response response = get("/webresources/movies");
        System.out.println(response.getBody().print());
    }
}
