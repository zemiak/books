package com.zemiak.books.client.boundary;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.zemiak.books.client.domain.Book;
import com.zemiak.books.client.domain.Cache;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class Books {
    private WebResource webResource;
    private Client client;

    public Books() {
    }
    
    @PostConstruct
    public void init() {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(RestData.BASE_URI).path("books");
    }

    public List<Book> findByAuthor(int author) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path("author/" + author);
        return resource.get(new GenericType<List<Book>>(){});
    }

    public List<Book> findByExpression(String expr) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path(java.text.MessageFormat.format("search/{0}", new Object[]{expr}));
        return resource.get(new GenericType<List<Book>>(){});
    }

    public Book find(String id) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.get(Book.class);
    }

    public List<Book> all() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        return resource.get(new GenericType<List<Book>>(){});
    }
    
    public int count() {
        WebResource resource = webResource;
        resource = resource.path("count");
        
        Cache cache = resource.get(Cache.class);
        return Integer.valueOf(cache.getValue());
    }

    public void close() {
        client.destroy();
    }
}
