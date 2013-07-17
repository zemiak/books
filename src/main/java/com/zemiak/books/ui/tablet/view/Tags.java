package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.responsive.Responsive;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.cdi.CDIView;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.client.boundary.CachedCollection;
import com.zemiak.books.client.domain.Tag;
import java.util.List;
import javax.inject.Inject;

@CDIView("tagsTablet")
public class Tags extends ViewAbstract {
    CssLayout grid = null;
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
        grid = new CssLayout();
        grid.setWidth("100%");
        grid.addStyleName("grid");
        setContent(grid);

        for (Tag tag: tags) {
            NavigationButton button = new NavigationButton(tag.getName());
            button.setSizeUndefined();
            grid.addComponent(button);

            final Tag finalTag = tag;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    TagDetail view = (TagDetail) getNavManager().getView("tagdetailTablet");
                    view.setTag(finalTag);
                    getNavManager().navigateTo(view);
                }
            });
        }
        
        new Responsive(grid);
    }
}
