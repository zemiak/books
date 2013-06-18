package com.zemiak.books.phone.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.phone.boundary.Collection;
import com.zemiak.books.phone.domain.Author;
import com.zemiak.books.phone.domain.Tag;
import com.zemiak.books.phone.vaadin.NavManager;
import com.zemiak.books.phone.vaadin.NavToolbar;
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

    public TagPage(String tag, NavManager manager) {
        setCaption("#" + tag);

        this.manager = manager;
        this.col = manager.getCollection();
        this.authors = col.getAuthorsByTag(tag);

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

        VerticalComponentGroup group = new VerticalComponentGroup("Authors");

        for (Author author: authors) {
            NavigationButton button = new NavigationButton(author.getName());
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
