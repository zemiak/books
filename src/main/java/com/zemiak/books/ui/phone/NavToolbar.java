package com.zemiak.books.ui.phone;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;

public class NavToolbar extends Toolbar {
    NavManager nav;

    public NavToolbar(NavManager nav) {
        super();

        this.nav = nav;

        lettersButton();
        tagsButton();
        searchButton();
        aboutButton();
    }

    private void lettersButton() {
        Button button = new Button();
        button.setIcon(new ThemeResource("icons/book.png"));
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                nav.navigateTo("letters");
            }
        });
        addComponent(button);
    }

    private void tagsButton() {
        Button button = new Button();
        button.setIcon(new ThemeResource("icons/tag.png"));
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                nav.navigateTo("tags");
            }
        });
        addComponent(button);
    }

    private void searchButton() {
        Button button = new Button();
        button.setIcon(new ThemeResource("icons/search.png"));
        button.setCaption("Search");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                nav.navigateTo("search");
            }
        });
        addComponent(button);
    }

    private void aboutButton() {
        Button button = new Button();
        button.setIcon(new ThemeResource("icons/stats.png"));
        button.setCaption("Search");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                nav.navigateTo("about");
            }
        });
        addComponent(button);
    }
}
