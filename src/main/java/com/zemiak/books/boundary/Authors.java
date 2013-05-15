/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemiak.books.boundary;

/**
 * Jersey REST client generated for REST resource:Authors [authors]<br>
 * USAGE:
 * <pre>
 *        Authors client = new Authors();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author vasko
 */
public class Authors {
    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/books-backend/webresources";

    public Authors() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(BASE_URI).path("authors");
    }

    public <T> T findByExpression(Class<T> responseType, String expr) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("search/{0}", new Object[]{expr}));
        return resource.get(responseType);
    }

    public <T> T findByLetter(Class<T> responseType, String letter) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("letter/{0}", new Object[]{letter}));
        return resource.get(responseType);
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
