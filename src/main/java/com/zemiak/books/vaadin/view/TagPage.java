package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Tag;
import com.zemiak.books.vaadin.NavManager;
import com.zemiak.books.vaadin.NavToolbar;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author vasko
 */
class TagPage extends NavigationView implements Component {
    List<Author> authors;
    NavManager manager;
    CssLayout content = null;
    Collection col;

    public TagPage(Tag tag, NavManager manager) {
        setCaption(tag.getName());

        this.manager = manager;
        this.col = manager.getCollection();
        this.authors = col.getAuthorsByTag(tag.getName());

        this.setToolbar(new NavToolbar(manager));
        refresh();
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
    }

    private void refresh() {
        Collections.sort(authors);

        content = new CssLayout();
        setContent(content);

        VerticalComponentGroup group = new VerticalComponentGroup();
        final TagPage that = this;

        for (Author author: authors) {
            NavigationButton button = new NavigationButton();
            button.setCaption(author.getName());
            group.addComponent(button);

            final Author finalAuthor = author;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    getNavigationManager().navigateTo(new AuthorPage(finalAuthor, manager));
                }
            });
        }

        content.addComponents(group);
    }
}
