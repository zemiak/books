package com.zemiak.books.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Tag {
    private long id;
    private String name;
    private String authorsUrl;

    public Tag() {
    }

    public Tag(com.zemiak.books.domain.Tag tag, String baseUrl) {
        id = tag.getId();
        name = tag.getName();

        authorsUrl = baseUrl + "/authors/tag/" + name;
    }

    @Override
    public String toString() {
        return "Tag{" + "id=" + id + ", name=" + name + ", authorsUrl=" + authorsUrl + '}';
    }
}
