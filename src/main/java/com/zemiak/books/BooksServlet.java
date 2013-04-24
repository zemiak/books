package com.zemiak.books;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.addon.touchkit.settings.TouchKitSettings;
import com.vaadin.addon.touchkit.settings.ViewPortSettings;
import javax.servlet.ServletException;

public class BooksServlet extends TouchKitServlet {
    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();

        TouchKitSettings s = getTouchKitSettings();
        
        s.getApplicationIcons().addApplicationIcon(57, 57, "/books/img/icon-57.jpg", true);
        s.getApplicationIcons().addApplicationIcon(72, 72, "/books/img/icon-72.jpg", true);
        s.getApplicationIcons().addApplicationIcon(144, 144, "/books/img/icon-144.jpg", true);
        s.getApplicationIcons().addApplicationIcon(512, 512, "/books/img/icon-512.jpg", true);
        s.getApplicationIcons().addApplicationIcon(1024, 1024, "/books/img/icon-1024.jpg", true);
        
        ViewPortSettings vp = s.getViewPortSettings();
        vp.setViewPortUserScalable(false);
        
        s.getWebAppSettings().setWebAppCapable(true);
        //s.getWebAppSettings().setStartupImage("/books/img/startup-screen.jpg");
    }
}
