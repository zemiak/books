package com.zemiak.books.boundary;

import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Book;
import com.zemiak.books.domain.Letter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class Collection {
    private List<Letter> letters = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    
    @PostConstruct
    void init() {
    }
    
    public List<Author> getAuthors() {
        return authors;
    }
    
    public Author getAuthor(int id) {
        for (Author a: authors) {
            if (id == a.getId()) {
                return a;
            }
        }
        
        return null;
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public List<Book> getBooksByExpression(String expr) {
        List<Book> found = new ArrayList<>();
        
        for (Book book: books) {
            if (book.getName().toLowerCase().contains(expr.toLowerCase())) {
                found.add(book);
            }
        }
        
        return found;
    }
    
    public List<Author> getAuthorsByExpression(String expr) {
        List<Author> found = new ArrayList<>();
        
        for (Author author: authors) {
            if (author.getName().toLowerCase().contains(expr.toLowerCase())) {
                found.add(author);
            }
        }
        
        return found;
    }

    public Book getBook(int id) {
        for (Book e: books) {
            if (id == e.getId()) {
                return e;
            }
        }
        
        return null;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public Letter getLetter(String letter) {
        for (Letter e: letters) {
            if (letter.equals(e.getLetter())) {
                return e;
            }
        }
        
        return null;
    }

    public List<Author> getAuthorsByTag(String tagName) {
        List<Author> found = new ArrayList<>();
        
        for (Author author: authors) {
            if (author.getTags().contains(tagName)) {
                found.add(author);
            }
        }
        
        return found;
    }

    public List<String> getTags() {
        List<String> found = new ArrayList<>();
        
        for (String e: tags) {
            if (! found.contains(e)) {
                found.add(e);
            }
        }
       
        return found;
    }

    public void refreshCollections() {
        for (Letter l: getLetters()) {
            for (Author a: l.getAuthors()) {
                authors.add(a);
                books.addAll(a.getBooks());
                tags.addAll(a.getTags());
            }
        }
    }

    public void deleteData() {
        letters = new ArrayList<>();
        authors = new ArrayList<>();
        books = new ArrayList<>();
        tags = new ArrayList<>();
    }

    public List<Book> getBooksByDateInterval(Date from, Date to) {
        List<Book> results = new ArrayList<>();
        
        for (Book book: getBooks()) {
            Date ftime = book.getModifiedTime();
            
            if ((ftime.after(from) || ftime.equals(from)) && ftime.before(to)) {
                results.add(book);
            }
        }
        
        return results;
    }

    public List<Book> getBooksBySource(Book.BookSource source) {
        List<Book> results = new ArrayList<>();
        
        for (Book book: getBooks()) {
            if (book.getSource().equals(source)) {
                results.add(book);
            }
        }
        
        return results;
    }
}
