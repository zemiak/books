package com.zemiak.books.boundary.rest;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.dto.Book;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("books")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class Books {

    @Inject
    private Collection collection;

    @GET
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();

        for (com.zemiak.books.domain.Book book: collection.getBooks()) {
            Book dto = new Book(book);
            books.add(dto);
        }

        return books;
    }

    @GET
    @Path("{id}")
    public Book getBook(@PathParam("id") int id){
        com.zemiak.books.domain.Book book = collection.getBook(id);

        Book ret = (null == book) ? null : new Book(book);
        return ret;
    }

    @GET
    @Path("author/{author}")
    public List<Book> getByAuthor(@PathParam("author") int authorId){
        List<Book> books = new ArrayList<>();

        for (com.zemiak.books.domain.Book book: collection.getAuthor(authorId).getBooks()) {
            Book dto = new Book(book);
            books.add(dto);
        }

        return books;
    }
}
