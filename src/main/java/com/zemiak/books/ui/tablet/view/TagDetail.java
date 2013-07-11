package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.client.boundary.Collection;
import com.zemiak.books.client.domain.Author;
import com.zemiak.books.ui.tablet.NavManager;
import com.zemiak.books.ui.tablet.NavToolbar;
import java.util.List;

/**
 *
 * @author vasko
 */
class TagDetail extends NavigationView {
    List<Author> authors;
    NavManager manager;
    CssLayout content = null;
    Collection col;
    String tag;

    public TagDetail(String tag, NavManager manager) {
        setCaption("#" + tag);

        this.manager = manager;
        this.tag = tag;

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
        
        this.col = manager.getCollection();
        this.authors = col.getAuthorsByTag(tag);

        VerticalComponentGroup group = new VerticalComponentGroup("Authors");

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
