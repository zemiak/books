package com.zemiak.books.boundary;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.zemiak.books.domain.Author;
import java.util.List;

public class Authors {
    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;

    public Authors() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(RestData.BASE_URI).path("authors");
    }

    public List<Author> findByExpression(String expr) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path(java.text.MessageFormat.format("search/{0}", new Object[]{expr}));
        return resource.get(new GenericType<List<Author>>() {});
    }

    public List<Author> findByLetter(String letter) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        
        resource = resource.path(java.text.MessageFormat.format("letter/{0}", new Object[]{letter}));
        return resource.get(new GenericType<List<Author>>() {});
    }

    public Author find(String id) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.get(Author.class);
    }

    public List<Author> all() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        
        return resource.get(new GenericType<List<Author>>() {});
    }

    public void close() {
        client.destroy();
    }

    List<Author> findByTag(String tagName) {
        WebResource resource = webResource;
        
        resource = resource.path(java.text.MessageFormat.format("tag/{0}", new Object[]{tagName}));
        return resource.get(new GenericType<List<Author>>() {});
    }
}
