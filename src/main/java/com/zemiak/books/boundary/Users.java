package com.zemiak.books.boundary;

import com.zemiak.books.domain.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Users {

    @PersistenceContext
    private EntityManager em;
    private static final Logger LOG = Logger.getLogger(Users.class.getName());

    public User find(String userName, String password) {
        User user = em.find(User.class, userName);

        if (null != user) {
            if (!user.encryptPassword(password).equals(user.getPassword())) {
                user = null;
                LOG.log(Level.SEVERE, "User {0}: bad password", userName);
            }
        } else {
            LOG.log(Level.SEVERE, "User {0} does not exist", userName);
        }

        return user;
    }
}
