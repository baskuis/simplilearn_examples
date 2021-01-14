package widgetstore.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoggingFilter implements Filter {

    String prefix;

    public void  init(FilterConfig config) throws ServletException {
        prefix = config.getInitParameter("prefix");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        System.out.println("Received request " + prefix + " " + httpServletRequest.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
