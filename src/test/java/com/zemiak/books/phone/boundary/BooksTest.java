package com.zemiak.books.phone.boundary;

import com.zemiak.books.phone.domain.Author;
import com.zemiak.books.phone.domain.Book;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class BooksTest {
    Books client;

    @Before
    public void init() {
        client = new Books();
    }

    @Test
    @Ignore
    public void findByAuthor() {
        Authors authorsClient = new Authors();
        List<Author> authors = authorsClient.findByExpression("Chandler, Raymond");
        Author author = authors.get(0);

        List<Book> result = client.findByAuthor(author.getId());
        assertFalse(result.isEmpty());
    }

    @Test
    @Ignore
    public void findByExpression() {
        List<Book> result = client.findByExpression("hell");
        assertFalse(result.isEmpty());
    }
    
    @Test
    @Ignore
    public void count() {
        assertTrue(client.count() > 0);
    }
}
