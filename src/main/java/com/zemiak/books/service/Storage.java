package com.zemiak.books.service;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Book;
import com.zemiak.books.domain.Letter;
import com.zemiak.books.domain.Tag;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
@DependsOn("Configuration")
public class Storage {
    private static final int NUMBER_OF_LETTERS = 26;
    private static final Logger LOG = Logger.getLogger(Storage.class.getName());

    @Inject
    private Collection col;
    
    @Inject
    private Configuration conf;

    @PostConstruct
    public void readData() {
        if (isDataCached()) {
            return;
        }

        long startTime = System.currentTimeMillis();
        LOG.log(Level.INFO, "readData started on {0}", new Object[]{new Date()});

        File mainDir = new File(conf.getBookPath());

        readDataFromFiles(mainDir);

        LOG.log(Level.INFO,"readData finished in {0}ms"
                + ". Stats: " + "Letters {1}, authors {2}, books {3}, tagged authors {4}",
                new Object[]{(System.currentTimeMillis() - startTime),
                    col.getLetters().size(), col.getAuthors().size(), col.getBooks().size(),
                    col.getTags().size()});
    }

    private boolean isDataCached() {
        // testing
        return true;
        
        //return col.getLetters().size() == NUMBER_OF_LETTERS;
    }

    public void readDataFromFiles(File mainDir) {
        for (String letter : mainDir.list()) {
            File letterFile = new File(mainDir.getAbsolutePath() + "/" + letter);

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

            col.persist(letter);
        }
    }

    private Set<Author> readAuthors(File letterFile) {
        Set<Author> authors = new HashSet<>();

        for (String author: letterFile.list()) {
            File authorFile = new File(letterFile.getAbsolutePath() + "/" + author);

            if (authorFile.isDirectory() && !authorFile.getName().startsWith(".")) {
                authors.add(readAuthor(authorFile));
            }
        }

        return authors;
    }

    private Author readAuthor(File authorFile) {
        Author author = new Author();

        author.setName(authorFile.getName());
        author.setOriginalSite(readOriginalSite(authorFile));
        author.setBibliography(readBibliography(authorFile));
        author.setGuttenberg(readGuttenberg(authorFile));
        author.setWikipedia(readWikipedia(authorFile));
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

        String content = readFileContent(file);

        if (content.indexOf(';') == -1) {
            author.addTag(new Tag(content));
        } else {
            for (String tagName : content.split(";")) {
                author.addTag(new Tag(tagName));
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
            File bookFile = new File(dir.getAbsolutePath() + "/" + bookFileName);

            if (bookFile.isFile() && !bookFile.getName().startsWith(".") && bookFile.getName().endsWith(".mobi")) {
                Book book = new Book();

                String name = bookFile.getName();
                book.setName(name.substring(0, name.lastIndexOf('.')));

                name = bookFile.getAbsolutePath();
                book.setMobiFileName(name);
                book.setEpubFileName(name.substring(0, name.indexOf('.')) + ".epub");
                book.setAuthor(author);
                book.setId();

                authorBooks.add(book);
            }
        }

        return authorBooks;
    }
}
