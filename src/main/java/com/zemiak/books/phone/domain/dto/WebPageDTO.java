package com.zemiak.books.phone.domain.dto;

import java.net.URL;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class WebPageDTO {
    private String name;
    private URL url;
    private int id;

    private String authorUrl;

    public WebPageDTO() {
    }

    @Override
    public String toString() {
        return "WebPageDTO{" + "name=" + name + ", url=" + url + ", id=" + id + ", authorUrl=" + authorUrl + '}';
    }

    public String getName() {
        return name;
    }

    public URL getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }
}
