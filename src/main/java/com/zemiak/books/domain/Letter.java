package com.zemiak.books.domain;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Letter {
    private String letter;
    private String authorsUrl;

    public Letter() {
    }

    @Override
    public String toString() {
        return "Letter{" + "letter=" + letter + ", authorsUrl=" + authorsUrl + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.letter);
        hash = 37 * hash + Objects.hashCode(this.authorsUrl);
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
        final Letter other = (Letter) obj;
        if (!Objects.equals(this.letter, other.letter)) {
            return false;
        }
        if (!Objects.equals(this.authorsUrl, other.authorsUrl)) {
            return false;
        }
        return true;
    }
}
