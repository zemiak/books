package com.zemiak.books.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Book implements Serializable, Comparable {

    private String mobiFileName;
    private String epubFileName;
    private String name;
    private boolean english;
    private BookSource source = null;
    private Author author;
    private Date modifiedTime;
    private int id;

    public Book() {
    }

    public String getMobiFileName() {
        return mobiFileName;
    }

    public void setMobiFileName(String mobiFileName) {
        this.mobiFileName = mobiFileName;
    }

    public String getEpubFileName() {
        return epubFileName;
    }

    public void setEpubFileName(String epubFileName) {
        this.epubFileName = epubFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        id = hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.mobiFileName);
        hash = 83 * hash + Objects.hashCode(this.epubFileName);
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.mobiFileName, other.mobiFileName)) {
            return false;
        }
        if (!Objects.equals(this.epubFileName, other.epubFileName)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book{" + "mobiFileName=" + mobiFileName + ", epubFileName=" + epubFileName + ", name=" + name + ", author=" + author + ", id=" + id + '}';
    }

    public boolean isEnglish() {
        return english;
    }

    public void setEnglish(boolean english) {
        this.english = english;
    }

    @Override
    public int compareTo(Object o) {
        Book other = (Book) o;

        return this.getName().compareTo(other.getName());
    }

    public Date getModifiedTime() {
        if (null != modifiedTime) {
            return modifiedTime;
        }

        String fileName = getEpubFileName();

        if (null == fileName) {
            fileName = getMobiFileName();
        }

        if (null == fileName) {
            return null;
        }

        File file = new File(fileName);
        modifiedTime = new Date(file.lastModified());

        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public BookSource getSource() {
        if (null != source) {
            return source;
        }

        String fileName = getEpubFileName();
        if (null != fileName) {
            try {
                ZipFile epub = new ZipFile(fileName);

                if (isPalmKnihySource(epub)) {
                    source = BookSource.PALMKNIHY;
                } else if (isMartinusSource(epub)) {
                    source = BookSource.MARTINUS;
                } else if (isKosmasSource(epub)) {
                    source = BookSource.KOSMAS;
                } else {
                    source = BookSource.OTHER;
                }
            } catch (IOException ex) {
                Logger.getLogger(Book.class.getName()).log(Level.SEVERE, "Cannot read ZipFile:" + fileName, ex);
            }
        }

        if (null == source) {
            source = BookSource.OTHER;
        }

        return source;
    }

    public void setSource(BookSource source) {
        this.source = source;
    }

    private boolean isPalmKnihySource(ZipFile epub) {
        ZipEntry entry = epub.getEntry("OEPBS/Text/social-drm.html");
        Boolean is = false;

        if (null != entry && !entry.isDirectory()) {
            try {
                try (InputStream stream = epub.getInputStream(entry)) {
                    String content = Book.fromStream(stream);
                    if (content.contains("Palmknihy")) {
                        is = true;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Book.class.getName()).log(Level.SEVERE, "Cannot get input stream from:" + entry.getName(), ex);
            }
        }

        return is;
    }

    private boolean isMartinusSource(ZipFile epub) {
        Boolean is = false;
        
        for (int i = 0; i < 10; i++) {
            ZipEntry entry = epub.getEntry("OEPBS/Text/disclaimer_" + i + ".html");

            if (null != entry && !entry.isDirectory()) {
                try {
                    try (InputStream stream = epub.getInputStream(entry)) {
                        String content = Book.fromStream(stream);
                        if (content.contains("Martinus")) {
                            is = true;
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Book.class.getName()).log(Level.SEVERE, "Cannot get input stream from:" + entry.getName(), ex);
                }
            }
            
            if (is) {
                break;
            }
        }

        return is;
    }

    private boolean isKosmasSource(ZipFile epub) {
        Boolean is = false;
        
        for (Enumeration<? extends ZipEntry> e = epub.entries(); e.hasMoreElements();) {
            ZipEntry entry = e.nextElement();

            if (null != entry && !entry.isDirectory() && entry.getName().startsWith("OEBPS/Text/")
                    && (entry.getName().matches("^.*_00\\d\\.html$") 
                    || entry.getName().matches("^.*_000\\d\\.html$"))) {
                try {
                    try (InputStream stream = epub.getInputStream(entry)) {
                        String content = Book.fromStream(stream);
                        if (content.contains("Kosmas")) {
                            is = true;
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Book.class.getName()).log(Level.SEVERE, "Cannot get input stream from:" + entry.getName(), ex);
                }
            }
            
            if (is) {
                break;
            }
        }

        return is;
    }

    public static String fromStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        
        return out.toString();
    }

    public enum BookSource {
        MARTINUS,
        PALMKNIHY,
        KOSMAS,
        OTHER,
    }
}
