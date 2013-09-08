package com.zemiak.books.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Author implements Serializable, Comparable {
    private List<String> tags;

    private List<Book> books;
    
    private List<WebPage> webPages;

    private String name;

    private int id;

    public Author() {
        tags = new ArrayList<>();
        books = new ArrayList<>();
    }

    public void setId() {
        id = hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.name);
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
        return true;
    }

    @Override
    public String toString() {
        return "Author{name=" + name + ", id=" + id + '}';
    }

    public List<WebPage> getWebPages() {
        return webPages;
    }

    public void setWebPages(List<WebPage> webPages) {
        this.webPages = webPages;
    }

    @Override
    public int compareTo(Object o) {
        Author other = (Author) o;
        
        return this.getName().compareTo(other.getName());
    }
}
