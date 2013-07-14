package com.zemiak.books.client.boundary;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.zemiak.books.client.domain.Letter;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class Letters {
    private WebResource webResource;
    private Client client;

    public Letters() {
    }
    
    @PostConstruct
    public void init() {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
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
