package com.zemiak.books.jsf;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Book;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
public class BookPage {

    @ManagedProperty("#{param.id}")
    private int id;
    @Inject
    private Collection col;
    private Book book;

    @PostConstruct
    public void init() {
        book = col.getBook(id);
    }

    public Book getBook() {
        return book;
    }

    public String getFireDownload() {
        fileMobi();

        return "Done";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void fileMobi() {
        initBook(); // action scope is different

        getFile(book.getMobiFileName(), "mobi");
    }

    public void fileEpub() {
        initBook(); // action scope is different

        getFile(book.getEpubFileName(), "epub");
    }

    private void initBook() {
        Map<String, String> params = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        id = Integer.parseInt(params.get("id"));
        init();
    }

    private void getFile(String fileName, String ext) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        /**
         * Some JSF component library or some Filter might have set some headers
         * in the buffer beforehand. We want to get rid of them, else it may
         * collide.
         */
        ec.responseReset();

        ec.setResponseContentType("application/x-mobipocket-ebook");
        ec.setResponseContentLength(getFileSize(fileName));
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + book.getName() + "." + ext + "\"");

        OutputStream output;
        try {
            output = ec.getResponseOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(BookPage.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        byte[] input = readFileBytes(fileName);
        if (input == null) {
            try {
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(BookPage.class.getName()).log(Level.SEVERE, null, ex);
            }

            return;
        }

        try {
            output.write(input);
        } catch (IOException ex) {
            Logger.getLogger(BookPage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(BookPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * Important! Otherwise JSF will attempt to render the response which
         * obviously will fail since it's already written with a file and
         * closed.
         */
        fc.responseComplete();
    }

    byte[] readFileBytes(String fileName) {
        File file = new File(fileName);
        
        byte[] result = new byte[(int) file.length()];
        try {
            try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
                int totalBytesRead = 0;
                while (totalBytesRead < result.length) {
                    int bytesRemaining = result.length - totalBytesRead;
                    int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
                    
                    if (bytesRead > 0) {
                        totalBytesRead = totalBytesRead + bytesRead;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BookPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    private int getFileSize(String fileName) {
        File file = new File(fileName);
        
        return (int) file.length();
    }
}
