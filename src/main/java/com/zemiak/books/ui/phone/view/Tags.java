package com.zemiak.books.ui.phone.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.cdi.CDIView;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.boundary.Collection;
import java.util.List;
import javax.inject.Inject;

@CDIView("tags")
public class Tags extends ViewAbstract {
    CssLayout content = null;
    List<String> tags;
    
    @Inject
    Collection col;
    
    @Inject
    TagDetail tagView;
    
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
        
        this.tags = col.getTags();
        
        refresh();
        initialized = true;
    }

    private void refresh() {
        content = new CssLayout();
        setContent(content);

        VerticalComponentGroup group = new VerticalComponentGroup("Tags");

        for (String tag: tags) {
            NavigationButton button = new NavigationButton(tag);
            group.addComponent(button);

            final String finalTag = tag;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    tagView.setTag(finalTag);
                    getNavManager().navigateTo(tagView);
                }
            });
        }

        content.addComponents(group);
    }
}
