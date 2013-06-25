package com.zemiak.books.phone.vaadin;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.zemiak.books.phone.boundary.Collection;
import com.zemiak.books.phone.vaadin.view.LettersMain;

public class NavManager extends NavigationManager {
    Collection collection;

    public NavManager(Collection collection) {
        super();

        this.collection = collection;

        setCurrentComponent(new LettersMain(this));

        /**
         * To generate a startup image for iOS
         */
        //setCurrentComponent(new EmptyMain());
    }

    public Collection getCollection() {
        return collection;
    }
}
