package com.zemiak.books.ui.phone.view;

import com.vaadin.cdi.CDIView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.zemiak.books.client.boundary.CacheClearEvent;
import com.zemiak.books.client.boundary.CachedCollection;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@CDIView("about")
public class About extends ViewAbstract {
    @Inject
    CachedCollection col;
    
    @Inject 
    private javax.enterprise.event.Event<CacheClearEvent> clearEvent;
    
    CssLayout content = null;
    public final String VERSION = "1.0";
    boolean initialized = false;

    public About() {
    }
    
    @PostConstruct
    public void init() {
        this.setCaption("Books");
    }
    
    @Override
    public void onBecomingVisible() {
        super.onBecomingVisible();
        
        if (initialized) {
            return;
        }
        
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
        content.addComponent(new Button("Clear Cache", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                clearEvent.fire(new CacheClearEvent());
                Notification.show("Data Cache Cleared", Notification.Type.HUMANIZED_MESSAGE);
            }
        }));
    }

    private String getBold(String text) {
        return "<b>" + text + "</b>";
    }
}