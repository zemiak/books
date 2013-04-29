package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Component;
import com.zemiak.books.domain.Tag;

/**
 *
 * @author vasko
 */
class TagPage extends NavigationView implements Component {

    public TagPage(Tag tag) {
        setCaption(tag.getName());
    }
    
}
