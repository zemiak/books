package com.zemiak.books.phone.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class BookDTO {
    private String mobiFileName;
    private String epubFileName;
    private String name;
    private int id;
    private boolean english;

    private String authorUrl;

    public BookDTO() {
    }

    @Override
    public String toString() {
        return "BookDTO{" + "mobiFileName=" + mobiFileName + ", epubFileName=" + epubFileName + ", name=" + name + ", id=" + id + ", authorUrl=" + authorUrl + '}';
    }

    public String getMobiFileName() {
        return mobiFileName;
    }

    public String getEpubFileName() {
        return epubFileName;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isEnglish() {
        return english;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }
}
