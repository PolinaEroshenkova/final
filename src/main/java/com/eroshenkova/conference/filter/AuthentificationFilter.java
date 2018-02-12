package com.eroshenkova.conference.filter;

import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthentificationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        String accountType = (String) session.getAttribute(Parameter.TYPE);
        String nextPage = request.getRequestURI();
        if ((accountType == null && (UrlManager.getProperty(Page.CONFERENCE).equals(nextPage) || //TODO
                UrlManager.getProperty(Page.INDEX).equals(nextPage) ||
                UrlManager.getProperty(Page.FAQ).equals(nextPage) ||
                UrlManager.getProperty(Page.REGISTRATION).equals(nextPage))) ||
                (accountType != null && accountType.equals("user") &&
                        !UrlManager.getProperty(Page.MANAGEMENT).equals(nextPage)) ||
                (accountType != null && accountType.equals("admin"))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(Page.JSP_ERROR);
            dispatcher.forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
