package com.zemiak.books.boundary.rest;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.dto.Letter;
import com.zemiak.books.service.Configuration;
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
@Path("letters")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class Letters {
    @Inject
    private Collection collection;
    
    @Inject
    private Configuration conf;

    @GET
    public List<Letter> all() {
        List<Letter> letters = new ArrayList<>();

        for (com.zemiak.books.domain.Letter letter: collection.getLetters()) {
            Letter dto = new Letter(letter, conf.getRestBaseUrl());
            letters.add(dto);
        }

        return letters;
    }

    @GET
    @Path("{id}")
    public Letter find(@PathParam("id") String id){
        com.zemiak.books.domain.Letter letter = collection.getLetter(id);

        Letter ret = (null == letter) ? null : new Letter(letter, conf.getRestBaseUrl());
        return ret;
    }
}
