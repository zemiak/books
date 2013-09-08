package com.zemiak.books.ui.phone.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Link;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Book;
import com.zemiak.books.domain.WebPage;
import java.util.List;

class AuthorDetail extends ViewAbstract {
    CssLayout content = null;
    
    Author author;
    List<Book> books;
    List<String> tags;

    public AuthorDetail(Author author) {
        this.author = author;
        readData();
    }
    
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        setCaption(author.getName());
        
        refresh();
    }

    private void refresh() {
        content = new CssLayout();
        setContent(content);
        
        renderWebPages();
        renderBooks();
        renderTags();
    }

    private void readData() {
        tags = author.getTags();
        books = author.getBooks();
    }

    private void renderWebPages() {
        if (author.getWebPages() != null && !author.getWebPages().isEmpty()) {
            VerticalComponentGroup group2 = new VerticalComponentGroup("Links");

            for (WebPage page: author.getWebPages()) {
                Link link = new Link(page.getName(), new ExternalResource(page.getUrl().toString()));
                group2.addComponent(link);
            }

            content.addComponents(group2);
        }
    }

    private void renderBooks() {
        VerticalComponentGroup group1 = new VerticalComponentGroup("Books");

        for (Book book: books) {
            NavigationButton button = new NavigationButton(book.getName());
            group1.addComponent(button);

            final Book finalBook = book;

            button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                @Override
                public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                    BookDetail view = new BookDetail(finalBook);
                    getNavManager().navigateTo(view);
                }
            });
        }

        content.addComponents(group1);
    }

    private void renderTags() {
        if (null != tags && !tags.isEmpty()) {
            VerticalComponentGroup group = new VerticalComponentGroup("Tags");

            for (String tag: tags) {
                NavigationButton button = new NavigationButton(tag);
                group.addComponent(button);

                final String finalTag = tag;

                button.addClickListener(new NavigationButton.NavigationButtonClickListener() {
                    @Override
                    public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                        TagDetail view = new TagDetail(finalTag);
                        getNavManager().navigateTo(view);
                    }
                });
            }

            content.addComponents(group);
        }
    }
}
