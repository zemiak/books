package com.zemiak.books.domain;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.zemiak.books.boundary.RestData;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Author {
    private Set<String> tags = new HashSet<>();
    private String name;
    private int id;
    private List<WebPage> webPages;

    private String booksUrl;
    
    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;

    public Author() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(RestData.BASE_URI);
    }
    
    public List<Book> getBooks() {
        WebResource resource = webResource;
        
        resource = resource.path(booksUrl);
        return resource.get(new GenericType<List<Book>>() {});
    }
    
    // getTags
    // getWebPages
    
    public void close() {
        client.destroy();
    }

    @Override
    public String toString() {
        return "Author{" + "tags=" + tags + ", name=" + name + ", id=" + id + ", webPages=" + webPages + ", booksUrl=" + booksUrl + '}';
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<WebPage> getWebPages() {
        return webPages;
    }

    public void setWebPages(List<WebPage> webPages) {
        this.webPages = webPages;
    }

    public String getBooksUrl() {
        return booksUrl;
    }

    public void setBooksUrl(String booksUrl) {
        this.booksUrl = booksUrl;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.booksUrl);
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
        if (!Objects.equals(this.booksUrl, other.booksUrl)) {
            return false;
        }
        return true;
    }
}
