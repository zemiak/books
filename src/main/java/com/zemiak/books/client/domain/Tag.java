package com.zemiak.books.client.domain;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Tag implements Comparable, AutoCloseable {
    private String name;
    private String authorsUrl;
    private List<Author> authors = null;

    @XmlTransient
    private Client client = null;
    
    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        if (null == authors) {
            WebResource resource = getClient().resource(authorsUrl);
            authors = resource.get(new GenericType<List<Author>>(){});
        }

        return authors;
    }
    
    private Client getClient() {
        if (null == client) {
            ClientConfig config = new DefaultClientConfig();
            client = Client.create(config);
        }
        
        return client;
    }

    @Override
    public void close() {
        if (null != client) client.destroy();
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tag{name=" + name + '}';
    }

    @Override
    public int compareTo(Object t) {
        Tag other = (Tag) t;

        return name.compareTo(other.getName());
    }
}
