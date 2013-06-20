package com.zemiak.books.backend.systemtest;

import com.zemiak.books.phone.boundary.Authors;
import com.zemiak.books.phone.boundary.Books;
import com.zemiak.books.phone.domain.Author;
import com.zemiak.books.phone.domain.Book;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BooksTest {
    Books client;

    @Before
    public void init() {
        client = new Books();
    }

    @Test
    public void findByAuthor() {
        Authors authorsClient = new Authors();
        List<Author> authors = authorsClient.findByExpression("Chandler, Raymond");
        Author author = authors.get(0);

        List<Book> result = client.findByAuthor(author.getId());
        assertFalse(result.isEmpty());
    }

    @Test
    public void findByExpression() {
        List<Book> result = client.findByExpression("hell");
        assertFalse(result.isEmpty());
    }
}
