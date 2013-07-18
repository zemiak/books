package com.zemiak.books.ui.phone.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.cdi.CDIView;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.client.boundary.CachedCollection;
import com.zemiak.books.client.domain.Tag;
import java.util.List;
import javax.inject.Inject;

@CDIView("tags")
public class Tags extends ViewAbstract {
    CssLayout content = null;
    List<Tag> tags;
    
    @Inject
    CachedCollection col;
    
    boolean initialized = false;

    public Tags() {
    }
    
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        setCaption("Tags");
        
        if (initialized) {
            return;
        }
        
        this.tags = col.getDistinctTags();
        
        refresh();
        initialized = true;
    }

    private void refresh() {
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
                    TagDetail view = new TagDetail(finalTag);
                    getNavManager().navigateTo(view);
                }
            });
        }

        content.addComponents(group);
    }
}
