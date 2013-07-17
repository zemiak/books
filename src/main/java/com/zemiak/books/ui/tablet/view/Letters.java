package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.responsive.Responsive;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.cdi.CDIView;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.client.boundary.CachedCollection;
import com.zemiak.books.client.domain.Letter;
import java.util.List;
import javax.inject.Inject;

@CDIView("lettersTablet")
public class Letters extends ViewAbstract {
    CssLayout grid = null;
    List<Letter> letters;
    
    @Inject
    CachedCollection col;
    
    boolean initialized = false;

    public Letters() {
    }
    
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        setCaption("Books/Tablet");
        
        if (initialized) {
            return;
        }
        
        this.letters = col.getLetters();
        
        refresh();
        initialized = true;
    }

    private void refresh() {
        grid = new CssLayout();
        grid.setWidth("100%");
        grid.addStyleName("grid");
        setContent(grid);

        for (Letter letter: letters) {
            NavigationButton button = new NavigationButton(letter.getLetter());
            button.setSizeUndefined();
            grid.addComponent(button);

            final Letter finalLetter = letter;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    LetterDetail view = (LetterDetail) getNavManager().getView("letterdetailTablet");
                    view.setLetter(finalLetter);
                    getNavManager().navigateTo(view);
                }
            });
            
            grid.addComponent(button);
        }
        
        new Responsive(grid);
    }
}
