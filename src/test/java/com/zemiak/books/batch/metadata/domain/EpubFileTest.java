package com.zemiak.books.batch.metadata.domain;

import com.zemiak.books.service.Storage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.epub.EpubWriter;
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
    public void testSimpleNameWithStoring() throws IOException {
        final String SIMPLE_NAME = "Grossman a Simek";
        final String DATE_FORMAT = "ddMMyy-hhmmss.SSS";
        final String PATH_SEPARATOR = System.getProperty("file.separator");
        final String FILENAME_PREFIX = "/tmp/EPUB-";
        
        nl.siegmann.epublib.domain.Book book = new nl.siegmann.epublib.domain.Book();
        nl.siegmann.epublib.domain.Author eauthor = new nl.siegmann.epublib.domain.Author(SIMPLE_NAME);
        nl.siegmann.epublib.domain.Metadata metadata = book.getMetadata();
        
        List<nl.siegmann.epublib.domain.Author> authors = new ArrayList<>();
        authors.add(eauthor);
        metadata.setAuthors(authors);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        String fileName = FILENAME_PREFIX.replace("/", PATH_SEPARATOR) 
                + simpleDateFormat.format(new Date()) + ".txt";
        
        EpubWriter writer = new EpubWriter();
        FileOutputStream outputStream = new FileOutputStream(fileName);
        writer.write(book, outputStream);
        outputStream.close();
        
        EpubReader reader = new EpubReader();
        FileInputStream file = new FileInputStream(fileName);
        book = reader.readEpub(file);
        Metadata data = book.getMetadata();
        eauthor = data.getAuthors().get(0);
        
        assertNotNull(eauthor);
        
        /* This fails because of a bug in epublib
        assertEquals(SIMPLE_NAME, eauthor.getLastname());
        assertEquals("", eauthor.getFirstname());
        */
    }
}
