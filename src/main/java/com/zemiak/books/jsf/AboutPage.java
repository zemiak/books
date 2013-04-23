package com.zemiak.books.jsf;

import com.zemiak.books.boundary.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class AboutPage {
    final static private String VERSION = "1.0";
    
    @Inject
    private Collection col;

    public String getVersion() {
        return VERSION;
    }
    
    public String getAuthors() {
        return String.valueOf(col.getAuthors().size());
    }
    
    public String getBooks() {
        return String.valueOf(col.getBooks().size());
    }
    
    public String getDocumentedAuthors() {
        return String.valueOf(col.getAuthorsDocumented().size());
    }
    
    public String getTaggedAuthors() {
        return String.valueOf(col.getAuthorsTagged().size());
    }
}
