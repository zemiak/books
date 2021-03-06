package com.zemiak.books.ui.tablet.view;

import com.vaadin.cdi.CDIView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.zemiak.books.boundary.BatchRunner;
import com.zemiak.books.domain.CacheClearEvent;
import com.zemiak.books.service.Statistics;
import javax.inject.Inject;

@CDIView("aboutTablet")
class AboutTablet extends ViewAbstractTablet {
    @Inject
    Statistics stats;
    
    @Inject 
    private javax.enterprise.event.Event<CacheClearEvent> clearEvent;
    
    @Inject
    BatchRunner runner;
    
    CssLayout content = null;
    public final String VERSION = "1.0";
    boolean initialized = false;

    public AboutTablet() {
    }
    
    @Override
    public void onBecomingVisible() {
        super.onBecomingVisible();
        this.setCaption("About");
        
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
            new Label(getBold(String.valueOf(stats.getAuthors())), ContentMode.HTML)}, 2);
        table.addItem(new Object[]{"Documented Authors", 
            new Label(getBold(String.valueOf(stats.getAuthorsDocumented())), ContentMode.HTML)}, 3);
        table.addItem(new Object[]{"Tagged Authors", 
            new Label(getBold(String.valueOf(stats.getAuthorsTagged())), ContentMode.HTML)}, 4);
        table.addItem(new Object[]{"Books", 
            new Label(getBold(String.valueOf(stats.getBooks())), ContentMode.HTML)}, 5);

        content.addComponent(table);
        
        NativeButton clearCacheButton = new NativeButton("Clear Cache", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                clearEvent.fire(new CacheClearEvent());
                Notification.show("Data Cache Cleared", Notification.Type.HUMANIZED_MESSAGE);
            }
        });
        clearCacheButton.setSizeFull();
        content.addComponent(clearCacheButton);
        
        NativeButton updateCollectionButton = new NativeButton("Update Collection", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (runner.isUpdateCollectionRunning()) {
                    Notification.show("Job is still running");
                } else {
                    runner.runUpdateCollection();
                }
            }
        });
        updateCollectionButton.setSizeFull();
        content.addComponent(updateCollectionButton);
    }

    private String getBold(String text) {
        return "<b>" + text + "</b>";
    }
}
