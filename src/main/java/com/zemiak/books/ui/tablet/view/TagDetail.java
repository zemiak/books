package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.responsive.Responsive;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Author;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
class TagDetail extends ViewAbstract {
    List<Author> authors;
    CssLayout grid = null;
    
    @Inject
    Collection col;
    
    @Inject
    AuthorDetail authorView;
    
    String tag;
    
    public TagDetail() {
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();

        setCaption("#" + tag);
        refresh();
    }

    private void refresh() {
        grid = new CssLayout();
        grid.setWidth("100%");
        grid.addStyleName("grid");
        setContent(grid);
        new Responsive(grid);
        
        for (Author author: col.getAuthorsByTag(tag)) {
            NavigationButton button = new NavigationButton(author.getName());
            button.setSizeUndefined();
            grid.addComponent(button);

            final Author finalAuthor = author;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    authorView.setAuthor(finalAuthor);
                    getNavManager().navigateTo(authorView);
                }
            });
        }
    }
}
