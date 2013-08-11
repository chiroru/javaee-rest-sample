package jp.co.ctc_g.javaee_rest_sample.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;

public class MovieResourceClient {

    private final static String END_POINT = "http://127.0.0.1:8080/javaee-rest-sample";

    private WebTarget movieWebTarget;

    public MovieResourceClient() {
        ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget webTarget = client.target(END_POINT);
        WebTarget resourceWebTarget = webTarget.path("webresources");
        movieWebTarget = resourceWebTarget.path("movies");
    }

    public void findAll() {

        Invocation.Builder invocationBuilder = movieWebTarget.path("1").request("application/json");
        Response response = invocationBuilder.get();
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }

    public static void main(String[] args) {
        MovieResourceClient client = new MovieResourceClient();
        client.findAll();
    }
}
