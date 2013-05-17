package com.zemiak.books.boundary;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.zemiak.books.domain.Tag;
import java.util.ArrayList;
import java.util.List;

public class Tags {
    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;

    public Tags() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(RestData.BASE_URI).path("tags");
    }

    public Tag find(String id) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.get(Tag.class);
    }

    public List<Tag> all() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        
        return resource.get(new GenericType<List<Tag>>(){});
    }

    public void close() {
        client.destroy();
    }

    List<String> findByDistinct() {
        WebResource resource = webResource;
        
        resource = resource.path("distinct");
        return resource.get(new GenericType<List<String>>(){});
    }
    
}
