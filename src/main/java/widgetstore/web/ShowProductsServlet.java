package widgetstore.web;

import desserts.DessertDAOImpl;
import desserts.DessertDTO;
import desserts.GenericDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShowProductsServlet extends HttpServlet {

    GenericDAO<DessertDTO> dessertDTO;

    public ShowProductsServlet() {
        dessertDTO = new DessertDAOImpl();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<form action='' method='POST'>");
        out.println("<label>Enter Dessert Name: <input type='text' name='dessert-name'></input></label>");
        out.println("<label>Enter If Good: <input type='radio' name='dessert-good' value='0' />No <input type='radio' name='dessert-good' value='1' />Yes</label>");
        out.println("<input type='submit'>Create Dessert</input>");
        out.println("</form>");

        out.println("<h2>Desserts</h2>");
        for (DessertDTO dessert: dessertDTO.getAll()) {
            out.println("<p>" + dessert.getId() + ": " + dessert.getName() + " It is good:" + dessert.isGood() + "</p>");
        }
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("dessert-name");
        String good = request.getParameter("dessert-good");
        Boolean isGood = Boolean.parseBoolean(good);
        dessertDTO.create(new DessertDTO(
                null,
                name,
                isGood
        ));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Dessert was created");
    }
}
