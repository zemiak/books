package com.zemiak.books.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name = "Book.findAll", query = "select a from Book a"),
@NamedQuery(name = "Book.findByExpression", query = "select a from Book a where lower(a.name) like :expr")
})
public class Book implements Serializable {
    private String mobiFileName;
    private String epubFileName;
    private String name;

    @ManyToOne
    private Author author;

    @Id
    private int id;

    public Book() {
    }

    public String getMobiFileName() {
        return mobiFileName;
    }

    public void setMobiFileName(String mobiFileName) {
        this.mobiFileName = mobiFileName;
    }

    public String getEpubFileName() {
        return epubFileName;
    }

    public void setEpubFileName(String epubFileName) {
        this.epubFileName = epubFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        id = hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.mobiFileName);
        hash = 83 * hash + Objects.hashCode(this.epubFileName);
        hash = 83 * hash + Objects.hashCode(this.name);
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
        final Book other = (Book) obj;
        if (!Objects.equals(this.mobiFileName, other.mobiFileName)) {
            return false;
        }
        if (!Objects.equals(this.epubFileName, other.epubFileName)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book{" + "mobiFileName=" + mobiFileName + ", epubFileName=" + epubFileName + ", name=" + name + ", author=" + author + ", id=" + id + '}';
    }


}
