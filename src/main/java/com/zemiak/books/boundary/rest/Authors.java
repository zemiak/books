package com.zemiak.books.boundary.rest;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.dto.Author;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/authors")
@Stateless
public class Authors {
    @Inject
    private Collection collection;

    @GET
    @Produces({"application/xml","application/json"})
    public List<Author> getAll() {
        List<Author> authors = new ArrayList<>();

        for (com.zemiak.books.domain.Author author: collection.getAuthors()) {
            Author dto = new Author(author);
            authors.add(dto);
        }

        return authors;
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml","application/json"})
    public Author getAuthor(@PathParam("id") int id){
        com.zemiak.books.domain.Author author = collection.getAuthor(id);

        Author ret = (null == author) ? null : new Author(author);
        return ret;
    }

    @GET
    @Path("letter/{letter}")
    @Produces({"application/xml","application/json"})
    public List<Author> getByLetter(@PathParam("letter") String letter){
        List<Author> authors = new ArrayList<>();

        for (com.zemiak.books.domain.Author author: collection.getLetter(letter).getAuthors()) {
            Author dto = new Author(author);
            authors.add(dto);
        }

        return authors;
    }
}
