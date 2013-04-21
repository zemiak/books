package com.zemiak.books.jsf;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Book;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
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

    public void setText(String text) {
        this.text = text.toLowerCase();
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
    
    public String getHighlightedValue(String value) {
        String highlighted = "";
        int i = value.toLowerCase().indexOf(text);
        
        if (i > 0) {
            highlighted = value.substring(0, i);
        }
        
        // Gold color
        highlighted += "<span style=\"background-color: #FFD700;\">"
                + value.substring(i, i + text.length())
                + "</span>";
        
        if (value.length() > i + text.length()) {
            highlighted += value.substring(i + text.length(), value.length());
        }
        
        return highlighted;
    }
}
