package com.zemiak.books.phone.boundary;

import com.zemiak.books.phone.domain.Letter;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LettersTest {
    Letters client;

    @Before
    public void init() {
        client = new Letters();
    }

    @Test
    public void find() {
        Letter result = client.find("S");
        assertNotNull(result);
    }

    @Test
    public void all() {
        List<Letter> result = client.all();
        assertFalse(result.isEmpty());
    }
}
