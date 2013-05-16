package com.zemiak.books.boundary;

import com.sun.jersey.api.client.WebResource;
import com.zemiak.books.domain.Author;
import java.util.ArrayList;
import java.util.List;

public class Authors {
    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/books-backend/webresources";

    public Authors() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(BASE_URI).path("authors");
    }

    public List<Author> findByExpression(String expr) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        List<Author> type = new ArrayList<>();
        resource = resource.path(java.text.MessageFormat.format("search/{0}", new Object[]{expr}));
        
        return resource.get(type.getClass());
    }

    public List<Author> findByLetter(String letter) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        List<Author> type = new ArrayList<>();
        
        resource = resource.path(java.text.MessageFormat.format("letter/{0}", new Object[]{letter}));
        return resource.get(type.getClass());
    }

    public Author find(String id) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.get(Author.class);
    }

    public List<Author> all() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        List<Author> type = new ArrayList<>();
        
        return resource.get(type.getClass());
    }

    public void close() {
        client.destroy();
    }

    List<Author> findByTag(String tagName) {
        WebResource resource = webResource;
        List<Author> type = new ArrayList<>();
        
        resource = resource.path(java.text.MessageFormat.format("tag/{0}", new Object[]{tagName}));
        return resource.get(type.getClass());
    }
}
