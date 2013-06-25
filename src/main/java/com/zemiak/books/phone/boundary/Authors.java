package com.zemiak.books.phone.boundary;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.zemiak.books.phone.domain.Author;
import com.zemiak.books.phone.domain.Cache;
import com.zemiak.books.phone.domain.Tag;
import com.zemiak.books.phone.domain.WebPage;
import java.util.List;

public class Authors implements AutoCloseable {
    private WebResource webResource;
    private Client client;

    public Authors() {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
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

    public Author find(int id) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        String strId = String.valueOf(id);
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{strId}));
        return resource.get(Author.class);
    }

    public List<Author> all() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        return resource.get(new GenericType<List<Author>>() {});
    }

    @Override
    public void close() {
        client.destroy();
    }

    public List<Author> findByTag(String tagName) {
        WebResource resource = webResource;

        resource = resource.path(java.text.MessageFormat.format("tag/{0}", new Object[]{tagName}));
        return resource.get(new GenericType<List<Author>>() {});
    }

    public List<Tag> getTags(int id) {
        WebResource resource = webResource;

        String strId = String.valueOf(id);
        resource = resource.path(java.text.MessageFormat.format("tags/{0}", new Object[]{strId}));
        return resource.get(new GenericType<List<Tag>>() {});
    }

    public List<WebPage> getWebPages(int id) {
        WebResource resource = webResource;

        String strId = String.valueOf(id);
        resource = resource.path(java.text.MessageFormat.format("webpages/{0}", new Object[]{strId}));
        return resource.get(new GenericType<List<WebPage>>() {});
    }
    
    public int count() {
        WebResource resource = webResource;
        resource = resource.path("count");
        
        Cache cache = resource.get(Cache.class);
        return Integer.valueOf(cache.getValue());
    }
    
    public int countDocumented() {
        WebResource resource = webResource;
        resource = resource.path("countDocumented");
        
        Cache cache = resource.get(Cache.class);
        return Integer.valueOf(cache.getValue());
    }
    
    public int countTagged() {
        WebResource resource = webResource;
        resource = resource.path("countTagged");
        
        Cache cache = resource.get(Cache.class);
        return Integer.valueOf(cache.getValue());
    }
}
