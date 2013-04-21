package com.zemiak.books.domain.dto;

import java.util.HashSet;
import java.util.List;
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

    public Author() {
    }

    public Author(com.zemiak.books.domain.Author author, String baseUrl) {
        
        name = author.getName();
        id = author.getId();
        
        for (com.zemiak.books.domain.WebPage item: author.getWebPages()) {
            webPages.add(new WebPage(item, baseUrl));
        }

        for (com.zemiak.books.domain.Tag tag: author.getTags()) {
            tags.add(tag.getName());
        }

        booksUrl = baseUrl + "/books/author/" + id;
    }

    @Override
    public String toString() {
        return "Author{" + "tags=" + tags + ", name=" + name + ", id=" + id + ", webPages=" + webPages + ", booksUrl=" + booksUrl + '}';
    }
    
}
