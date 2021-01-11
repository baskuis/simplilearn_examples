package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Setting up the content type of web page
        resp.setContentType("text/html");
        // Writing the message on the web page
        PrintWriter out = resp.getWriter();
        out.println("<h1>Hello World</h1>");
    }

}
