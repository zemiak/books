package com.zemiak.books.phone.domain;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.zemiak.books.phone.boundary.RestData;
import com.zemiak.books.phone.domain.dto.TagDTO;
import java.util.List;
import java.util.Objects;

public class Tag {
    private long id;
    private String name;
    private String authorsUrl;
    private List<Author> authors = null;

    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;
    public Tag() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(RestData.BASE_URI);
    }

    public Tag(TagDTO dto) {
        this();

        this.id = dto.getId();
        this.name = dto.getName();
        this.authorsUrl = dto.getAuthorsUrl();
    }

    public List<Author> getAuthors() {
        if (null == authors) {
            WebResource resource = webResource;

            resource = resource.path(authorsUrl);
            authors = resource.get(new GenericType<List<Author>>(){});
        }

        return authors;
    }

    public void close() {
        client.destroy();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tag other = (Tag) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tag{" + "id=" + id + ", name=" + name + '}';
    }

    public int compareTo(Object t) {
        Tag other = (Tag) t;

        return name.compareTo(other.getName());
    }
}
