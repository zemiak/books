package com.zemiak.books.backend.systemtest;

import com.zemiak.books.phone.boundary.Statistics;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
    Statistics client;

    @Before
    public void init() {
        client = new Statistics();
    }

    @Test
    public void getAuthors() {
        assertTrue(client.getAuthors() > 0);
    }

    @Test
    public void getAuthorsDocumented() {
        assertTrue(client.getAuthorsDocumented() > 0);
    }

    @Test
    public void getAuthorsTagged() {
        assertTrue(client.getAuthorsTagged() > 0);
    }

    @Test
    public void getBooks() {
        assertTrue(client.getBooks() > 0);
    }
}
