package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.responsive.Responsive;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Book;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class SourceResults extends ViewAbstract {

    CssLayout grid = null;
    
    @Inject
    Collection col;
    
    Book.BookSource source;
    private List<Book> books;
    
    @Inject
    BookDetail bookView;

    public SourceResults() {
    }
    
    public void setSource(Book.BookSource source) {
        this.source = source;

        refreshData();
    }

    private void refreshData() {
        books = col.getBooksBySource(source);
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        setCaption("Source: " + Book.getSourceCaption(source));

        refresh();
    }

    private void refresh() {
        grid = new CssLayout();
        grid.setWidth("100%");
        grid.addStyleName("grid");
        setContent(grid);

        new Responsive(grid);

        for (Book book : books) {
            NavigationButton button = new NavigationButton();
            button.setSizeUndefined();
            button.setDescription(book.getName());
            grid.addComponent(button);

            final Book finalBook = book;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    bookView.setBook(finalBook);
                    getNavManager().navigateTo(bookView);
                }
            });
        }
    }
}
