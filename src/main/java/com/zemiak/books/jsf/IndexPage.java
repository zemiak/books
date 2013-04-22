package com.zemiak.books.jsf;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Letter;
import com.zemiak.books.domain.Tag;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class IndexPage {
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
    
    @Inject
    private SearchPage searchPage;
    
    public void searchTextListener(ValueChangeEvent event) {
        searchPage.setText(event.getNewValue().toString());
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

    public SearchPage getSearchPage() {
        return searchPage;
    }
}
