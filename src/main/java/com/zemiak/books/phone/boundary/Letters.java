package com.zemiak.books.phone.boundary;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.zemiak.books.phone.domain.Letter;
import java.util.List;

public class Letters implements AutoCloseable {
    private WebResource webResource;
    private Client client;

    public Letters() {
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

    @Override
    public void close() {
        client.destroy();
    }
}
