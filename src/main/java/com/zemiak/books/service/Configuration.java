package com.zemiak.books.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class Configuration {
    final static String CONFIGURATION_FILE = "/etc/zemiak-books.properties";
    
    private String bookPath = "/Users/vasko/Documents/Books/";
    private String baseUrl = "http://localhost:8080/books/";
    private String restBaseUrl = "http://localhost:8080/books/webservices/";
    
    @PostConstruct
    public void readConfiguration() {
        Properties prop = new Properties();
        
        InputStream stream;
        try {
            stream = new FileInputStream(CONFIGURATION_FILE);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        try {
            prop.load(stream);
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        bookPath = prop.getProperty("bookPath", "/Users/vasko/Documents/Books/");
        baseUrl = prop.getProperty("baseUrl", "http://localhost:8080/books/");
        restBaseUrl = prop.getProperty("baseUrl", "http://localhost:8080/books/webservices/");
        
        System.out.println("BaseURL:" + baseUrl);
    }

    public String getBookPath() {
        return bookPath;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
    
    public String getRestBaseUrl() {
        return restBaseUrl;
    }
}
