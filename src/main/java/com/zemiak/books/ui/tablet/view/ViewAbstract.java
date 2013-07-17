package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.zemiak.books.ui.phone.NavManager;
import com.zemiak.books.ui.phone.NavToolbar;

abstract public class ViewAbstract extends NavigationView implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        onBecomingVisible();
    }
    
    public NavManager getNavManager() {
        return (NavManager) getNavigationManager();
    }
    
    @Override
    public void attach() {
        super.attach();
        
        Toolbar toolbar = new NavToolbar(getNavManager());
        this.setToolbar(toolbar);
    }
}
