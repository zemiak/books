package com.zemiak.books.vaadin;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.vaadin.view.LettersMain;
import com.zemiak.books.vaadin.view.SearchMain;
import com.zemiak.books.vaadin.view.TagsMain;

public class NavManager extends NavigationManager {
    private Collection col;
    
    public NavManager(Collection col) {
        super();
        this.setCurrentComponent(new LettersMain(col.getLetters(), col, this));
        
        this.col = col;
    }
    
    public Toolbar getToolbar() {
        final NavManager that = this;
        final Toolbar toolbar = new Toolbar();
        
        Button button = new Button();
        button.setIcon(new ThemeResource("../books/icons/letter-a.png"));
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                that.setCurrentComponent(new LettersMain(col.getLetters(), col, that));
            }
        });
        toolbar.addComponent(button);
        
        button = new Button();
        button.setIcon(new ThemeResource("../books/icons/tag.png"));
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                that.setCurrentComponent(new TagsMain(col.getTags(), col, that));
            }
        });
        toolbar.addComponent(button);
        
        button = new Button();
        //button.setIcon(new ThemeResource("../books/icons/tag.png"));
        button.setCaption("Search");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                that.setCurrentComponent(new SearchMain(that));
            }
        });
        toolbar.addComponent(button);
        
        return toolbar;
    }
}
