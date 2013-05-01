package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Component;
import com.zemiak.books.vaadin.NavManager;

public class SearchMain extends NavigationView implements Component {
    public SearchMain(NavManager manager) {
        setCaption("Search");
        
        this.setToolbar(manager.getToolbar());
    }
}
