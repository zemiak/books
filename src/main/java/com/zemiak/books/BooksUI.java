package com.zemiak.books;

import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("mobiletheme")
@Title("Books")
@Widgetset("com.zemiak.books.AppWidgetSet")
public class BooksUI extends UI {
    private TabBarView bar;
    
    @Override
    protected void init(VaadinRequest request) {
        initTabBar();
        
        initLettersTab();
        initTagsTab();
        initSearchTab();
    }

    private void initTabBar() {
        bar = new TabBarView();
        setContent(bar);
    }

    private void initLettersTab() {
        Label content = new Label("Here be letters");

        Tab tab = bar.addTab(content);
        tab.setCaption("Letters");
    }

    private void initTagsTab() {
        Label content = new Label("Here be tags");

        Tab tab = bar.addTab(content);
        tab.setCaption("Tags");
    }

    private void initSearchTab() {
        Label content = new Label("Here be search");

        Tab tab = bar.addTab(content);
        tab.setCaption("Search");
    }
}
