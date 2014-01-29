package com.zemiak.books.service;

import java.util.Properties;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class Configuration {
    @Resource(name = "com.zemiak.books") private Properties conf;
    
    private String bookPath;
    private static final Logger LOG = Logger.getLogger(Configuration.class.getName());
    
    @PostConstruct
    public void readConfiguration() {
        readBasePathOption();
    }

    public String getBookPath() {
        return bookPath;
    }

    private void readBasePathOption() {
        bookPath = conf.getProperty("path", bookPath);
        
        if (! bookPath.endsWith(Storage.PATH_SEPARATOR)) {
            bookPath += Storage.PATH_SEPARATOR;
        }
    }
}
