package com.zemiak.books.phone.domain;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Letter implements Comparable, AutoCloseable {
    private String letter;
    private String authorsUrl;
    private List<Author> authors = null;

    @XmlTransient
    private Client client = null;
    
    public Letter() {
    }
    
    @XmlTransient
    private static final Logger LOG = Logger.getLogger(Letter.class.getName());

    public List<Author> getAuthors() {
        if (null == authors) {
            WebResource resource = getClient().resource(authorsUrl);
            long time = System.currentTimeMillis();
            authors = resource.get(new GenericType<List<Author>>(){});
            System.err.println("getAuthors/REST: " + (System.currentTimeMillis() - time) 
                    + "ms, for " + authors.size() + " authors");
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
