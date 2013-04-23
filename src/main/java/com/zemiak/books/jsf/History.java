package com.zemiak.books.jsf;

import java.util.ArrayDeque;
import java.util.Deque;
import javax.faces.bean.SessionScoped;

@SessionScoped
public class History {
    final static private String HOME = "#letter?reverse=true";
    
    private Deque<String> history = new ArrayDeque<>();
    
    public void setLastItem(String item) {
        history.add("#" + item + "?reverse=true");
    }
    
    public String getLastItem() {
        if (! history.isEmpty()) {
            return history.pop();
        }
        
        return HOME;
    }
    
    public String getHome() {
        history.clear();
        
        return HOME;
    }
}
