package com.eroshenkova.conference.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Set's utf-8 encoding to all pages
 *
 * @author Palina Yerashenkava
 * @see Filter
 */
public class EncodingFilter implements Filter {

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
        servletRequest.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Is called when filter ended his work
     */
    @Override
    public void destroy() {
    }
}
