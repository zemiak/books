package com.zemiak.books.client.domain;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.net.URL;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class WebPage implements Comparable, AutoCloseable {
    private String name;
    private URL url;
    private int id;

    private String authorUrl;
    private Author author = null;

    @XmlTransient
    private Client client = null;
    
    public WebPage() {
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

    public URL getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.url);
        hash = 79 * hash + this.id;
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
        final WebPage other = (WebPage) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WebPage{" + "name=" + name + ", url=" + url + ", id=" + id + '}';
    }

    @Override
    public int compareTo(Object t) {
        WebPage other = (WebPage) t;

        return name.compareTo(other.getName());
    }
}
