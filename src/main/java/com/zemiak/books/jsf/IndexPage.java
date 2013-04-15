package com.zemiak.books.jsf;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Letter;
import com.zemiak.books.domain.Tag;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class IndexPage {
    @Inject
    private Collection col;
    
    public List<Letter> getLetters() {
        return col.getLetters();
    }
    
    public List<Tag> getTags() {
        return col.getTags();
    }
}