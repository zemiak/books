package com.zemiak.books.boundary.rest;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.dto.Tag;
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
@Path("tags")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class Tags {
    @Inject
    private Collection collection;
    
    @Inject
    private Configuration conf;

    @GET
    public List<Tag> all() {
        List<Tag> tags = new ArrayList<>();

        for (com.zemiak.books.domain.Tag tag: collection.getTags()) {
            Tag dto = new Tag(tag, conf.getRestBaseUrl());
            tags.add(dto);
        }

        return tags;
    }

    @GET
    @Path("{id}")
    public Tag find(@PathParam("id") int id){
        com.zemiak.books.domain.Tag tag = collection.getTag(id);

        Tag ret = (null == tag) ? null : new Tag(tag, conf.getRestBaseUrl());
        return ret;
    }
}
