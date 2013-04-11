package com.zemiak.books.boundary;

import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Book;
import com.zemiak.books.domain.Letter;
import com.zemiak.books.domain.Tag;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

@Stateless
public class Collection {
    @PersistenceContext
    private EntityManager em;
    
    public List<Author> getAuthors() {
        return em.createNamedQuery("Author.findAll").getResultList();
    }
    
    public Author getAuthor(String id) {
        return em.find(Author.class, id);
    }
    
    public List<Book> getBooks() {
        return em.createNamedQuery("Book.findAll").getResultList();
    }
    
    public Book getBook(String id) {
        return em.find(Book.class, id);
    }
    
    public List<Letter> getLetters() {
        return em.createNamedQuery("Letter.findAll").getResultList();
    }
    
    public Letter getLetter(String id) {
        return em.find(Letter.class, id);
    }
    
    public List<Tag> getTags() {
        return em.createNamedQuery("Tag.findAll").getResultList();
    }
    
    public List<Tag> getDistinctTags() {
        return em.createNamedQuery("Tag.findDistinct").getResultList();
    }
    
    public Tag getTag(String name) {
        return em.find(Tag.class, name);
    }
    
    public void persist(Object entity) {
        em.persist(entity);
    }
}
