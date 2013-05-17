package com.zemiak.books.boundary;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.zemiak.books.domain.Letter;
import java.util.List;

public class Letters {
    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;

    public Letters() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(RestData.BASE_URI).path("letters");
    }

    public Letter find(String id) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.get(Letter.class);
    }

    public List<Letter> all() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        
        return resource.get(new GenericType<List<Letter>>(){});
    }

    public void close() {
        client.destroy();
    }
}
