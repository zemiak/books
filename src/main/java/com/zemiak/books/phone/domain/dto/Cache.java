package com.zemiak.books.phone.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Cache {
    private String key;
    private String value;
    
    public Cache() {
    }
    
    @Override
    public String toString() {
        return "CacheDTO{" + "key=" + key + ", value=" + value + '}';
    }
}
