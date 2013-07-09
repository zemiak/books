package com.zemiak.books.phone.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.phone.boundary.Collection;
import com.zemiak.books.phone.domain.Letter;
import com.zemiak.books.phone.vaadin.NavManager;
import com.zemiak.books.phone.vaadin.NavToolbar;
import java.util.List;

public class Letters extends NavigationView {
    CssLayout content = null;
    List<Letter> letters;
    NavManager manager;
    Collection col;
    boolean initialized = false;

    public Letters() {
        super("Books");
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        
        if (initialized) {
            return;
        }
        
        this.manager = (NavManager) getNavigationManager();
        this.col = manager.getCollection();
        this.letters = col.getLetters();
        this.setToolbar(new NavToolbar(manager));
        
        refresh();
        initialized = true;
    }

    private void refresh() {
        content = new CssLayout();
        setContent(content);

        VerticalComponentGroup group = new VerticalComponentGroup("Letters");
        for (Letter letter: letters) {
            NavigationButton button = new NavigationButton();
            button.setCaption(letter.getLetter());
            group.addComponent(button);

            final Letter finalLetter = letter;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    getNavigationManager().navigateTo(new LetterDetail(finalLetter, manager));
                }
            });
        }

        content.addComponents(group);
    }
}
