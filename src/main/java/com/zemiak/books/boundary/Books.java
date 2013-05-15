/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemiak.books.boundary;

/**
 * Jersey REST client generated for REST resource:Books [books]<br>
 * USAGE:
 * <pre>
 *        Books client = new Books();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author vasko
 */
public class Books {
    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/books-backend/webresources";

    public Books() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(BASE_URI).path("books");
    }

    public <T> T findByAuthor(Class<T> responseType, String author) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("author/{0}", new Object[]{author}));
        return resource.get(responseType);
    }

    public <T> T findByExpression(Class<T> responseType, String expr) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("search/{0}", new Object[]{expr}));
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
