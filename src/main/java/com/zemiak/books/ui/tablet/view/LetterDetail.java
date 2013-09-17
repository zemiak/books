package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.responsive.Responsive;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Letter;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Dependent
class LetterDetail extends ViewAbstract {
    CssLayout grid = null;
    
    Letter letter;
    List<Author> authors;
    
    @Inject
    Instance<AuthorDetail> authorView;
    
    public LetterDetail() {
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
        refreshData();
    }
    
    @Override
    public void attach() {
        super.attach();
        
        grid = new CssLayout();
        grid.setWidth("100%");
        grid.addStyleName("grid");
        setContent(grid);
        
        new Responsive(grid);
    }
    
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        setCaption("Letter " + letter.getLetter());
        
        refresh();
    }

    private void refresh() {
        grid.removeAllComponents();

        for (Author author: authors) {
            NavigationButton button = new NavigationButton(author.getName());
            button.setSizeUndefined();
            grid.addComponent(button);

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
    }

    private void refreshData() {
        authors = letter.getAuthors();
    }
}
