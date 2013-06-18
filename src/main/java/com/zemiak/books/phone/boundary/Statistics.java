package com.zemiak.books.phone.boundary;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.zemiak.books.phone.domain.Cache;

public class Statistics implements AutoCloseable {
    private WebResource webResource;
    private Client client;

    public Statistics() {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(RestData.BASE_URI).path("statistics");
    }

    public int getAuthors() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getAuthors");

        Cache cache = resource.get(Cache.class);
        return Integer.valueOf(cache.getValue());
    }

    public int getAuthorsDocumented() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getAuthorsDocumented");

        Cache cache = resource.get(Cache.class);
        return Integer.valueOf(cache.getValue());
    }

    public int getAuthorsTagged() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getAuthorsTagged");

        Cache cache = resource.get(Cache.class);
        return Integer.valueOf(cache.getValue());
    }

    public int getBooks() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getBooks");

        Cache cache = resource.get(Cache.class);
        return Integer.valueOf(cache.getValue());
    }

    @Override
    public void close() {
        client.destroy();
    }
}
