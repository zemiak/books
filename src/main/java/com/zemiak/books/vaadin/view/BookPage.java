package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Link;
import com.zemiak.books.domain.Book;
import com.zemiak.books.vaadin.NavManager;
import com.zemiak.books.vaadin.NavToolbar;
import java.io.File;

/**
 *
 * @author vasko
 */
class BookPage extends NavigationView implements Component {
    CssLayout content = null;
    NavManager manager;
    Book book;

    public BookPage(Book book, NavManager manager) {
        super(book.getName());

        this.book = book;
        this.manager = manager;

        this.setToolbar(new NavToolbar(manager));
        refresh();
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
    }

    private void refresh() {
        content = new CssLayout();
        setContent(content);

        VerticalComponentGroup group1 = new VerticalComponentGroup();

        if (book.getMobiFileName() != null) {
            Link button = new Link();
            button.setCaption("Kindle Format");
            group1.addComponent(button);

            FileDownloader fileDownloader = new FileDownloader(new FileResource(new File(book.getMobiFileName())));
            fileDownloader.extend(button);
        }

        if (book.getEpubFileName() != null) {
            Link button = new Link();
            button.setCaption("iBooks Format");
            group1.addComponent(button);

            FileDownloader fileDownloader = new FileDownloader(new FileResource(new File(book.getEpubFileName())));
            fileDownloader.extend(button);
        }

        content.addComponent(group1);
    }
}
