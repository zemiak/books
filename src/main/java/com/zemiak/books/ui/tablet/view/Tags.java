package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.responsive.Responsive;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.cdi.CDIView;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.boundary.Collection;
import java.util.List;
import javax.inject.Inject;

@CDIView("tagsTablet")
public class Tags extends ViewAbstract {
    CssLayout grid = null;
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
        grid = new CssLayout();
        grid.setWidth("100%");
        grid.addStyleName("grid");
        setContent(grid);

        for (String tag: tags) {
            NavigationButton button = new NavigationButton(tag);
            button.setSizeUndefined();
            grid.addComponent(button);

            final String finalTag = tag;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    tagView.setTag(finalTag);
                    getNavManager().navigateTo(tagView);
                }
            });
        }
        
        new Responsive(grid);
    }
}
