package com.zemiak.books.client.boundary;

import com.zemiak.books.client.domain.Author;
import com.zemiak.books.client.domain.Book;
import com.zemiak.books.client.domain.Letter;
import com.zemiak.books.client.domain.Tag;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author vasko
 */
@Stateless
public class CachedCollection {
    @Inject
    private Collection col;
    
    @Inject
    private Cache cache;
    
    @PostConstruct
    public void init() {
    }
    
    public List<Author> getAuthors() {
        final String key = "getAuthors";
        List<Author> ret = (List<Author>) cache.get(key);
        
        if (null == ret) {
            ret = col.getAuthors();
            cache.set(key, ret);
        }
        
        return ret;
    }
    
    public Author getAuthor(int id) {
        final String key = "getAuthor." + id;
        Author ret = (Author) cache.get("getAuthor:" + id);
        
        if (null == ret) {
            ret = col.getAuthor(id);
            cache.set(key, ret);
        }
        
        return ret;
    }
    
    public List<Author> getAuthorsByExpression(String expr) {
        final String key = "getAuthorsByExpression." + expr;
        List<Author> ret = col.getAuthorsByExpression(expr);
        
        if (null == expr) {
            ret = col.getAuthorsByExpression(expr);
            cache.set(key, ret);
        }
        
        return ret;
    }
    
    public List<Author> getAuthorsByTag(String tagName) {
        final String key = "getAuthorsByTag." + tagName;
        List<Author> ret = (List<Author>) cache.get(key);
        
        if (null == ret) {
            ret = col.getAuthorsByTag(tagName);
            cache.set(key, ret);
        }
        
        return ret;
    }

    public Book getBook(int id) {
        final String key = "getBook." + id;
        Book ret = (Book) cache.get(key);
        
        if (null == ret) {
            ret = col.getBook(id);
            cache.set(key, ret);
        }
        
        return ret;
    }
    
    public List<Book> getBooks() {
        final String key = "getBooks";
        List<Book> ret = (List<Book>) cache.get(key);
        
        if (null == ret) {
            ret = col.getBooks();
            cache.set(key, ret);
        }
        
        return ret;
    }
    
    public List<Book> getBooksByExpression(String expr) {
        final String key = "getBooksByExpression." + expr;
        List<Book> ret = (List<Book>) cache.get(key);
        
        if (null == ret) {
            ret = col.getBooksByExpression(expr);
            cache.set(key, ret);
        }
        
        return ret;
    }

    public List<Letter> getLetters() {
        final String key = "getLetters";
        List<Letter> ret = (List<Letter>) cache.get(key);
        
        if (null == ret) {
            ret = col.getLetters();
            cache.set(key, ret);
        }
        
        return ret;
    }

    public Letter getLetter(String letter) {
        final String key = "getLetter." + letter;
        Letter ret = (Letter) cache.get(key);
        
        if (null == ret) {
            ret = col.getLetter(letter);
            cache.set(key, ret);
        }
        
        return ret;
    }

    public List<Tag> getTags() {
        final String key = "getTags";
        List<Tag> ret = (List<Tag>) cache.get(key);
        
        if (null == ret) {
            ret = col.getTags();
            cache.set(key, ret);
        }
        
        return ret;
    }

    public List<Tag> getDistinctTags() {
        final String key = "getDistinctTags";
        List<Tag> ret = (List<Tag>) cache.get(key);
        
        if (null == ret) {
            ret = col.getDistinctTags();
            cache.set(key, ret);
        }
        
        return ret;
    }

    public Tag getTag(String name) {
        final String key = "getTag." + name;
        Tag ret = (Tag) cache.get(key);
        
        if (null == ret) {
            ret = col.getTag(name);
            cache.set(key, ret);
        }
        
        return ret;
    }
    
    public int getAuthorsCount() {
        final String key = "getAuthorsCount";
        Integer ret = (Integer) cache.get(key);
        
        if (null == ret) {
            ret = col.getAuthorsCount();
            cache.set(key, ret);
        }
        
        return ret;
    }
    
    public int getAuthorsDocumentedCount() {
        final String key = "getAuthorsDocumentedCount";
        Integer ret = (Integer) cache.get(key);
        
        if (null == ret) {
            ret = col.getAuthorsDocumentedCount();
            cache.set(key, ret);
        }
        
        return ret;
    }
    
    public int getAuthorsTaggedCount() {
        final String key = "getAuthorsTaggedCount";
        Integer ret = (Integer) cache.get(key);
        
        if (null == ret) {
            ret = col.getAuthorsTaggedCount();
            cache.set(key, ret);
        }
        
        return ret;
    }
    
    public int getBooksCount() {
        final String key = "getBooksCount";
        Integer ret = (Integer) cache.get(key);
        
        if (null == ret) {
            ret = col.getBooksCount();
            cache.set(key, ret);
        }
        
        return ret;
    }
}
