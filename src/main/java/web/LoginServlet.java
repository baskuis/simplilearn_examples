package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<form action='' method='POST'>");
        out.println("<label>Enter userid: <input type='text' name='userid'></input></label>");
        out.println("<input type='submit'>Sign In</input>");
        out.println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userid");
        if (userId != null) {
            resp.sendRedirect("desserts");
        }
        this.doGet(req, resp);
    }
}
