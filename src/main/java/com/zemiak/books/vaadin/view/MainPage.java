package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.domain.Letter;
import java.util.Collections;
import java.util.List;

public class MainPage extends NavigationView implements Component {
    CssLayout content = null;
    List<Letter> letters;
    
    public MainPage(List<Letter> letters) {
        super("Letters");
        
        this.letters = letters;
    }
    
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        
        if (null != content) {
            return;
        }
        
        content = new CssLayout();
        setContent(content);

        Collections.sort(letters);
        
        VerticalComponentGroup group = new VerticalComponentGroup();
        
        for (Letter letter: letters) {
            NavigationButton button = new NavigationButton();
            button.setCaption(letter.getLetter());
            group.addComponent(button);
            
            final Letter finalLetter = letter;
            
            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    getNavigationManager().navigateTo(new LetterPage(finalLetter));
                }
            });
        }
        
        content.addComponents(group);
    }
}
