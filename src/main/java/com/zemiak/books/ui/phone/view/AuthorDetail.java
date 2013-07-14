package com.zemiak.books.ui.phone.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.cdi.CDIView;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Link;
import com.zemiak.books.client.boundary.Collection;
import com.zemiak.books.client.domain.Author;
import com.zemiak.books.client.domain.Book;
import com.zemiak.books.client.domain.Tag;
import com.zemiak.books.client.domain.WebPage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@CDIView("authordetail")
class AuthorDetail extends ViewAbstract {
    CssLayout content = null;
    
    @Inject
    Collection col;
    
    Author author;
    List<Book> books;
    List<String> tags;

    public AuthorDetail() {
    }
    
    @PostConstruct
    public void init() {
    }
    
    public void setAuthor(Author author) {
        this.author = author;
        readData();
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        refresh();
    }

    private void refresh() {
        content = new CssLayout();
        setContent(content);
        
        setCaption(author.getName());

        if (author.getWebPages() != null && !author.getWebPages().isEmpty()) {
            VerticalComponentGroup group2 = new VerticalComponentGroup("Links");

            for (WebPage page: author.getWebPages()) {
                Link link = new Link(page.getName(), new ExternalResource(page.getUrl().toString()));
                group2.addComponent(link);
            }

            content.addComponents(group2);
        }

        VerticalComponentGroup group1 = new VerticalComponentGroup("Books");

        for (Book book: books) {
            NavigationButton button = new NavigationButton(book.getName());
            group1.addComponent(button);

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

        content.addComponents(group1);
        
        if (null != tags && !tags.isEmpty()) {
            VerticalComponentGroup group = new VerticalComponentGroup("Tags");

            for (String tag: tags) {
                NavigationButton button = new NavigationButton(tag);
                group.addComponent(button);

                final String finalTag = tag;

                button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                    @Override
                    public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                        TagDetail view = (TagDetail) getNavManager().getView("tagdetail");
                        view.setTag(finalTag);
                        getNavManager().navigateTo(view);
                    }
                });
            }

            content.addComponents(group);
        }
    }

    private void readData() {
        tags = new ArrayList<>();

        for (Tag tag: author.getTags()) {
            tags.add(tag.getName());
        }
        
        Collections.sort(tags);

        books = author.getBooks();
    }
}
