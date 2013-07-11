package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.zemiak.books.client.boundary.Collection;
import com.zemiak.books.ui.tablet.NavManager;
import com.zemiak.books.ui.tablet.NavToolbar;

public class About extends NavigationView {
    Collection col;
    CssLayout content = null;
    public final String VERSION = "1.0";
    boolean initialized = false;

    public About() {
        setCaption("Books");
    }
    
    @Override
    public void onBecomingVisible() {
        super.onBecomingVisible();
        
        if (initialized) {
            return;
        }
        
        NavManager manager = (NavManager) getNavigationManager();
        this.col = manager.getCollection();
        this.setToolbar(new NavToolbar(manager));
        
        refresh();
        initialized = true;
    }
    
    private void refresh() {
        content = new CssLayout();
        setContent(content);
        
        Table table = new Table("Statistics");
        table.addContainerProperty(1, String.class, null);
        table.addContainerProperty(2, Label.class, null);
        table.setColumnAlignment(2, Table.Align.RIGHT);
        table.setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        table.setWidth("100%");
        table.setHeight("10em");
        
        table.addItem(new Object[]{"Version", 
            new Label(getBold(VERSION), ContentMode.HTML)}, 1);
        table.addItem(new Object[]{"Authors", 
            new Label(getBold(String.valueOf(col.getAuthorsCount())), ContentMode.HTML)}, 2);
        table.addItem(new Object[]{"Documented Authors", 
            new Label(getBold(String.valueOf(col.getAuthorsDocumentedCount())), ContentMode.HTML)}, 3);
        table.addItem(new Object[]{"Tagged Authors", 
            new Label(getBold(String.valueOf(col.getAuthorsTaggedCount())), ContentMode.HTML)}, 4);
        table.addItem(new Object[]{"Books", 
            new Label(getBold(String.valueOf(col.getBooksCount())), ContentMode.HTML)}, 5);

        content.addComponent(table);
    }

    private String getBold(String text) {
        return "<b>" + text + "</b>";
    }
}
