package com.zemiak.books.vaadin;

import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.service.Statistics;
import com.zemiak.books.vaadin.view.AboutMain;
import com.zemiak.books.vaadin.view.LettersMain;
import com.zemiak.books.vaadin.view.SearchMain;
import com.zemiak.books.vaadin.view.TagsMain;

public class NavToolbar extends Toolbar {
    NavManager nav;
    Collection col;
    Statistics stat;

    public NavToolbar(NavManager nav) {
        super();

        this.nav = nav;
        this.col = nav.getCollection();
        this.stat = nav.getStatistics();

        lettersButton();
        tagsButton();
        searchButton();
        aboutButton();
    }

    private void lettersButton() {
        Button button = new Button();
        button.setIcon(new ThemeResource("../runo/icons/64/users.png"));
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                nav.setCurrentComponent(new LettersMain(nav));
            }
        });
        addComponent(button);
    }

    private void tagsButton() {
        Button button = new Button();
        button.setIcon(new ThemeResource("../runo/icons/64/note.png"));
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                nav.setCurrentComponent(new TagsMain(nav));
            }
        });
        addComponent(button);
    }

    private void searchButton() {
        Button button = new Button();
        button.setIcon(new ThemeResource("../runo/icons/64/document-edit.png"));
        button.setCaption("Search");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                nav.setCurrentComponent(new SearchMain(nav));
            }
        });
        addComponent(button);
    }

    private void aboutButton() {
        Button button = new Button();
        button.setIcon(new ThemeResource("../runo/icons/64/folder.png"));
        button.setCaption("Search");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                nav.setCurrentComponent(new AboutMain(nav));
            }
        });
        addComponent(button);
    }
}
