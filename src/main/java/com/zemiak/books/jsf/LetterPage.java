package com.zemiak.books.jsf;

import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Letter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LetterPage {
    private Letter letter;
    
    public Letter getLetter() {
        return letter;
    }
    
    public List<Author> getAuthors() {
        if (null == letter) return null;
        
        List<Author> authors = letter.getAuthors();
        
        Collections.sort(authors, new Comparator<Author>() {
            @Override
            public int compare(Author o1, Author o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        
        return authors;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }
}
