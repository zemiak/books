package com.zemiak.books.ui.tablet;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.View;

public class NavManagerTablet extends NavigationManager {
    private CDIViewProvider viewProvider;
    
    public NavManagerTablet() {
        super();
    }

    public CDIViewProvider getViewProvider() {
        return viewProvider;
    }

    public void setViewProvider(CDIViewProvider viewProvider) {
        this.viewProvider = viewProvider;
    }
    
    public View getView(String name) {
        return this.viewProvider.getView(name);
    }
    
    public void navigateTo(String name) {
        this.navigateTo((NavigationView) getView(name));
    }
}
