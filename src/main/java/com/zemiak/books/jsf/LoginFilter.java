package com.zemiak.books.jsf;

import com.zemiak.books.service.UserManager;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@WebFilter("/phone/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //pass
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        UserManager userManager = (UserManager) req.getSession().getAttribute("userManager");

        if (userManager == null || !userManager.isLoggedIn()) {
            res.sendRedirect(req.getContextPath() + "/login.jsf");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        //pass
    }
    
}
