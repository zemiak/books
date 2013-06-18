package com.zemiak.books.phone.vaadin.view;

import com.vaadin.addon.touchkit.extensions.Html5InputSettings;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.zemiak.books.phone.vaadin.NavManager;
import com.zemiak.books.phone.vaadin.NavToolbar;

public class SearchMain extends NavigationView implements Component {
    NavManager manager;
    Layout form = null;
    final TextField searchField = new TextField();
    
    public SearchMain(NavManager manager) {
        setCaption("Books");

        this.manager = manager;

        this.setToolbar(new NavToolbar(manager));
        refresh();
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        searchField.focus();
    }

    private void refresh() {
        form = new CssLayout();
        setContent(form);
        
        PropertysetItem item = new PropertysetItem();
        item.addItemProperty("name", new ObjectProperty<>(""));
        
        VerticalComponentGroup group = new VerticalComponentGroup("Search");
        
        final Html5InputSettings html5InputSettings = new Html5InputSettings(
                searchField);
        html5InputSettings.setProperty("type", "search");
        html5InputSettings.setProperty("autocomplete", "off");
        html5InputSettings.setProperty("autocorrect", "off");
        html5InputSettings.setProperty("autocapitalize", "off");
        html5InputSettings.setProperty("placeholder", "Search...");
        
        
        searchField.setImmediate(true);
        searchField.focus();
        group.addComponent(searchField);

        Button button = new Button();
        button.setCaption("Search");
        button.setVisible(false);
        button.setClickShortcut(KeyCode.ENTER);
        group.addComponent(button);
        
        NavigationButton navButton = new NavigationButton();
        navButton.setCaption("Search");
        navButton.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                getNavigationManager().navigateTo(new SearchPage(searchField.getValue(), manager));
            }
        });
        group.addComponent(navButton);
        
        FieldGroup binder = new FieldGroup(item);
        binder.bind(searchField, "name");
        
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getNavigationManager().navigateTo(new SearchPage(searchField.getValue(), manager));
            }
        });

        form.addComponent(group);
    }
}
