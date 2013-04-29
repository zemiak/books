package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Letter;
import java.util.Collections;
import java.util.List;

class LetterPage extends NavigationView implements Component {
    CssLayout content = null;
    List<Author> authors;

    public LetterPage(Letter letter) {
        super(letter.getLetter());
        
        authors = letter.getAuthors();
    }
    
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        
        if (null != content) {
            return;
        }

        Collections.sort(authors);
        
        content = new CssLayout();
        setContent(content);

        VerticalComponentGroup group = new VerticalComponentGroup();
        
        for (Author author: authors) {
            NavigationButton button = new NavigationButton();
            button.setCaption(author.getName());
            group.addComponent(button);
            
            final Author finalAuthor = author;
            
            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    getNavigationManager().navigateTo(new AuthorPage(finalAuthor));
                }
            });
        }
        
        content.addComponents(group);
    }
}
