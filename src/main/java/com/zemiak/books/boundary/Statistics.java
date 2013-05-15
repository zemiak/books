/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemiak.books.boundary;

/**
 * Jersey REST client generated for REST resource:Statistics [statistics]<br>
 * USAGE:
 * <pre>
 *        Statistics client = new Statistics();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author vasko
 */
public class Statistics {
    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/books-backend/webresources";

    public Statistics() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(BASE_URI).path("statistics");
    }

    public String getAuthors() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getAuthors");
        return resource.get(String.class);
    }

    public String getAuthorsDocumented() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getAuthorsDocumented");
        return resource.get(String.class);
    }

    public String getAuthorsTagged() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getAuthorsTagged");
        return resource.get(String.class);
    }

    public String getBooks() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getBooks");
        return resource.get(String.class);
    }

    public void close() {
        client.destroy();
    }
    
}
