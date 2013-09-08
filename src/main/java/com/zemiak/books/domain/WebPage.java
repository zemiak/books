package com.zemiak.books.domain;

import java.io.Serializable;
import java.net.URL;
import java.util.Objects;

public class WebPage implements Serializable, Comparable {
    private int id;
    
    private String name;
    private URL url;
    
    private Author author;
    
    public WebPage() {
        
    }

    public WebPage(String name, URL url, Author author) {
        this.name = name;
        this.url = url;
        this.author = author;
    }
    
    public int getId() {
        return id;
    }

    public void setId() {
        this.id = hashCode();
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.url);
        hash = 61 * hash + this.author.hashCode();
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WebPage{" + "id=" + id + ", name=" + name + ", url=" + url + ", author=" + author + '}';
    }
    
    public String getHref() {
        return url.toString();
    }

    @Override
    public int compareTo(Object o) {
        WebPage other = (WebPage) o;
        
        return this.getName().compareTo(other.getName());
    }
}
