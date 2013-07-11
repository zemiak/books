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
public class Author implements Comparable, AutoCloseable {
    private List<Tag> tags = null;
    private String name;
    private int id;
    private List<WebPage> webPages = null;
    private List<Book> books = null;

    private String booksUrl;
    private String tagsUrl;
    private String webPagesUrl;

    @XmlTransient
    private Client client = null;

    public Author() {
    }

    public List<Book> getBooks() {
        if (null == books) {
            WebResource resource = getClient().resource(booksUrl);
            books = resource.get(new GenericType<List<Book>>() {});
        }

        return books;
    }

    public List<WebPage> getWebPages() {
        if (null == webPages) {
            WebResource resource = getClient().resource(webPagesUrl);
            webPages = resource.get(new GenericType<List<WebPage>>() {});
        }

        return webPages;
    }

    public List<Tag> getTags() {
        if (null == tags) {
            WebResource resource = getClient().resource(tagsUrl);
            tags = resource.get(new GenericType<List<Tag>>() {});
        }

        return tags;
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

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + this.id;
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
        final Author other = (Author) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Author{" + "name=" + name + ", id=" + id + '}';
    }

    @Override
    public int compareTo(Object t) {
        Author other = (Author) t;

        return name.compareTo(other.getName());
    }
}
