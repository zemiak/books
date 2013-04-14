package com.zemiak.books.jsf;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Book;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

@ManagedBean
public class BookPage {
    @ManagedProperty("#{param.id}")
    private int id;
    
    @Inject
    private Collection col;
    
    private Book book;
    
    @PostConstruct
    public void init() {
        book = col.getBook(id);
    }
    
    public Book getBook() {
        return book;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
