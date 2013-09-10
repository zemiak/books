package com.zemiak.books.ui.phone.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Author;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Dependent
class TagDetail extends ViewAbstract {
    List<Author> authors;
    CssLayout content = null;
    
    String tag;
    
    @Inject
    Collection col;
    
    @Inject
    Instance<AuthorDetail> authorView;
    
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
        content = new CssLayout();
        setContent(content);
        
        VerticalComponentGroup group = new VerticalComponentGroup("Authors");

        for (Author author: col.getAuthorsByTag(tag)) {
            NavigationButton button = new NavigationButton(author.getName());
            group.addComponent(button);

            final Author finalAuthor = author;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    AuthorDetail view = authorView.get();
                    view.setAuthor(finalAuthor);
                    getNavManager().navigateTo(view);
                }
            });
        }

        content.addComponents(group);
    }
}
