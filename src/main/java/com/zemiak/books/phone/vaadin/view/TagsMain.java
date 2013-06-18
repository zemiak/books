package com.zemiak.books.phone.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.phone.boundary.Collection;
import com.zemiak.books.phone.domain.Tag;
import com.zemiak.books.phone.vaadin.NavManager;
import com.zemiak.books.phone.vaadin.NavToolbar;
import java.util.Collections;
import java.util.List;

public class TagsMain extends NavigationView implements Component {
    CssLayout content = null;
    List<Tag> tags;
    NavManager manager;
    Collection col;

    public TagsMain(NavManager manager) {
        super("Books");


        this.manager = manager;
        this.col = manager.getCollection();
        this.tags = col.getDistinctTags();

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

        Collections.sort(tags);
        System.err.println(tags);

        VerticalComponentGroup group = new VerticalComponentGroup("Tags");

        for (Tag tag: tags) {
            NavigationButton button = new NavigationButton(tag.getName());
            group.addComponent(button);

            final Tag finalTag = tag;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    getNavigationManager().navigateTo(new TagPage(finalTag.getName(), manager));
                }
            });
        }

        content.addComponents(group);
    }
}
