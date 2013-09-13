package com.zemiak.books.batch.metadata.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vasko
 */
public class EpubFileTest {
    
    public EpubFileTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSimpleName() {
        nl.siegmann.epublib.domain.Author eauthor = new nl.siegmann.epublib.domain.Author("Grossman a Simek");

        assertEquals(eauthor.getFirstname(), "");
        assertEquals(eauthor.getLastname(), "Grossman a Simek");
        
    }
    
    @Test
    public void testComposedName() {
        nl.siegmann.epublib.domain.Author eauthor = new nl.siegmann.epublib.domain.Author("First", "Last");
        
        assertEquals(eauthor.getFirstname(), "First");
        assertEquals(eauthor.getLastname(), "Last");
    }
    
    @Test
    public void testStarredName() {
        nl.siegmann.epublib.domain.Author eauthor = new nl.siegmann.epublib.domain.Author("*", "Grossman a Simek");
        
        assertEquals(eauthor.getFirstname(), "*");
        assertEquals(eauthor.getLastname(), "Grossman a Simek");
    }
    
    @Test
    public void testSimpleNameWithStoring() {
        nl.siegmann.epublib.domain.Book book = new nl.siegmann.epublib.domain.Book();
        nl.siegmann.epublib.domain.Author eauthor = new nl.siegmann.epublib.domain.Author("Grossman a Simek");
        nl.siegmann.epublib.domain.Metadata metadata = book.getMetadata();
        
        List<nl.siegmann.epublib.domain.Author> authors = new ArrayList<>();
        authors.add(eauthor);
        metadata.setAuthors(authors);
        
        
    }
}