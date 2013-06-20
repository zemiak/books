package com.zemiak.books.phone.boundary;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.zemiak.books.phone.domain.Book;
import com.zemiak.books.phone.domain.dto.BookDTO;
import java.util.ArrayList;
import java.util.List;

public class Books implements AutoCloseable {
    private WebResource webResource;
    private Client client;

    public Books() {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(RestData.BASE_URI).path("books");
    }

    public List<Book> findByAuthor(int author) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path("author/" + author);
        return convert(resource.get(new GenericType<List<BookDTO>>(){}));
    }

    public List<Book> findByExpression(String expr) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path(java.text.MessageFormat.format("search/{0}", new Object[]{expr}));
        return convert(resource.get(new GenericType<List<BookDTO>>(){}));
    }

    public Book find(String id) throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return convert(resource.get(BookDTO.class));
    }

    public List<Book> all() throws com.sun.jersey.api.client.UniformInterfaceException {
        WebResource resource = webResource;

        return convert(resource.get(new GenericType<List<BookDTO>>(){}));
    }

    @Override
    public void close() {
        client.destroy();
    }

    private List<Book> convert(List<BookDTO> list) {
        List<Book> result = new ArrayList<>();

        for (BookDTO dto: list) {
            result.add(new Book(dto));
        }

        return result;
    }

    private Book convert(BookDTO dto) {
        return new Book(dto);
    }
}
