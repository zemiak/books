package com.zemiak.books.ui.phone.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.client.domain.Author;
import com.zemiak.books.client.domain.Tag;
import java.util.List;

class TagDetail extends ViewAbstract {
    List<Author> authors;
    CssLayout content = null;
    
    Tag tag;
    
    public TagDetail(Tag tag) {
        this.tag = tag;
    }
    
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();

        setCaption("#" + tag.getName());
        refresh();
    }

    private void refresh() {
        content = new CssLayout();
        setContent(content);
        
        VerticalComponentGroup group = new VerticalComponentGroup("Authors");

        for (Author author: tag.getAuthors()) {
            NavigationButton button = new NavigationButton(author.getName());
            group.addComponent(button);

            final Author finalAuthor = author;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    AuthorDetail view = new AuthorDetail(finalAuthor);
                    getNavManager().navigateTo(view);
                }
            });
        }

        content.addComponents(group);
    }
}
