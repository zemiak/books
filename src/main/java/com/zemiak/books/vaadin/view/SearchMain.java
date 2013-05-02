package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.zemiak.books.domain.Letter;
import com.zemiak.books.vaadin.NavManager;
import com.zemiak.books.vaadin.NavToolbar;

public class SearchMain extends NavigationView implements Component {
    NavManager manager;
    CssLayout content = null;

    public SearchMain(NavManager manager) {
        setCaption("Search");

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
        group.addComponent(searchField);

        NavigationButton button = new NavigationButton();
        button.setCaption("Search");
        group.addComponent(button);

        button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                getNavigationManager().navigateTo(new SearchPage(searchField.getValue(), manager));
            }
        });

        content.addComponent(group);
    }
}
