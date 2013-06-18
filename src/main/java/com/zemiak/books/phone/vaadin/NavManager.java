package com.zemiak.books.phone.vaadin;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.zemiak.books.phone.boundary.Collection;
import com.zemiak.books.phone.boundary.Statistics;
import com.zemiak.books.phone.vaadin.view.LettersMain;

public class NavManager extends NavigationManager {
    Collection collection;
    Statistics statistics;

    public NavManager(Collection collection, Statistics statistics) {
        super();

        this.collection = collection;
        this.statistics = statistics;

        setCurrentComponent(new LettersMain(this));

        /**
         * To generate a startup image for iOS
         */
        //setCurrentComponent(new EmptyMain());
    }

    public Collection getCollection() {
        return collection;
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
