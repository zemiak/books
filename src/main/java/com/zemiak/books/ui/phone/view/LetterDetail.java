package com.zemiak.books.ui.phone.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.client.boundary.Collection;
import com.zemiak.books.client.domain.Author;
import com.zemiak.books.client.domain.Letter;
import com.zemiak.books.ui.phone.NavManager;
import com.zemiak.books.ui.phone.NavToolbar;
import java.util.List;

class LetterDetail extends NavigationView {
    CssLayout content = null;
    List<Author> authors;
    NavManager manager;
    Collection col;
    Letter letter;

    public LetterDetail(Letter letter, NavManager manager) {
        super(letter.getLetter());

        this.letter = letter;
        authors = letter.getAuthors();
        this.manager = manager;
        this.col = manager.getCollection();

        this.setToolbar(new NavToolbar(manager));
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        
        refresh();
    }

    private void refresh() {
        content = new CssLayout();
        setContent(content);

        VerticalComponentGroup group = new VerticalComponentGroup();

        for (Author author: authors) {
            NavigationButton button = new NavigationButton(author.getName());
            group.addComponent(button);

            final Author finalAuthor = author;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    getNavigationManager().navigateTo(new AuthorDetail(finalAuthor, manager));
                }
            });
        }

        content.addComponents(group);
    }
}
