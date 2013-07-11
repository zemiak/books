package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.client.boundary.Collection;
import com.zemiak.books.client.domain.Tag;
import com.zemiak.books.ui.tablet.NavManager;
import com.zemiak.books.ui.tablet.NavToolbar;
import java.util.List;

public class Tags extends NavigationView {
    CssLayout content = null;
    List<Tag> tags;
    NavManager manager = null;
    Collection col;
    boolean initialized = false;

    public Tags() {
        super("Books");
    }

    @Override
    protected void onBecomingVisible() {
        System.out.println("Tags.onBecomingVisible()");
        
        super.onBecomingVisible();
        
        if (initialized) {
            return;
        }
        
        if (null == manager) this.manager = (NavManager) getNavigationManager();
        this.setToolbar(new NavToolbar(manager));
        
        this.col = manager.getCollection();
        this.tags = col.getDistinctTags();
        
        refresh();
        initialized = true;
    }

    private void refresh() {
        System.out.println("Tags.refresh()");
        
        content = new CssLayout();
        setContent(content);

        VerticalComponentGroup group = new VerticalComponentGroup("Tags");

        for (Tag tag: tags) {
            NavigationButton button = new NavigationButton(tag.getName());
            group.addComponent(button);

            final Tag finalTag = tag;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    getNavigationManager().navigateTo(new TagDetail(finalTag.getName(), manager));
                }
            });
        }

        content.addComponents(group);
    }
}
