package web;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MyGenericServlet extends GenericServlet {

    public void init() {
        System.out.println("Going to be totally vanilla");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.getWriter().println("my vanilla response");
    }

    public void destroy() {
        System.out.println("No longer totally vanilla");
    }

}
