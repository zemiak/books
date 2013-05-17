package com.zemiak.books.boundary;

import com.sun.jersey.api.client.WebResource;

public class Statistics {
    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;

    public Statistics() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(RestData.BASE_URI).path("statistics");
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
