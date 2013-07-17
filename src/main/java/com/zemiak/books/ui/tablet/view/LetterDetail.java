package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.cdi.CDIView;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.client.boundary.CachedCollection;
import com.zemiak.books.client.domain.Author;
import com.zemiak.books.client.domain.Letter;
import java.util.List;
import javax.inject.Inject;

@CDIView("letterdetailTablet")
class LetterDetail extends ViewAbstract {
    CssLayout content = null;
    
    @Inject
    CachedCollection col;
    
    Letter letter;
    List<Author> authors;

    public LetterDetail() {
    }
    
    public void setLetter(Letter letter) {
        this.letter = letter;
        refreshData();
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        setCaption("Letter " + letter.getLetter());
        
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
                    AuthorDetail view = (AuthorDetail) getNavManager().getView("authordetailTablet");
                    view.setAuthor(finalAuthor);
                    getNavManager().navigateTo(view);
                }
            });
        }

        content.addComponents(group);
    }

    private void refreshData() {
        authors = letter.getAuthors();
    }
}
