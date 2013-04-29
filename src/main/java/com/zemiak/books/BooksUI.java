package com.zemiak.books;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.zemiak.books.vaadin.NavManager;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SuppressWarnings("serial")
@Theme("mobiletheme")
@Title("Books")
@Widgetset("com.zemiak.books.AppWidgetSet")
@CDIUI
@SessionScoped
public class BooksUI extends UI {
    @Inject
    private NavManager manager;
    
    @Override
    protected void init(VaadinRequest request) {
        setContent(manager.getManager());
    }
}
