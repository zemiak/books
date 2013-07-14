package com.zemiak.books.ui.phone.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.cdi.CDIView;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.client.boundary.CachedCollection;
import com.zemiak.books.client.domain.Letter;
import java.util.List;
import javax.inject.Inject;

@CDIView("letters")
public class Letters extends ViewAbstract {
    CssLayout content = null;
    List<Letter> letters;
    
    @Inject
    CachedCollection col;
    
    boolean initialized = false;

    public Letters() {
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        
        if (initialized) {
            return;
        }
        
        this.setCaption("Books");
        
        this.letters = col.getLetters();
        
        refresh();
        initialized = true;
    }

    private void refresh() {
        content = new CssLayout();
        setContent(content);

        VerticalComponentGroup group = new VerticalComponentGroup("Letters");
        for (Letter letter: letters) {
            NavigationButton button = new NavigationButton();
            button.setCaption(letter.getLetter());
            group.addComponent(button);

            final Letter finalLetter = letter;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    LetterDetail view = (LetterDetail) getNavManager().getView("letterdetail");
                    view.setLetter(finalLetter);
                    getNavManager().navigateTo(view);
                }
            });
        }

        content.addComponents(group);
    }
}
