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
import com.zemiak.books.vaadin.NavToolbar;
import java.util.Collections;
import java.util.List;

public class LettersMain extends NavigationView implements Component {
    CssLayout content = null;
    List<Letter> letters;
    NavManager manager;
    Collection col;

    public LettersMain(NavManager manager) {
        super("Books");

        this.manager = manager;
        this.col = manager.getCollection();
        this.letters = col.getLetters();



        this.setToolbar(new NavToolbar(manager));
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
        for (Letter letter: letters) {
            NavigationButton button = new NavigationButton();
            button.setCaption(letter.getLetter());
            group.addComponent(button);

            final Letter finalLetter = letter;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    getNavigationManager().navigateTo(new LetterPage(finalLetter, manager));
                }
            });
        }

        content.addComponents(group);
    }
}
