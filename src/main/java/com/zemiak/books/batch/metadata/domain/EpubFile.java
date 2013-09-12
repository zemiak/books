package com.zemiak.books.batch.metadata.domain;

import com.zemiak.books.domain.Book;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.epub.EpubWriter;

/**
 *
 * @author vasko
 */
public class EpubFile {
    private static final Logger LOG = Logger.getLogger(EpubFile.class.getName());
    
    private Book book;
    private nl.siegmann.epublib.domain.Book ebook;
    private boolean different = false;
    
    public EpubFile(Book book) throws IOException {
        this.book = book;
        
        EpubReader epubReader = new EpubReader();
        this.ebook = epubReader.readEpub(new FileInputStream(book.getEpubFileName()));

        Metadata data = ebook.getMetadata();

        String title = (data.getTitles().isEmpty() ? "" : data.getTitles().get(0));
        compare(book.getName(), title, "Name");

        String authorName = "";
        if (! data.getAuthors().isEmpty()) {
            nl.siegmann.epublib.domain.Author author = data.getAuthors().get(0);

            if (! author.getFirstname().isEmpty()) {
                authorName = author.getLastname() + ", " + author.getFirstname();
            } else {
                authorName = author.getLastname();
            }
        }
        compare(book.getAuthor().getName(), authorName, "Author");
    }
    
    public boolean isUpToDate() {
        return (!different);
    }
    
    private void compare(Object should, Object is, String fieldName) {
        if (! should.equals(is)) {
            different = true;
            
            LOG.log(Level.INFO, "Book {0}: different field {1}. Should be: {2}, is: {3}", 
                    new Object[]{book.getId(), fieldName, should, is});
        }
    }
    
    public void updateAndWrite() throws IOException {
        Metadata data = ebook.getMetadata();
        
        List<String> titles = new ArrayList<>();
        titles.add(book.getName());
        data.setTitles(titles);
        
        List<nl.siegmann.epublib.domain.Author> authors = new ArrayList<>();
        nl.siegmann.epublib.domain.Author author;
        String name = book.getAuthor().getName();
        
        if (! name.contains(",")) {
            author = new nl.siegmann.epublib.domain.Author(name);
        } else {
            String[] parts = name.split(",");
            
            // firstName, lastName
            author = new nl.siegmann.epublib.domain.Author(parts[1].trim(), parts[0].trim());
        }
        authors.add(author);
        data.setAuthors(authors);
        
        ebook.setMetadata(data);
        
        EpubWriter epubWriter = new EpubWriter();
        boolean result = true;
        
        epubWriter.write(ebook, new FileOutputStream(book.getEpubFileName()));
    }
}
