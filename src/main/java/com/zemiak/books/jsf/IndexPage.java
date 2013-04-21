package com.zemiak.books.jsf;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Letter;
import com.zemiak.books.domain.Tag;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class IndexPage {
    private String searchText;
    
    @Inject
    private Collection col;
    
    @Inject 
    private LetterPage letterPage;
    
    @Inject
    private TagPage tagPage;
    
    @Inject
    private AuthorPage authorPage;
    
    @Inject
    private History history;
    
    @Inject
    private BookPage bookPage;
    
    public void runSearch() {
        if (null == searchText || searchText.trim().isEmpty()) {
            return;
        }
        
        searchText = searchText.trim();
        
        try {
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect("search.jsf?text=" + searchText);
        } catch (IOException ex) {
            Logger.getLogger(IndexPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSearchText() {
        return searchText;
    }
    
    @PostConstruct
    public void init() {
        // handle POST: search
        
        Map<String,String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        searchText = requestParams.get("searchForm:text");
        
        if (null != searchText && !searchText.trim().isEmpty()) {
            runSearch();
        }
    }

    public void setSearchText(String text) {
        this.searchText = text;
    }

    public List<Letter> getLetters() {
        return col.getLetters();
    }
    
    public List<Tag> getTags() {
        return col.getTags();
    }

    public LetterPage getLetterPage() {
        return letterPage;
    }

    public TagPage getTagPage() {
        return tagPage;
    }

    public AuthorPage getAuthorPage() {
        return authorPage;
    }

    public History getHistory() {
        return history;
    }

    public BookPage getBookPage() {
        return bookPage;
    }
}
