package com.zemiak.books.ui.tablet;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.zemiak.books.client.boundary.Collection;
import com.zemiak.books.ui.tablet.view.About;
import com.zemiak.books.ui.tablet.view.Letters;
import com.zemiak.books.ui.tablet.view.Search;
import com.zemiak.books.ui.tablet.view.Tags;

public class NavManager extends NavigationManager {
    Collection collection;
    
    NavigationView about;
    NavigationView letters;
    NavigationView tags;
    NavigationView search;
    
    public NavManager(Collection collection) {
        super();

        this.collection = collection;

        initViews();
    }

    public Collection getCollection() {
        return collection;
    }
    
    private void initViews() {
        tags = new Tags();
        about = new About();
        search = new Search();
        letters = new Letters();
        
        
        addComponents(letters, search, about, tags);
    }

    public NavigationView getAbout() {
        return about;
    }

    public NavigationView getLetters() {
        return letters;
    }

    public NavigationView getTags() {
        return tags;
    }

    public NavigationView getSearch() {
        return search;
    }
}
