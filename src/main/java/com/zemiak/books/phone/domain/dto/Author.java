package com.zemiak.books.phone.domain.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Author {
    private List<String> tags = new ArrayList<>();
    private String name;
    private int id;
    private List<WebPage> webPages;

    private String booksUrl;
    private String tagsUrl;
    private String webPagesUrl;

    public Author() {
    }

    @Override
    public String toString() {
        return "Author{" + "tags=" + tags + ", name=" + name + ", id=" + id + ", webPages=" + webPages + ", booksUrl=" + booksUrl + '}';
    }

    public List<String> getTags() {
        return tags;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<WebPage> getWebPages() {
        return webPages;
    }

    public String getBooksUrl() {
        return booksUrl;
    }

    public String getTagsUrl() {
        return tagsUrl;
    }

    public String getWebPagesUrl() {
        return webPagesUrl;
    }
}
