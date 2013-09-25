package com.zemiak.books.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
    private static final Logger LOG = Logger.getLogger(Configuration.class.getName());
    
    Properties prop = new Properties();
    
    @PostConstruct
    public void readConfiguration() {
        if (! loadPropertiesFromFile()) {
            return;
        }
        
        readBasePathOption();
    }

    public String getBookPath() {
        return bookPath;
    }

    private boolean loadPropertiesFromFile() {
        InputStream stream;
        
        try {
            stream = new FileInputStream(CONFIGURATION_FILE);
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, "Conf file not found: " + CONFIGURATION_FILE, ex);
            return false;
        }
        
        try {
            prop.load(stream);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Cannot load conf file: " + CONFIGURATION_FILE, ex);
            return false;
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, "Cannot close conf file: " + CONFIGURATION_FILE, ex);
            }
        }
        
        return true;
    }

    private void readBasePathOption() {
        bookPath = prop.getProperty("bookPath", bookPath);
        
        if (! bookPath.endsWith(Storage.PATH_SEPARATOR)) {
            bookPath += Storage.PATH_SEPARATOR;
        }
    }
}
