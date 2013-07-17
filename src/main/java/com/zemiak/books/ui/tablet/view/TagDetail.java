package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.responsive.Responsive;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.cdi.CDIView;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.client.boundary.CachedCollection;
import com.zemiak.books.client.domain.Author;
import com.zemiak.books.client.domain.Tag;
import java.util.List;
import javax.inject.Inject;

@CDIView("tagdetailTablet")
class TagDetail extends ViewAbstract {
    List<Author> authors;
    CssLayout grid = null;
    
    @Inject
    CachedCollection col;
    
    String tag;
    
    public TagDetail() {
    }
    
    public void setTag(String tag) {
        this.tag = tag;
        refreshData();
    }
    
    public void setTag(Tag tag) {
        setTag(tag.getName());
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
        
        for (Author author: authors) {
            NavigationButton button = new NavigationButton(author.getName());
            button.setSizeUndefined();
            grid.addComponent(button);

            final Author finalAuthor = author;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    AuthorDetail view = (AuthorDetail) getNavManager().getView("authordetailTablet");
                    view.setAuthor(finalAuthor);
                    getNavManager().navigateTo(view);
                }
            });
        }
    }

    private void refreshData() {
        this.authors = col.getAuthorsByTag(tag);
    }
}
