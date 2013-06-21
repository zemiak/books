package com.zemiak.books.phone.boundary;

import com.zemiak.books.phone.boundary.Tags;
import com.zemiak.books.phone.domain.Tag;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TagsTest {
    Tags client;
    static private int TAG_ID_CRIME = 113;

    @Before
    public void init() {
        client = new Tags();
    }

    @Test
    public void findByName() {
        Tag result = client.findByName("crime");
        assertNotNull(result);
    }

    @Test
    public void find() {
        Tag result = client.find(TAG_ID_CRIME);
        assertNotNull(result);
    }

    @Test
    public void all() {
        List<Tag> result = client.all();
        assertFalse(result.isEmpty());
    }

    @Test
    public void findByDistinct() {
        List<Tag> result = client.findByDistinct();
        assertFalse(result.isEmpty());
    }
}
