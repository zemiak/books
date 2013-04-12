package com.zemiak.books.domain.dto;

import com.zemiak.books.domain.Tag;
import java.io.Serializable;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Author {
    private URL wikipedia;
    private URL originalSite;
    private URL bibliography;
    private URL guttenberg;
    private Set<String> tags = new HashSet<>();
    private String name;
    private int id;

    private String booksUrl;

    public Author() {
    }

    public Author(com.zemiak.books.domain.Author author) {
        wikipedia = author.getWikipedia();
        originalSite = author.getOriginalSite();
        bibliography = author.getBibliography();
        guttenberg = author.getGuttenberg();
        name = author.getName();
        id = author.getId();

        for (Tag tag: author.getTags()) {
            tags.add(tag.getName());
        }

        booksUrl = "/webservices/books/author/?id=" + id;
    }

    @Override
    public String toString() {
        return "Author{" + "wikipedia=" + wikipedia + ", originalSite=" + originalSite + ", bibliography=" + bibliography + ", guttenberg=" + guttenberg + ", tags=" + tags + ", name=" + name + ", id=" + id + ", booksUrl=" + booksUrl + '}';
    }
}
