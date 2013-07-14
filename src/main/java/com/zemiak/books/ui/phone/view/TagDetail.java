package com.zemiak.books.ui.phone.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.cdi.CDIView;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.client.boundary.CachedCollection;
import com.zemiak.books.client.domain.Author;
import com.zemiak.books.client.domain.Tag;
import java.util.List;
import javax.inject.Inject;

@CDIView("tagdetail")
class TagDetail extends ViewAbstract {
    List<Author> authors;
    CssLayout content = null;
    
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
                    AuthorDetail view = (AuthorDetail) getNavManager().getView("authordetail");
                    view.setAuthor(finalAuthor);
                    getNavManager().navigateTo(view);
                }
            });
        }

        content.addComponents(group);
    }

    private void refreshData() {
        this.authors = col.getAuthorsByTag(tag);
    }
}
