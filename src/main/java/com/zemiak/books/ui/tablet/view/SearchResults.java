package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.responsive.Responsive;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Book;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Dependent
public class SearchResults extends ViewAbstract {
    CssLayout grid = null;

    String text;
    
    @Inject
    Collection col;
    
    @Inject
    Instance<BookDetail> bookView;
    
    @Inject
    Instance<AuthorDetail> authorView;

    private List<Author> authors;
    private List<Book> books;

    public SearchResults() {
    }
    
    public void setText(String text) {
        this.text = text;
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
        grid = new CssLayout();
        grid.setWidth("100%");
        grid.addStyleName("grid");
        setContent(grid);

        if (text.isEmpty()) {
            grid.addComponent(new Label("Cannot search for an empty string"));
            return;
        }
        
        if (books.isEmpty() && authors.isEmpty()) {
            Label searchText = new Label("No results for text: <b>" + text + "</b>");
            searchText.setContentMode(ContentMode.HTML);
            grid.addComponent(searchText);
            return;
        }
        
        new Responsive(grid);
        
        if (! books.isEmpty()) {
            for (Book book: books) {
                NavigationButton button = new NavigationButton();
                button.setSizeUndefined();
                button.setDescription(getHighlightedValue(book.getName()));
                grid.addComponent(button);

                final Book finalBook = book;

                button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                    @Override
                    public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                        BookDetail view = bookView.get();
                        view.setBook(finalBook);
                        getNavManager().navigateTo(view);
                    }
                });
            }
        }

        if (! authors.isEmpty()) {
            for (Author author: authors) {
                NavigationButton button = new NavigationButton();
                button.setSizeUndefined();
                button.setDescription(getHighlightedValue(author.getName()));
                grid.addComponent(button);

                final Author finalAuthor = author;

                button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                    @Override
                    public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                        AuthorDetail view = authorView.get();
                        view.setAuthor(finalAuthor);
                        getNavManager().navigateTo(view);
                    }
                });
            }
        }
    }
}
