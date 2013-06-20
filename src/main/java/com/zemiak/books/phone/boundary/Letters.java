package com.zemiak.books.phone.boundary;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.zemiak.books.phone.domain.Letter;
import com.zemiak.books.phone.domain.dto.LetterDTO;
import java.util.ArrayList;
import java.util.List;

public class Letters implements AutoCloseable {
    private WebResource webResource;
    private Client client;

    public Letters() {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(RestData.BASE_URI).path("letters");

        System.err.println("Letters webResource: " + webResource.getURI().toString());
    }

    public Letter find(String id) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return convert(resource.get(LetterDTO.class));
    }

    public List<Letter> all() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        return convert(resource.get(new GenericType<List<LetterDTO>>(){}));
    }

    @Override
    public void close() {
        client.destroy();
    }

    private List<Letter> convert(List<LetterDTO> list) {
        List<Letter> result = new ArrayList<>();

        for (LetterDTO dto: list) {
            result.add(new Letter(dto));
        }

        return result;
    }

    private Letter convert(LetterDTO dto) {
        return new Letter(dto);
    }
}
