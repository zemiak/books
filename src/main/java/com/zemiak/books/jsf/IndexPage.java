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
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
public class IndexPage {
    private String text;
    
    @Inject
    private Collection col;
    
    public void runSearch() {
        if (null == text || text.trim().isEmpty()) {
            return;
        }
        
        text = text.trim();
        
        try {
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect("search.jsf?text=" + text);
        } catch (IOException ex) {
            Logger.getLogger(IndexPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getText() {
        return text;
    }
    
    @PostConstruct
    public void init() {
        // handle POST: search
        
        Map<String,String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        text = requestParams.get("searchForm:text");
        
        if (null != text && !text.trim().isEmpty()) {
            runSearch();
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Letter> getLetters() {
        return col.getLetters();
    }
    
    public List<Tag> getTags() {
        return col.getTags();
    }
}
