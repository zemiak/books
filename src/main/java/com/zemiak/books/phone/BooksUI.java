package com.zemiak.books.phone;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.zemiak.books.phone.boundary.Collection;
import com.zemiak.books.phone.vaadin.NavManager;

@SuppressWarnings("serial")
@Theme("books")
@Title("Books")
@Widgetset("com.zemiak.books.phone.AppWidgetSet")
public class BooksUI extends UI {
    private Collection col = new Collection();

    @Override
    protected void init(VaadinRequest request) {
        setContent(new NavManager(col));
    }
}
