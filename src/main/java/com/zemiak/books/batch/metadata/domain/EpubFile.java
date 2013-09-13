package com.zemiak.books.batch.metadata.domain;

import com.zemiak.books.domain.Book;
import java.io.FileInputStream;
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
    
    private class AuthorName {
        private String first, last;
        
        public AuthorName(String composed) {
            if (composed.contains(",")) {
                String[] parts = composed.split(",");
                
                last = parts[0].trim();
                first = parts[1].trim();
            } else {
                last = composed;
                first = "";
            }
        }

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }
        
        @Override
        public String toString() {
            if (first.isEmpty()) {
                return last;
            }
            
            return last + ", " + first;
        }
        
        public nl.siegmann.epublib.domain.Author getAuthor() {
            return new nl.siegmann.epublib.domain.Author(first, last);
        }
    }
    
    public EpubFile(Book book) throws IOException {
        this.book = book;
        
        EpubReader epubReader = new EpubReader();
        
        if (null == book.getEpubFileName()) {
            LOG.log(Level.SEVERE, "epub is null. Book: {0}", book);
            return;
        }

        FileInputStream file = new FileInputStream(book.getEpubFileName());
        ebook = epubReader.readEpub(file);
        Metadata data = ebook.getMetadata();

        String title = (data.getTitles().isEmpty() ? "" : data.getTitles().get(0));
        compare(book.getName().trim(), title.trim(), "name");
        
        String authorName = "";
        AuthorName should = new AuthorName(book.getAuthor().getName());
        
        if (! data.getAuthors().isEmpty()) {
            nl.siegmann.epublib.domain.Author author = data.getAuthors().get(0);

            if (! author.getFirstname().isEmpty()) {
                authorName = author.getLastname() + ", " + author.getFirstname();
            } else {
                authorName = author.getLastname();
            }
        }
        compare(should.toString(), authorName, "author");
    }
    
    public boolean isUpToDate() {
        return (!different);
    }
    
    private void compare(Object should, Object is, String fieldName) {
        if (! should.equals(is)) {
            different = true;
            
            LOG.log(Level.INFO, 
                    "Book {0}: different field {1}. Should be: x" + should + "x is: x" + is + "x", 
                    new Object[]{book.getEpubFileName(), fieldName});
        }
    }
    
    public void update() {
        Metadata data = ebook.getMetadata();
        
        List<String> titles = new ArrayList<>();
        titles.add(book.getName());
        data.setTitles(titles);
        
        List<nl.siegmann.epublib.domain.Author> authors = new ArrayList<>();
        AuthorName name = new AuthorName(book.getAuthor().getName());
        
        authors.add(name.getAuthor());
        data.setAuthors(authors);
        
        ebook.setMetadata(data);
    }
    
    public void write() throws IOException {
        EpubWriter epubWriter = new EpubWriter();
        
        epubWriter.write(ebook, new FileOutputStream(book.getEpubFileName()));
    }
    
    @Override
    public String toString() {
        return book.getEpubFileName();
    }

    public Book getBook() {
        return book;
    }
}
