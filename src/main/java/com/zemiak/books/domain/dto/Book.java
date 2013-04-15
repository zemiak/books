package com.zemiak.books.domain.dto;

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

    public Book() {
    }

    public Book(com.zemiak.books.domain.Book book, String baseUrl) {
        mobiFileName = book.getMobiFileName();
        epubFileName = book.getEpubFileName();
        name = book.getName();
        id = book.getId();
        english = book.isEnglish();

        authorUrl = baseUrl + "/authors/" + book.getAuthor().getId();
    }

    @Override
    public String toString() {
        return "Book{" + "mobiFileName=" + mobiFileName + ", epubFileName=" + epubFileName + ", name=" + name + ", id=" + id + ", authorUrl=" + authorUrl + '}';
    }
}
