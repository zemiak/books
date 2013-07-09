package com.zemiak.books.phone;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.zemiak.books.phone.boundary.Collection;
import com.zemiak.books.phone.vaadin.NavManager;

@SuppressWarnings("serial")
@Theme("books")
@Title("Books")
@Widgetset("com.zemiak.books.phone.AppWidgetSet")
public class BooksUI extends UI {
    private Collection col;
    private NavManager nav;

    @Override
    protected void init(VaadinRequest request) {
        col = new Collection();
        nav = new NavManager(col);
        setContent(nav);
        
        getPage().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {

            @Override
            public void browserWindowResized(Page.BrowserWindowResizeEvent event) {
                if (event.getWidth() > event.getHeight()) {
                    System.out.println("Landscape");
                } else {
                    System.out.println("Portrait");
                }
            }
        });
        
        nav.navigateTo(nav.getLetters());
    }
}
