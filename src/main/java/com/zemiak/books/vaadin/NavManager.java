package com.zemiak.books.vaadin;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.vaadin.view.MainPage;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class NavManager implements Serializable {
    private Toolbar toolbar;
    private NavigationManager manager;
    
    @Inject
    private Collection col;
    
    @PostConstruct
    public void construct() {
        initToolbar();
        initNavigationManager();
    }

    public NavigationManager getManager() {
        return manager;
    }

    private void initToolbar() {
        toolbar = new Toolbar();
    }

    private void initNavigationManager() {
        manager = new NavigationManager(new MainPage(col.getLetters()));
        manager.setMaintainBreadcrumb(true);
    }
}
