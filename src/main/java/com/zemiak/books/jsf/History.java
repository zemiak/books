package com.zemiak.books.jsf;

import java.util.ArrayDeque;
import java.util.Deque;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.SessionScoped;

@SessionScoped
public class History {
    final static private String HOME = "#main?reverse=true";
    
    private Deque<String> history = new ArrayDeque<>();
    
    public void setLastItem(String item) {
        System.err.println("Adding history " + item);
        
        history.add("#" + item + "?reverse=true");
    }
    
    public String getLastItem() {
        if (! history.isEmpty()) {
            String last = history.pop();
            System.err.println("Returning last item " + last);
            return last;
        }
        
        System.err.println("Empty history, returning " + HOME);
        
        return HOME;
    }
    
    public String getHome() {
        history.clear();
        
        System.err.println("History cleared, returning " + HOME);
        
        return HOME;
    }
    
    @PostConstruct
    void init() {
        System.err.println("History initialized");
    }
    
    @PreDestroy
    void destruct() {
        System.err.println("History destroyed");
    }
}
