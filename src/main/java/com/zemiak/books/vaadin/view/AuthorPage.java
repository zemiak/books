package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Book;
import com.zemiak.books.domain.Tag;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author vasko
 */
class AuthorPage extends NavigationView implements Component {
    CssLayout content = null;
    List<Tag> tags;
    List<Book> books;

    public AuthorPage(Author author) {
        super(author.getName());
        
        tags = author.getTags();
        books = author.getBooks();
    }
    
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        
        if (null != content) {
            return;
        }
        
        content = new CssLayout();
        setContent(content);

        Collections.sort(tags);
        Collections.sort(books);
        
        VerticalComponentGroup group1 = new VerticalComponentGroup();
        Label label1 = new Label("Books");
        
        for (Book book: books) {
            NavigationButton button = new NavigationButton();
            button.setCaption(book.getName());
            group1.addComponent(button);
            
            final Book finalBook = book;
            
            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    getNavigationManager().navigateTo(new BookPage(finalBook));
                }
            });
        }
        
        content.addComponents(label1, group1);
        
        if (null != tags && !tags.isEmpty()) {
            VerticalComponentGroup group = new VerticalComponentGroup();
            Label label = new Label("Tags");

            for (Tag tag: tags) {
                NavigationButton button = new NavigationButton();
                button.setCaption(tag.getName());
                group.addComponent(button);

                final Tag finalTag = tag;

                button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                    @Override
                    public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                        getNavigationManager().navigateTo(new TagPage(finalTag));
                    }
                });
            }
            
            content.addComponents(label, group);
        }
    }
}
