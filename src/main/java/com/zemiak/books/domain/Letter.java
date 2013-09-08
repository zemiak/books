package com.zemiak.books.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Letter implements Serializable, Comparable {
    private String letter;

    private List<Author> authors;

    public Letter() {
        authors = new ArrayList<>();
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter.toUpperCase();
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Letter{" + "letter=" + letter + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.letter);
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
        return true;
    }

    @Override
    public int compareTo(Object o) {
        Letter other = (Letter) o;
        
        return this.getLetter().compareTo(other.getLetter());
    }
}
