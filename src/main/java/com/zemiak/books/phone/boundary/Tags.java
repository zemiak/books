package com.zemiak.books.phone.boundary;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.zemiak.books.phone.domain.Tag;
import com.zemiak.books.phone.domain.dto.TagDTO;
import java.util.ArrayList;
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
        return convert(resource.get(TagDTO.class));
    }

    public Tag findByName(String name) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path("name/" + name);
        return convert(resource.get(TagDTO.class));
    }

    public List<Tag> all() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        return convert(resource.get(new GenericType<List<TagDTO>>(){}));
    }

    @Override
    public void close() {
        client.destroy();
    }

    public List<Tag> findByDistinct() {
        WebResource resource = webResource;

        resource = resource.path("distinct");
        return convert(resource.get(new GenericType<List<TagDTO>>(){}));
    }

    private List<Tag> convert(List<TagDTO> list) {
        List<Tag> result = new ArrayList<>();

        for (TagDTO dto: list) {
            result.add(new Tag(dto));
        }

        return result;
    }

    private Tag convert(TagDTO dto) {
        return new Tag(dto);
    }
}
