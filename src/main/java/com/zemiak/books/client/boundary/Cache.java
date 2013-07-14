package com.zemiak.books.client.boundary;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;

/**
 *
 * @author vasko
 */
@Singleton
public class Cache {
    private Map<String, Object> cache = new HashMap<>();
    
    public Object get(String key) {
        return (containsKey(key)) ? cache.get(key) : null;
    }
    
    public void set(String key, Object value) {
        cache.put(key, value);
    }
    
    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
    
    public void clearEvent(@Observes CacheClearEvent event) {
        cache.clear();
    }
}
