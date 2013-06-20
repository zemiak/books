package com.zemiak.books.phone.boundary;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.zemiak.books.phone.domain.Author;
import com.zemiak.books.phone.domain.Tag;
import com.zemiak.books.phone.domain.WebPage;
import com.zemiak.books.phone.domain.dto.AuthorDTO;
import com.zemiak.books.phone.domain.dto.TagDTO;
import com.zemiak.books.phone.domain.dto.WebPageDTO;
import java.util.ArrayList;
import java.util.List;

public class Authors implements AutoCloseable {
    private WebResource webResource;
    private Client client;

    public Authors() {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(RestData.BASE_URI).path("authors");
    }

    public List<Author> findByExpression(String expr) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path(java.text.MessageFormat.format("search/{0}", new Object[]{expr}));
        return convert(resource.get(new GenericType<List<AuthorDTO>>() {}));
    }

    public List<Author> findByLetter(String letter) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path(java.text.MessageFormat.format("letter/{0}", new Object[]{letter}));
        return convert(resource.get(new GenericType<List<AuthorDTO>>() {}));
    }

    public Author find(int id) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        String strId = String.valueOf(id);
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{strId}));
        return convert(resource.get(AuthorDTO.class));
    }

    public List<Author> all() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        return convert(resource.get(new GenericType<List<AuthorDTO>>() {}));
    }

    @Override
    public void close() {
        client.destroy();
    }

    public List<Author> findByTag(String tagName) {
        WebResource resource = webResource;

        resource = resource.path(java.text.MessageFormat.format("tag/{0}", new Object[]{tagName}));
        return convert(resource.get(new GenericType<List<AuthorDTO>>() {}));
    }

    public List<Tag> getTags(int id) {
        WebResource resource = webResource;

        String strId = String.valueOf(id);
        resource = resource.path(java.text.MessageFormat.format("tags/{0}", new Object[]{strId}));
        return convertTags(resource.get(new GenericType<List<TagDTO>>() {}));
    }

    public List<WebPage> getWebPages(int id) {
        WebResource resource = webResource;

        String strId = String.valueOf(id);
        resource = resource.path(java.text.MessageFormat.format("webpages/{0}", new Object[]{strId}));
        return convertWebPages(resource.get(new GenericType<List<WebPageDTO>>() {}));
    }

    private List<Author> convert(List<AuthorDTO> list) {
        List<Author> result = new ArrayList<>();

        for (AuthorDTO dto: list) {
            result.add(new Author(dto));
        }

        return result;
    }

    private List<Tag> convertTags(List<TagDTO> list) {
        List<Tag> result = new ArrayList<>();

        for (TagDTO dto: list) {
            result.add(new Tag(dto));
        }

        return result;
    }

    private List<WebPage> convertWebPages(List<WebPageDTO> list) {
        List<WebPage> result = new ArrayList<>();

        for (WebPageDTO dto: list) {
            result.add(new WebPage(dto));
        }

        return result;
    }

    private Author convert(AuthorDTO dto) {
        return new Author(dto);
    }
}
