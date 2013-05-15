/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemiak.books.boundary;

/**
 * Jersey REST client generated for REST resource:Letters [letters]<br>
 * USAGE:
 * <pre>
 *        Letters client = new Letters();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author vasko
 */
public class Letters {
    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/books-backend/webresources";

    public Letters() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(BASE_URI).path("letters");
    }

    public <T> T find(Class<T> responseType, String id) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.get(responseType);
    }

    public <T> T all(Class<T> responseType) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        return resource.get(responseType);
    }

    public void close() {
        client.destroy();
    }
    
}
