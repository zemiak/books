package com.zemiak.books;

import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.zemiak.books.boundary.Collection;
import javax.inject.Inject;

@SuppressWarnings("serial")
@Theme("books")
@Title("Books")
@Widgetset("com.zemiak.books.ui.phone.AppWidgetSet")
@CDIUI
public class BooksUI extends UI {
    final boolean DEBUG_PHONE = false;
    
    private Collection col;
    
    @Inject
    private CDIViewProvider viewProvider;
    
    private Toolbar toolbar;

    @Override
    protected void init(VaadinRequest request) {
        initCollection();
        
        String agent = request.getHeader("user-agent");
        
        if (DEBUG_PHONE || (null != agent && agent.contains("iPhone"))) {
            initPhone();
        } else {
            initTablet();
        }
    }

    private void initPhone() {
        getPage().addBrowserWindowResizeListener(new com.zemiak.books.ui.phone.ScreenSizeListener());
        
        com.zemiak.books.ui.phone.NavManager nav = new com.zemiak.books.ui.phone.NavManager();
        setContent(nav);
        
        toolbar = new com.zemiak.books.ui.phone.NavToolbar(nav);
        
        nav.setViewProvider(viewProvider);
        nav.navigateTo("letters");
    }

    private void initCollection() {
        col = new Collection();
    }

    private void initTablet() {
        getPage().addBrowserWindowResizeListener(new com.zemiak.books.ui.tablet.ScreenSizeListener());
        
        com.zemiak.books.ui.tablet.NavManager nav = new com.zemiak.books.ui.tablet.NavManager();
        setContent(nav);
        
        toolbar = new com.zemiak.books.ui.tablet.NavToolbar(nav);
        
        nav.setViewProvider(viewProvider);
        nav.navigateTo("lettersTablet");
    }
    
    public Toolbar getToolbar() {
        return toolbar;
    }
}
