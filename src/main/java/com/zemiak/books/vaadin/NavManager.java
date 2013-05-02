package com.zemiak.books.vaadin;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.service.Statistics;
import com.zemiak.books.vaadin.view.EmptyMain;
import com.zemiak.books.vaadin.view.LettersMain;

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
