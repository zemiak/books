package com.zemiak.books.domain;

import com.sun.jersey.api.client.WebResource;
import com.zemiak.books.boundary.RestData;
import java.net.URL;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class WebPage {
    private String name;
    private URL url;
    private int id;

    private String authorUrl;

    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;
    public WebPage() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(RestData.BASE_URI);
    }
    
    public Author getAuthor() {
        WebResource resource = webResource;
        
        resource = resource.path(authorUrl);
        return resource.get(Author.class);
    }

    @Override
    public String toString() {
        return "WebPage{" + "name=" + name + ", url=" + url + ", id=" + id + ", authorUrl=" + authorUrl + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.url);
        hash = 19 * hash + this.id;
        hash = 19 * hash + Objects.hashCode(this.authorUrl);
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
        if (!Objects.equals(this.authorUrl, other.authorUrl)) {
            return false;
        }
        return true;
    }
}
