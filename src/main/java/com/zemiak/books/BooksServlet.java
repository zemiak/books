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

        s.getApplicationIcons().addApplicationIcon(57, 57, "./VAADIN/themes/books/icons/icon-57.jpg", false);
        s.getApplicationIcons().addApplicationIcon(72, 72, "./VAADIN/themes/books/icons/icon-72.jpg", false);
        s.getApplicationIcons().addApplicationIcon(144, 144, "./VAADIN/themes/books/icons/icon-144.jpg", false);
        s.getApplicationIcons().addApplicationIcon(512, 512, "./VAADIN/themes/books/icons/icon-512.jpg", false);
        s.getApplicationIcons().addApplicationIcon(1024, 1024, "./VAADIN/themes/books/icons/icon-1024.jpg", false);

        ViewPortSettings vp = s.getViewPortSettings();
        vp.setViewPortUserScalable(false);

        s.getWebAppSettings().setWebAppCapable(true);
        //s.getWebAppSettings().setStartupImage("./VAADIN/themes/books/img/startup-screen.png");
    }
}
