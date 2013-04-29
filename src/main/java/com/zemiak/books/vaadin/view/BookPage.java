package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Component;
import com.zemiak.books.domain.Book;

/**
 *
 * @author vasko
 */
class BookPage extends NavigationView implements Component {

    public BookPage(Book book) {
        setCaption(book.getName());
    }
    
}
