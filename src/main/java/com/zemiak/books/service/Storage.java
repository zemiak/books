package com.zemiak.books.service;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Book;
import com.zemiak.books.domain.CacheClearEvent;
import com.zemiak.books.domain.Letter;
import com.zemiak.books.domain.WebPage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Singleton
@Startup
@DependsOn("Configuration")
public class Storage {
    private static final Logger LOG = Logger.getLogger(Storage.class.getName());
    public static final String PATH_SEPARATOR = System.getProperty("file.separator");

    @Inject
    private Collection col;
    
    @Inject
    private Configuration conf;
    
    @Inject
    private Statistics stat;

    @PostConstruct
    public void readData() {
        refreshData();
    }

    public void readDataFromFiles(File mainDir) {
        col.deleteData();
        
        for (String letter : mainDir.list()) {
            File letterFile = new File(mainDir.getAbsolutePath() + PATH_SEPARATOR + letter);

            if (letterFile.isDirectory()) {
                readLetter(letterFile);
            }
        }
    }

    private void readLetter(File letterFile) {
        if (letterFile.getName().length() == 1) {
            Letter letter = new Letter();
            letter.setLetter(letterFile.getName());
            letter.setAuthors(readAuthors(letterFile));

            col.getLetters().add(letter);
        }
    }

    private List<Author> readAuthors(File letterFile) {
        List<Author> authors = new ArrayList<>();

        for (String author: letterFile.list()) {
            File authorFile = new File(letterFile.getAbsolutePath() + PATH_SEPARATOR + author);

            if (authorFile.isDirectory() && !authorFile.getName().startsWith(".")) {
                authors.add(readAuthor(authorFile));
            }
        }

        return authors;
    }

    private Author readAuthor(File authorFile) {
        Author author = new Author();

        author.setName(authorFile.getName());
        readWebPages(authorFile, author);
        readTags(authorFile, author);
        readBooks(authorFile, author);
        author.setId();

        return author;
    }

    private URL readOriginalSite(File authorFile) {
        return readUrlFile(authorFile, "o");
    }

    private URL readBibliography(File authorFile) {
        return readUrlFile(authorFile, "works");
    }

    private URL readGuttenberg(File authorFile) {
        return readUrlFile(authorFile, "g");
    }

    private URL readWikipedia(File authorFile) {
        return readUrlFile(authorFile, "w");
    }

    private void readTags(File authorFile, Author author) {
        File file = new File(authorFile.getAbsolutePath() + "/doc/tags.txt");

        if (! file.isFile()) {
            return;
        }

        String content = readFileContent(file).trim().toLowerCase();

        if (content.indexOf(';') == -1) {
            author.addTag(content);
        } else {
            for (String tagName : content.split(";")) {
                author.addTag(tagName);
            }
        }
    }

    private URL readUrlFile(File authorFile, String fileName) {
        File urlFile = new File(authorFile.getAbsolutePath() + "/doc/" + fileName + ".URL");

        if (! urlFile.isFile()) {
            urlFile = new File(authorFile.getAbsolutePath() + "/doc/" + fileName + ".url");
        }

        if (! urlFile.isFile()) {
            return null;
        }

        String content = readFileContent(urlFile);
        int pos = content.indexOf("URL=");

        if (pos == -1) {
            return null;
        }

        try {
            return new URL(content.substring(pos + 4));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public String readFileContent(File urlFile) {
        FileInputStream stream;
        try {
            stream = new FileInputStream(urlFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        try {
          FileChannel fc = stream.getChannel();
          MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

          return Charset.defaultCharset().decode(bb).toString();
        } catch (IOException ex) {
            Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void readBooks(File authorFile, Author author) {
        File en = new File(authorFile.getAbsolutePath() + "/en/");

        if (en.isDirectory()) {
            for (Book book: readBooksFromDirectory(en, author)) {
                book.setEnglish(true);
                author.addBook(book);
            }
        }

        for (Book book: readBooksFromDirectory(authorFile, author)) {
            author.addBook(book);
        }
    }

    private List<Book> readBooksFromDirectory(File dir, Author author) {
        List<Book> authorBooks = new ArrayList<>();

        for (String bookFileName : dir.list()) {
            File bookFile = new File(dir.getAbsolutePath() + PATH_SEPARATOR + bookFileName);

            if (bookFile.isFile() && !bookFile.getName().startsWith(".") && bookFile.getName().endsWith(".epub")) {
                Book book = new Book();

                String name = bookFile.getName();
                book.setName(name.substring(0, name.lastIndexOf('.')));

                name = bookFile.getAbsolutePath();
                book.setEpubFileName(name);
                
                File mobiFile = new File(name.substring(0, name.lastIndexOf('.')) + ".mobi");
                if (mobiFile.isFile()) {
                    book.setMobiFileName(mobiFile.getAbsolutePath());
                } else {
                    book.setMobiFileName(null);
                }
                
                book.setAuthor(author);
                book.setId();
                
                // read additional properties
                book.getModifiedTime();
                book.getSource();

                authorBooks.add(book);
            }
        }

        return authorBooks;
    }

    private void readWebPages(File authorFile, Author author) {
        List<WebPage> pages = new ArrayList<>();
        URL url;
        
        url = readOriginalSite(authorFile);
        if (null != url) {
            pages.add(new WebPage("Original Site", url, author));
        }
        
        url = readBibliography(authorFile);
        if (null != url) {
            pages.add(new WebPage("Bibliography", url, author));
        }
        
        url = readGuttenberg(authorFile);
        if (null != url) {
            pages.add(new WebPage("Guttenberg", url, author));
        }
        
        url = readWikipedia(authorFile);
        if (null != url) {
            pages.add(new WebPage("Wikipedia", url, author));
        }
        
        author.setWebPages(pages);
    }
    
    public void clearCache(@Observes CacheClearEvent event) {
        refreshData();
    }

    private void refreshData() {
        long startTime = System.currentTimeMillis();
        LOG.log(Level.SEVERE, "readData started on {0}", new Object[]{new Date()});
        
        File mainDir = new File(conf.getBookPath());
        readDataFromFiles(mainDir);
        
        col.refreshCollections();
        stat.readStatistics();

        LOG.log(Level.SEVERE,"readData finished in {0}ms"
                + ". Stats: " + "Letters {1}, authors {2}, books {3}, tags {4}",
                new Object[]{(System.currentTimeMillis() - startTime),
                    col.getLetters().size(), col.getAuthors().size(), col.getBooks().size(),
                    col.getTags().size()});
    }
}
