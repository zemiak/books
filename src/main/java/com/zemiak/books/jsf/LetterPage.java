package com.zemiak.books.jsf;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Letter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

@ManagedBean
public class LetterPage {
    @ManagedProperty("#{param.id}")
    private String id;
    
    @Inject
    private Collection col;
    
    private Letter letter;
    
    @PostConstruct
    public void init() {
        letter = col.getLetter(id);
    }
    
    public Letter getLetter() {
        return letter;
    }
    
    public List<Author> getAuthors() {
        List<Author> authors = letter.getAuthors();
        
        Collections.sort(authors, new Comparator<Author>() {
            @Override
            public int compare(Author o1, Author o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        
        return authors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
