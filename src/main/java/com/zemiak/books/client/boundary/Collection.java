package com.zemiak.books.client.boundary;

import com.zemiak.books.client.domain.Author;
import com.zemiak.books.client.domain.Book;
import com.zemiak.books.client.domain.Letter;
import com.zemiak.books.client.domain.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Collection {
    @Inject
    Authors authors;
    
    @Inject
    Books books;
    
    @Inject
    Letters letters;
    
    @Inject
    Tags tags;
    
    public Collection() {
    }
    
    @PostConstruct
    public void init() {
    }
    
    public List<Author> getAuthors() {
        return authors.all();
    }
    
    public Author getAuthor(int id) {
        return authors.find(id);
    }
    
    public List<Author> getAuthorsByExpression(String expr) {
        return authors.findByExpression(expr);
    }
    
    public List<Author> getAuthorsByTag(String tagName) {
        return authors.findByTag(tagName);
    }

    public Book getBook(int id) {
        return books.find(String.valueOf(id));
    }
    
    public List<Book> getBooks() {
        return books.all();
    }
    
    public List<Book> getBooksByExpression(String expr) {
        return books.findByExpression(expr);
    }

    public List<Letter> getLetters() {
        return letters.all();
    }

    public Letter getLetter(String letter) {
        return letters.find(letter);
    }

    public List<Tag> getTags() {
        List<Tag> tagList = new ArrayList<>();
        List<String> tagNames = new ArrayList<>();
        
        for (Object e: tags.all()) {
            Tag tag = (Tag) e;

            if (! tagNames.contains(tag.getName())) {
                tagList.add(tag);
                tagNames.add(tag.getName());
            }
        }

        return tagList;
    }

    public Tag getTag(String name) {
        return new Tag(name);
    }
    
    public int getAuthorsCount() {
        return authors.count();
    }
    
    public int getAuthorsDocumentedCount() {
        return authors.countDocumented();
    }
    
    public int getAuthorsTaggedCount() {
        return authors.countTagged();
    }
    
    public int getBooksCount() {
        return books.count();
    }
}
