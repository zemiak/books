package com.zemiak.books.client.boundary;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.zemiak.books.client.domain.Tag;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class Tags {
    private WebResource webResource;
    private Client client;

    public Tags() {
    }
    
    @PostConstruct
    public void init() {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(RestData.BASE_URI).path("tags");
    }

    public Tag find(int id) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path(String.valueOf(id));
        return resource.get(Tag.class);
    }

    public Tag findByName(String name) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path("name/" + name);
        return resource.get(Tag.class);
    }

    public List<Tag> all() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        return resource.get(new GenericType<List<Tag>>(){});
    }

    public List<Tag> findByDistinct() {
        WebResource resource = webResource;

        resource = resource.path("distinct");
        return resource.get(new GenericType<List<Tag>>(){});
    }

    public void close() {
        client.destroy();
    }
}
