package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.cdi.CDIView;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Link;
import com.zemiak.books.client.domain.Book;
import java.io.File;

@CDIView("bookdetailTablet")
class BookDetail extends ViewAbstract {
    static class BookFileResource extends FileResource {
        private String mimeType;
        
        public BookFileResource(File sourceFile, String mimeType) {
            super(sourceFile);
            
            this.mimeType = mimeType;
        }
        
        @Override
        public String getMIMEType() {
            return mimeType;
        }
    }
    
    CssLayout content = null;
    Book book;

    public BookDetail() {
    }
    
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        setCaption(book.getName());
        
        refresh();
    }
    
    public void setBook(Book book) {
        this.book = book;
    }

    private void refresh() {
        content = new CssLayout();
        setContent(content);

        VerticalComponentGroup group1 = new VerticalComponentGroup();

        if (book.getMobiFileName() != null) {
            Link button = new Link();
            button.setCaption("Kindle Format");
            group1.addComponent(button);
            
            FileDownloader fileDownloader = new FileDownloader(new BookFileResource(new File(book.getMobiFileName()), "application/x-mobipocket-ebook"));
            fileDownloader.extend(button);
        }

        if (book.getEpubFileName() != null) {
            Link button = new Link();
            button.setCaption("iBooks Format");
            group1.addComponent(button);

            FileDownloader fileDownloader = new FileDownloader(new BookFileResource(new File(book.getEpubFileName()), "application/epub+zip"));
            fileDownloader.extend(button);
        }

        content.addComponent(group1);
    }
}
