package com.zemiak.books.phone.domain;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.zemiak.books.phone.boundary.RestData;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Letter implements Comparable {
    private String letter;
    private String authorsUrl;
    private List<Author> authors = null;

    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;
    public Letter() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = com.sun.jersey.api.client.Client.create(config);
        webResource = client.resource(RestData.BASE_URI);
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

    public String getLetter() {
        return letter;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.letter);
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
        final Letter other = (Letter) obj;
        if (!Objects.equals(this.letter, other.letter)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Letter{" + "letter=" + letter + '}';
    }

    @Override
    public int compareTo(Object t) {
        Letter other = (Letter) t;

        return letter.compareTo(other.getLetter());
    }
}
