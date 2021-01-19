package widgetstore.web;

import desserts.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DrinkDetailsServlet extends HttpServlet {

    GenericDAO<DrinkDTO> drinkDAO;

    public DrinkDetailsServlet() {
        drinkDAO = new DrinkDAOImpl();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<form action='' method='POST'>");
        out.println("<label>Enter Product (Drink) ID: <input type='text' name='drink-id'></input></label>");
        out.println("<input type='submit'>Get Details</input>");
        out.println("</form>");
    }

    public void doPost(HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {
        String drinkId = request.getParameter("drink-id");
        PrintWriter out = response.getWriter();

        // Use the drinkDAO .. get the drinkDTO
        // show the details
        // or if not found
        // show an error message

        out.println("showing drink details - or an issue if not found");

    }

}
