package com.zemiak.books.boundary.rest;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.dto.Letter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/letters")
@Stateless
public class Letters {
    @Inject
    private Collection collection;

    @GET
    @Produces({"application/xml","application/json"})
    public List<Letter> getAll() {
        List<Letter> letters = new ArrayList<>();

        for (com.zemiak.books.domain.Letter letter: collection.getLetters()) {
            Letter dto = new Letter(letter);
            letters.add(dto);
        }

        return letters;
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml","application/json"})
    public Letter getLetter(@PathParam("id") String id){
        com.zemiak.books.domain.Letter letter = collection.getLetter(id);

        Letter ret = (null == letter) ? null : new Letter(letter);
        return ret;
    }
}
