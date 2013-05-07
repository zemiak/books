package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.zemiak.books.vaadin.NavManager;
import com.zemiak.books.vaadin.NavToolbar;

public class SearchMain extends NavigationView implements Component {
    NavManager manager;
    CssLayout content = null;

    public SearchMain(NavManager manager) {
        setCaption("Books");

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

        VerticalComponentGroup group = new VerticalComponentGroup();

        final TextField searchField = new TextField();
        searchField.setInputPrompt("Search...");
        searchField.focus();
        searchField.setSizeFull();
        searchField.setImmediate(true);
        group.addComponent(searchField);

        Button button = new Button();
        button.setCaption("Search");
        button.setClickShortcut(KeyCode.ENTER);
        group.addComponent(button);
        
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getNavigationManager().navigateTo(new SearchPage(searchField.getValue(), manager));
            }
        });

        content.addComponent(group);
    }
}
