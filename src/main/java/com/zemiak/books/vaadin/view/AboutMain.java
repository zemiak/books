package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.service.Statistics;
import com.zemiak.books.vaadin.NavManager;
import com.zemiak.books.vaadin.NavToolbar;

public class AboutMain extends NavigationView implements Component {
    Statistics stat;
    CssLayout content = null;
    public final String VERSION = "1.0";

    public AboutMain(NavManager manager) {
        setCaption("About");

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

        VerticalComponentGroup group = new VerticalComponentGroup();

        NavigationButton.NavigationButtonClickListener listener =
            new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    // pass
                }
            };

        NavigationButton label = new NavigationButton("Version");
        label.setDescription(VERSION);
        label.addClickListener(listener);
        group.addComponent(label);

        label = new NavigationButton("Authors");
        label.setDescription(String.valueOf(stat.getAuthors()));
        label.addClickListener(listener);
        group.addComponent(label);

        label = new NavigationButton("Documented Authors");
        label.setDescription(String.valueOf(stat.getAuthorsDocumented()));
        label.addClickListener(listener);
        group.addComponent(label);

        label = new NavigationButton("Tagged Authors");
        label.setDescription(String.valueOf(stat.getAuthorsTagged()));
        label.addClickListener(listener);
        group.addComponent(label);

        label = new NavigationButton("Books");
        label.setDescription(String.valueOf(stat.getBooks()));
        label.addClickListener(listener);
        group.addComponent(label);

        content.addComponent(group);
    }
}
