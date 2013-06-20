package com.zemiak.books.phone.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class TagDTO {
    private long id;
    private String name;
    private String authorsUrl;

    public TagDTO() {
    }

    @Override
    public String toString() {
        return "TagDTO{" + "id=" + id + ", name=" + name + ", authorsUrl=" + authorsUrl + '}';
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthorsUrl() {
        return authorsUrl;
    }
}
