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
        
        s.getApplicationIcons().addApplicationIcon(57, 57, "/img/icon-57.jpg", true);
        s.getApplicationIcons().addApplicationIcon(72, 72, "/img/icon-72.jpg", true);
        s.getApplicationIcons().addApplicationIcon(144, 144, "/img/icon-144.jpg", true);
        s.getApplicationIcons().addApplicationIcon(512, 512, "/img/icon-512.jpg", true);
        s.getApplicationIcons().addApplicationIcon(1024, 1024, "/img/icon-1024.jpg", true);
        
        ViewPortSettings vp = s.getViewPortSettings();
        vp.setViewPortUserScalable(false);
        
        s.getWebAppSettings().setWebAppCapable(true);
        //s.getWebAppSettings().setStartupImage("/img/startup-screen.jpg");
    }
}
