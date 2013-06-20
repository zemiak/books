package com.zemiak.books.phone.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class LetterDTO {
    private String letter;
    private String authorsUrl;

    public LetterDTO() {
    }

    @Override
    public String toString() {
        return "LetterDTO{" + "letter=" + letter + ", authorsUrl=" + authorsUrl + '}';
    }

    public String getLetter() {
        return letter;
    }

    public String getAuthorsUrl() {
        return authorsUrl;
    }
}
