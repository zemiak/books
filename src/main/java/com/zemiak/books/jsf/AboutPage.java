package com.zemiak.books.jsf;

import com.zemiak.books.service.Statistics;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class AboutPage {
    final static private String VERSION = "1.0";
    
    @Inject
    private Statistics stat;

    public String getVersion() {
        return VERSION;
    }
    
    public String getAuthors() {
        return String.valueOf(stat.getAuthors());
    }
    
    public String getBooks() {
        return String.valueOf(stat.getBooks());
    }
    
    public String getDocumentedAuthors() {
        return String.valueOf(stat.getAuthorsDocumented());
    }
    
    public String getTaggedAuthors() {
        return String.valueOf(stat.getAuthorsTagged());
    }
}
