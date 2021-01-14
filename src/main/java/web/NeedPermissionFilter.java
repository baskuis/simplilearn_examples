package web;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NeedPermissionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            /**
             * Checking the session for a userid -
             * to see if the valid password was provided
             **/
            Object userId = ((HttpServletRequest) request)
                    .getSession().getAttribute("userid");
            if (userId != null) {
                System.out.println("Valid login, continuing");
                chain.doFilter(request, response);
            } else {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                System.out.println("Redirecting to login, not logged in");
                httpResponse.sendRedirect("login");
            }
        }
    }

}
