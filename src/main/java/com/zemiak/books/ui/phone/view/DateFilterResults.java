package com.zemiak.books.ui.phone.view;

import com.vaadin.addon.responsive.Responsive;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.CssLayout;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Book;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DateFilterResults extends ViewAbstract {

    CssLayout content = null;
    
    @Inject
    Collection col;
    
    Date from, to;
    private List<Book> books;

    public DateFilterResults(Date from, Date to) {
        this.from = from;
        this.to = to;

        refreshData();
    }

    private void refreshData() {
        books = col.getBooksByDateInterval(from, to);
    }

    @Override
    protected void onBecomingVisible() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        super.onBecomingVisible();
        setCaption("Dates " + df.format(from) + " - " + df.format(to));

        refresh();
    }

    private void refresh() {
        content = new CssLayout();
        setContent(content);
        
        VerticalComponentGroup group = new VerticalComponentGroup();

        for (Book book : books) {
            NavigationButton button = new NavigationButton();
            button.setSizeUndefined();
            button.setDescription(book.getName());
            group.addComponent(button);

            final Book finalBook = book;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    BookDetail view = new BookDetail(finalBook);
                    getNavManager().navigateTo(view);
                }
            });
        }
        
        content.addComponents(group);
    }
}
