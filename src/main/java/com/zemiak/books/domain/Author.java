package com.zemiak.books.domain;

import java.io.Serializable;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
@NamedQuery(name = "Author.findAll", query = "select a from Author a")
})
public class Author implements Serializable {
    private URL wikipedia;
    private URL originalSite;
    private URL bibliography;
    private URL guttenberg;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="author_tag")
    private Set<Tag> tags;
    
    @OneToMany(mappedBy="author", cascade=CascadeType.PERSIST)
    private Set<Book> books;
    
    private String name;
    
    @Id
    private int id;

    public Author() {
        tags = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public URL getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(URL wikipedia) {
        this.wikipedia = wikipedia;
    }

    public URL getOriginalSite() {
        return originalSite;
    }

    public void setOriginalSite(URL originalSite) {
        this.originalSite = originalSite;
    }

    public URL getBibliography() {
        return bibliography;
    }

    public void setBibliography(URL bibliography) {
        this.bibliography = bibliography;
    }

    public URL getGuttenberg() {
        return guttenberg;
    }

    public void setGuttenberg(URL guttenberg) {
        this.guttenberg = guttenberg;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
    
    public int getId() {
        return id;
    }

    public void setId() {
        id = hashCode();
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.name);
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
        final Author other = (Author) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Author{" + "wikipedia=" + wikipedia + ", originalSite=" + originalSite + ", bibliography=" + bibliography + ", guttenberg=" + guttenberg + ", name=" + name + ", id=" + id + '}';
    }
    
    
}
