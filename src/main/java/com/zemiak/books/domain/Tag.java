package com.zemiak.books.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name = "Tag.findAll", query = "select a from Tag a"),
@NamedQuery(name = "Tag.findDistinct", query = "select distinct a.name from Tag a"),
@NamedQuery(name = "Tag.findByName", query = "select a from Tag a where a.name = :name")
})
public class Tag implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    
    private String name;
    
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

    @Override
    public String toString() {
        return "Tag{" + "name=" + name + '}';
    }
    
}
