package com.zemiak.books.domain.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tag {
    private long id;
    private String name;
    private String authorsUrl;

    public Tag() {
    }

    public Tag(com.zemiak.books.domain.Tag tag) {
        id = tag.getId();
        name = tag.getName();

        authorsUrl = "/webservices/authors/?tag=" + name;
    }

    @Override
    public String toString() {
        return "Tag{" + "id=" + id + ", name=" + name + ", authorsUrl=" + authorsUrl + '}';
    }
}
