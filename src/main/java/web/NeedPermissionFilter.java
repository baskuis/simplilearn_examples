package web;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class NeedPermissionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            Cookie cookie = null;
            try {
                if (((HttpServletRequest) request).getCookies() != null) {
                    cookie = Arrays.stream(
                            ((HttpServletRequest) request).getCookies()
                    ).filter((Cookie c) -> c.getName().equals("userid"))
                            .findFirst().get();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Cookie not found");
            }
            if (cookie != null) {
                System.out.println("Ok to continue");
                chain.doFilter(request, response);
            } else {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                System.out.println("Redirecting to login");
                httpResponse.sendRedirect("login");
            }
        }

    }

}
