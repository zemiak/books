package com.zemiak.books.phone.boundary;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.zemiak.books.phone.domain.Tag;
import java.util.List;

public class Tags implements AutoCloseable {
    private WebResource webResource;
    private Client client;

    public Tags() {
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

    @Override
    public void close() {
        client.destroy();
    }

    public List<Tag> findByDistinct() {
        WebResource resource = webResource;
        
        resource = resource.path("distinct");
        return resource.get(new GenericType<List<Tag>>(){});
    }
}
