package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.responsive.Responsive;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.client.domain.Author;
import com.zemiak.books.client.domain.Tag;
import java.util.List;

class TagDetail extends ViewAbstract {
    List<Author> authors;
    CssLayout grid = null;
    
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
        grid = new CssLayout();
        grid.setWidth("100%");
        grid.addStyleName("grid");
        setContent(grid);
        new Responsive(grid);
        
        for (Author author: tag.getAuthors()) {
            NavigationButton button = new NavigationButton(author.getName());
            button.setSizeUndefined();
            grid.addComponent(button);

            final Author finalAuthor = author;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    AuthorDetail view = new AuthorDetail(finalAuthor);
                    getNavManager().navigateTo(view);
                }
            });
        }
    }
}
