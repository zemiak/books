package com.zemiak.books.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name = "Tag.findAll", query = "select a from Tag a"),
@NamedQuery(name = "Tag.findDistinct", query = "select distinct a.name from Tag a"),
@NamedQuery(name = "Tag.findByName", query = "select a from Tag a where a.name = :name")
})
public class Tag implements Serializable {
    private String name;
    
    @Id
    private int id;
    
    @ManyToMany(mappedBy="tags")
    private Set<Author> authors;
    
    public Tag() {
    }
    
    public Tag(String name) {
        this.name = name.trim().toLowerCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim().toLowerCase();
    }

   @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
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
        final Tag other = (Tag) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
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

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Tag{" + "name=" + name + ", id=" + id + '}';
    }
    
}
