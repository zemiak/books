package com.zemiak.books.domain.dto;

import java.net.URL;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class WebPage {
    private String name;
    private URL url;
    private int id;

    private String authorUrl;

    public WebPage() {
    }

    public WebPage(com.zemiak.books.domain.WebPage webPage, String baseUrl) {
        id = webPage.getId();
        name = webPage.getName();
        url = webPage.getUrl();

        authorUrl = baseUrl + "/authors/" + webPage.getAuthor().getId();
    }

    @Override
    public String toString() {
        return "WebPageDTO{" + "name=" + name + ", url=" + url + ", id=" + id + ", authorUrl=" + authorUrl + '}';
    }
    
    
}
