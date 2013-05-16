package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.zemiak.books.boundary.Statistics;
import com.zemiak.books.vaadin.NavManager;
import com.zemiak.books.vaadin.NavToolbar;

public class AboutMain extends NavigationView implements Component {
    Statistics stat;
    CssLayout content = null;
    public final String VERSION = "1.0";

    public AboutMain(NavManager manager) {
        setCaption("Books");

        this.stat = manager.getStatistics();

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
            new Label(getBold(String.valueOf(stat.getAuthors())), ContentMode.HTML)}, 2);
        table.addItem(new Object[]{"Documented Authors", 
            new Label(getBold(String.valueOf(stat.getAuthorsDocumented())), ContentMode.HTML)}, 3);
        table.addItem(new Object[]{"Tagged Authors", 
            new Label(getBold(String.valueOf(stat.getAuthorsTagged())), ContentMode.HTML)}, 4);
        table.addItem(new Object[]{"Books", 
            new Label(getBold(String.valueOf(stat.getBooks())), ContentMode.HTML)}, 5);

        content.addComponent(table);
    }

    private String getBold(String text) {
        return "<b>" + text + "</b>";
    }
}
