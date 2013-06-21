package com.zemiak.books.phone.domain;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.zemiak.books.phone.boundary.RestData;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Author {
    private List<String> tags = null;
    private String name;
    private int id;
    private List<WebPage> webPages = null;
    private List<Book> books = null;

    private String booksUrl;
    private String tagsUrl;
    private String webPagesUrl;

    @XmlTransient
    private com.sun.jersey.api.client.WebResource webResource;

    @XmlTransient
    private com.sun.jersey.api.client.Client client;

    public Author() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(RestData.BASE_URI);
    }

    public List<Book> getBooks() {
        if (null == books) {
            WebResource resource = webResource;

            resource = resource.path(booksUrl);
            books = resource.get(new GenericType<List<Book>>() {});
        }

        return books;
    }

    public List<WebPage> getWebPages() {
        if (null == webPages) {
            WebResource resource = webResource;

            resource = resource.path(webPagesUrl);
            webPages = resource.get(new GenericType<List<WebPage>>() {});
        }

        return webPages;
    }

    public List<String> getTags() {
        if (null == tags) {
            WebResource resource = webResource;

            resource = resource.path(tagsUrl);
            tags = resource.get(new GenericType<List<String>>() {});
        }

        return tags;
    }

    public void close() {
        client.destroy();
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

    public int compareTo(Object t) {
        Author other = (Author) t;

        return name.compareTo(other.getName());
    }
}
