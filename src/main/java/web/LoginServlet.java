package web;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
        out.println("<label>Enter password: <input type='password' name='password'></input></label>");
        out.println("<input type='submit'>Sign In</input>");
        out.println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userid");
        String password = req.getParameter("password");
        if (userId != null && password != null) {

            /** This is what makes it a "valid" login */
            req.getSession().setAttribute(
                    "userid", userId
            );

            resp.sendRedirect("laptop");
        }
        this.doGet(req, resp);
    }
}
