package com.zemiak.books.domain;

import com.sun.jersey.api.client.WebResource;
import com.zemiak.books.boundary.RestData;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Book {
    private String mobiFileName;
    private String epubFileName;
    private String name;
    private int id;
    private boolean english;

    private String authorUrl;
    
    private com.sun.jersey.api.client.WebResource webResource;
    private com.sun.jersey.api.client.Client client;

    public Book() {
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
        return "Book{" + "mobiFileName=" + mobiFileName + ", epubFileName=" + epubFileName + ", name=" + name + ", id=" + id + ", authorUrl=" + authorUrl + '}';
    }
}
