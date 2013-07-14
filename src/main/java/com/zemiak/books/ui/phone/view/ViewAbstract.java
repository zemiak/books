package com.zemiak.books.ui.phone.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
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
    protected void onBecomingVisible() {
        super.onBecomingVisible();

        this.setToolbar(new NavToolbar(getNavManager()));
    }
}
