package filter;

import javax.servlet.*;
import java.io.IOException;

public class StaticFilter implements Filter {
    private RequestDispatcher dispatcher;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        dispatcher = filterConfig.getServletContext().getNamedDispatcher("default");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        dispatcher.forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
