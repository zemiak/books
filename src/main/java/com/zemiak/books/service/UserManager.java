package com.zemiak.books.service;

import com.zemiak.books.boundary.Users;
import com.zemiak.books.domain.User;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserManager {
    private User current;
    
    @Inject
    private Users users;

    public boolean isLoggedIn() {
        return current != null;
    }

    public User find(String username, String password) {
        current = users.find(username, password);
        
        return current;
    }
}
