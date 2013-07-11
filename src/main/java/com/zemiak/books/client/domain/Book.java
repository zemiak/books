package com.zemiak.books.client.domain;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Book implements Comparable, AutoCloseable {
    private String mobiFileName;
    private String epubFileName;
    private String name;
    private int id;
    private boolean english;

    private String authorUrl;
    private Author author = null;

    @XmlTransient
    private Client client = null;

    public Book() {
    }

    public Author getAuthor() {
        if (null == author) {
            WebResource resource = getClient().resource(authorUrl);
            author = resource.get(Author.class);
        }

        return author;
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

    public String getMobiFileName() {
        return mobiFileName;
    }

    public String getEpubFileName() {
        return epubFileName;
    }

    public boolean isEnglish() {
        return english;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + this.id;
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
        final Book other = (Book) obj;
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
        return "Book{" + "name=" + name + ", id=" + id + ", english=" + english + '}';
    }

    @Override
    public int compareTo(Object t) {
        Book other = (Book) t;

        return name.compareTo(other.getName());
    }
}
