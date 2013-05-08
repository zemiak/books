package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Book;
import com.zemiak.books.vaadin.NavManager;
import com.zemiak.books.vaadin.NavToolbar;
import java.util.Collections;
import java.util.List;

public class SearchPage extends NavigationView implements Component {
    CssLayout content = null;
    NavManager manager;
    Collection col;
    String text;

    private List<Author> authors;
    private List<Book> books;

    public SearchPage(String text, NavManager manager) {
        super("Search Results");

        col = manager.getCollection();
        this.manager = manager;
        this.text = text.trim().toLowerCase();

        if (! this.text.isEmpty()) {
            authors = col.getAuthorsByExpression(this.text);
            books = col.getBooksByExpression(this.text);
        }

        this.setToolbar(new NavToolbar(manager));
        refresh();
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
    }

    private String getHighlightedValue(String value) {
        String highlighted = "";
        int i = value.toLowerCase().indexOf(text);

        if (i > 0) {
            highlighted = value.substring(0, i);
        }

        // Gold color
        highlighted += "<span style=\"background-color: #FFD700;\">"
                + value.substring(i, i + text.length())
                + "</span>";

        if (value.length() > i + text.length()) {
            highlighted += value.substring(i + text.length(), value.length());
        }

        return highlighted;
    }

    private void refresh() {
        content = new CssLayout();
        setContent(content);

        if (text.isEmpty()) {
            content.addComponent(new Label("Cannot search for an empty string"));
            return;
        }

        if (books.isEmpty() && authors.isEmpty()) {
            content.addComponent(new Label("No search results"));
            return;
        }
        
        Label searchText = new Label("Search text: <b>" + text + "</b>");
        searchText.setContentMode(ContentMode.HTML);
        content.addComponent(searchText);

        if (! books.isEmpty()) {
            VerticalComponentGroup group = new VerticalComponentGroup("Books: " + books.size());

            Collections.sort(books);
            for (Book book: books) {
                NavigationButton button = new NavigationButton();
                //button.setCaption((book.getName()));
                button.setDescription(getHighlightedValue(book.getName()));
                group.addComponent(button);

                final Book finalBook = book;

                button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                    @Override
                    public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                        getNavigationManager().navigateTo(new BookPage(finalBook, manager));
                    }
                });
            }

            content.addComponents(group);
        }

        if (! authors.isEmpty()) {
            VerticalComponentGroup group = new VerticalComponentGroup("Authors: " + authors.size());

            Collections.sort(authors);
            for (Author author: authors) {
                NavigationButton button = new NavigationButton();
                //button.setCaption((author.getName()));
                button.setDescription(getHighlightedValue(author.getName()));
                group.addComponent(button);

                Button b = new Button();
                b.setHtmlContentAllowed(true);

                final Author finalAuthor = author;

                button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                    @Override
                    public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                        getNavigationManager().navigateTo(new AuthorPage(finalAuthor, manager));
                    }
                });
            }

            content.addComponents(group);
        }
    }
}
