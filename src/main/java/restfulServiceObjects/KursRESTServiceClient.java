/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfulServiceObjects;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:KursFacadeREST [kursJson]<br>
 * USAGE:
 * <pre>
 *        KursRESTServiceClient client = new KursRESTServiceClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 */
public class KursRESTServiceClient {
    private final WebTarget webTarget;
    private final Client client;
    private static final String BASE_URI = "http://localhost:8080/LearningAgreement_RestfulService/webresources";

    public KursRESTServiceClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("kursJson");
    }

    public <T> T findAll(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("findAlleKurse");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
