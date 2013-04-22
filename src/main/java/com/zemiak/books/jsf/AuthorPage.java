package com.zemiak.books.jsf;

import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Book;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AuthorPage {
    private Author author;
    
    public Author getAuthor() {
        return author;
    }
    
    public List<Book> getBooks() {
        if (null == author) return null;
        
        List<Book> books = author.getBooks();
        
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        
        return books;
    }
    
    public String getLetter() {
        return author.getName().substring(0, 1).toUpperCase();
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
