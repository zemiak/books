package com.zemiak.books.jsf;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Book;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

@ManagedBean
public class SearchPage {
    @ManagedProperty("#{param.text}")
    private String text;
    
    @Inject
    private Collection col;
    
    private List<Author> authors;
    private List<Book> books;
    
    public String getText() {
        return text;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Book> getBooks() {
        return books;
    }
    
    @PostConstruct
    public void search() {
        authors = col.getAuthorsByExpression(text);
        books = col.getBooksByExpression(text);
    }
}
