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
    private String baseUrl = "http://localhost:8080/books/";
    private String restBaseUrl = "http://localhost:8080/books/webresources/";
    private boolean refreshData = false;
    Properties prop = new Properties();
    
    @PostConstruct
    public void readConfiguration() {
        if (! loadPropertiesFromFile()) {
            return;
        }
        
        readBasePathOption();
        readBaseUrlOption();
        readRestBaseUrlOption();
        readRefreshDataOption(prop);
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

    public boolean isRefreshData() {
        return refreshData;
    }

    private void readRefreshDataOption(Properties prop) {
        String refreshDataText = prop.getProperty("refreshData", "false").toLowerCase();
        if ("true".equals(refreshDataText) || "yes".equals(refreshDataText) || "y".equals(refreshDataText)
                || "t".equals(refreshDataText) || "1".equals(refreshDataText)) {
            this.refreshData = true;
        }
    }

    private boolean loadPropertiesFromFile() {
        InputStream stream;
        
        try {
            stream = new FileInputStream(CONFIGURATION_FILE);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        try {
            prop.load(stream);
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return true;
    }

    private void readBasePathOption() {
        bookPath = prop.getProperty("bookPath", bookPath);
        
        if (! bookPath.endsWith("/")) {
            bookPath += "/";
        }
    }

    private void readBaseUrlOption() {
        baseUrl = prop.getProperty("baseUrl", baseUrl);
        
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
    }

    private void readRestBaseUrlOption() {
        restBaseUrl = prop.getProperty("restBaseUrl", restBaseUrl);
        
        if (restBaseUrl.endsWith("/")) {
            restBaseUrl = restBaseUrl.substring(0, restBaseUrl.length() - 1);
        }
    }
}
