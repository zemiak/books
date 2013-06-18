package com.zemiak.books.phone.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

/**
 * To produce a loading image for iOS
 */
public class EmptyMain extends NavigationView implements Component {
    public EmptyMain() {
        setToolbar(new Toolbar());

        refresh();
    }

    private void refresh() {
        CssLayout content = new CssLayout();
        setContent(content);

        VerticalComponentGroup group = new VerticalComponentGroup();
        for (int i =0; i < 26; i++) {
            NavigationButton button = new NavigationButton();
            button.setDescription("&nbsp;");

            group.addComponent(button);
        }

        content.addComponent(group);
    }
}
