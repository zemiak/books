package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.zemiak.books.ui.tablet.NavManagerTablet;
import com.zemiak.books.ui.tablet.NavToolbarTablet;

abstract public class ViewAbstractTablet extends NavigationView implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        onBecomingVisible();
    }
    
    public NavManagerTablet getNavManager() {
        return (NavManagerTablet) getNavigationManager();
    }
    
    @Override
    public void attach() {
        super.attach();
        
        Toolbar toolbar = new NavToolbarTablet(getNavManager());
        this.setToolbar(toolbar);
    }
}
