package com.zemiak.books.ui.tablet;

import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;

public class NavToolbarTablet extends Toolbar {
    NavManagerTablet nav;

    public NavToolbarTablet(NavManagerTablet nav) {
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
        button.setCaption("Letters");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                nav.navigateTo("lettersTablet");
            }
        });
        addComponent(button);
    }

    private void tagsButton() {
        Button button = new Button();
        button.setIcon(new ThemeResource("icons/tag.png"));
        button.setCaption("Tags");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                nav.navigateTo("tagsTablet");
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
                nav.navigateTo("searchTablet");
            }
        });
        addComponent(button);
    }

    private void aboutButton() {
        Button button = new Button();
        button.setIcon(new ThemeResource("icons/stats.png"));
        button.setCaption("About");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                nav.navigateTo("aboutTablet");
            }
        });
        addComponent(button);
    }
}
