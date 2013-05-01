package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Letter;
import com.zemiak.books.vaadin.NavManager;
import java.util.Collections;
import java.util.List;

public class LettersMain extends NavigationView implements Component {
    CssLayout content = null;
    List<Letter> letters;
    NavManager manager;
    Collection col;
    
    public LettersMain(List<Letter> letters, Collection col, NavManager manager) {
        super("Letters");
        
        this.letters = letters;
        this.manager = manager;
        this.col = col;
        
        this.setToolbar(manager.getToolbar());
        refresh();
    }
    
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
    }

    private void refresh() {
        content = new CssLayout();
        setContent(content);

        Collections.sort(letters);
        
        VerticalComponentGroup group = new VerticalComponentGroup();
        final Toolbar toolbar = (Toolbar) getToolbar();
        
        for (Letter letter: letters) {
            NavigationButton button = new NavigationButton();
            button.setCaption(letter.getLetter());
            group.addComponent(button);
            
            final Letter finalLetter = letter;
            
            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    getNavigationManager().navigateTo(new LetterPage(finalLetter, col, manager));
                }
            });
        }
        
        content.addComponents(group);
    }
}
