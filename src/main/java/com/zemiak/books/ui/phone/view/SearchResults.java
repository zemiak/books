package com.zemiak.books.ui.phone.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.cdi.CDIView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.zemiak.books.client.boundary.CachedCollection;
import com.zemiak.books.client.domain.Author;
import com.zemiak.books.client.domain.Book;
import java.util.List;
import javax.inject.Inject;

@CDIView("searchresults")
public class SearchResults extends ViewAbstract {
    CssLayout content = null;

    @Inject
    CachedCollection col;
    
    String text;

    private List<Author> authors;
    private List<Book> books;

    public SearchResults() {
    }
    
    public void setText(String text) {
        this.text = text.trim().toLowerCase();
        refreshData();
    }
    
    private void refreshData() {
        authors = col.getAuthorsByExpression(this.text);
        books = col.getBooksByExpression(this.text);
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        setCaption("?" + text);
        
        refresh();
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
            Label searchText = new Label("No results for text: <b>" + text + "</b>");
            searchText.setContentMode(ContentMode.HTML);
            content.addComponent(searchText);
            return;
        }
        
        if (! books.isEmpty()) {
            VerticalComponentGroup group = new VerticalComponentGroup("Books: " + books.size());

            for (Book book: books) {
                NavigationButton button = new NavigationButton();
                button.setDescription(getHighlightedValue(book.getName()));
                group.addComponent(button);

                final Book finalBook = book;

                button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                    @Override
                    public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                        BookDetail view = (BookDetail) getNavManager().getView("bookdetail");
                        view.setBook(finalBook);
                        getNavManager().navigateTo(view);
                    }
                });
            }

            content.addComponents(group);
        }

        if (! authors.isEmpty()) {
            VerticalComponentGroup group = new VerticalComponentGroup("Authors: " + authors.size());

            for (Author author: authors) {
                NavigationButton button = new NavigationButton();
                button.setDescription(getHighlightedValue(author.getName()));
                group.addComponent(button);

                Button b = new Button();
                b.setHtmlContentAllowed(true);

                final Author finalAuthor = author;

                button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                    @Override
                    public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                        AuthorDetail view = (AuthorDetail) getNavManager().getView("authordetail");
                        view.setAuthor(finalAuthor);
                        getNavManager().navigateTo(view);
                    }
                });
            }

            content.addComponents(group);
        }
    }
}
