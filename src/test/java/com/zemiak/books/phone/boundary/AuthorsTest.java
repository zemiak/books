package com.zemiak.books.phone.boundary;

import com.zemiak.books.phone.domain.Author;
import com.zemiak.books.phone.domain.Tag;
import com.zemiak.books.phone.domain.WebPage;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class AuthorsTest {
    Authors client;

    @Before
    public void init() {
        client = new Authors();
    }

    @Test
    @Ignore
    public void findByExpression() {
        List<Author> result = client.findByExpression("hell");
        assertTrue(! result.isEmpty());
    }

    @Test
    @Ignore
    public void findByLetter() {
        List<Author> result = client.findByLetter("C");
        assertTrue(! result.isEmpty());
    }

    @Test
    @Ignore
    public void getTags() {
        List<Author> result = client.findByExpression("Chandler, Raymond");
        assertTrue(! result.isEmpty());

        Author author = result.get(0);
        List<Tag> pages = client.getTags(author.getId());
        assertTrue(! pages.isEmpty());
    }

    @Test
    @Ignore
    public void getWebPages() {
        List<Author> result = client.findByExpression("Clarke, Arthur Charles");
        assertTrue(! result.isEmpty());

        Author author = result.get(0);
        List<WebPage> pages = client.getWebPages(author.getId());
        assertTrue(! pages.isEmpty());
    }

    @Test
    @Ignore
    public void find() {
        List<Author> result = client.findByExpression("Clarke, Arthur Charles");
        assertTrue(! result.isEmpty());

        Author author = result.get(0);
        Author author2 = client.find(author.getId());

        assertNotNull(author2);
        assertEquals(author, author2);
    }

    @Test
    @Ignore
    public void all() {
        List<Author> result = client.all();
        assertTrue(result.size() > 50);
    }

    @Test
    @Ignore
    public void findByTag() {
        List<Author> result = client.findByTag("crime");
        assertTrue(result.size() > 5);
    }
    
    @Test
    @Ignore
    public void count() {
        assertTrue(client.count() > 0);
    }
    
    @Test
    @Ignore
    public void countDocumented() {
        assertTrue(client.countDocumented() > 0);
    }
    
    @Test
    @Ignore
    public void countTagged() {
        assertTrue(client.countTagged() > 0);
    }
}
