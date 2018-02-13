package com.eroshenkova.conference.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Doesn't allow access static files to servlet
 *
 * @author Palina Yerashenkava
 * @see Filter
 */
public class StaticFilter implements Filter {

    /**
     * @param filterConfig is filter configuration
     * @throws ServletException if initialization is ended correctly
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * @param servletRequest is request from page
     * @param servletResponse is response from page
     * @param filterChain is filter chain
     * @throws IOException if I/O error occurred
     * @throws ServletException if session is not defined correctly
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();
        if (path.startsWith("/static")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * Is called when filter ended his work
     */
    @Override
    public void destroy() {
    }
}
