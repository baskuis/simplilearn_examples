package widgetstore.web;

import desserts.*;
import ecommerce.LaptopEntity;
import hibernate.HibernateUtils;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LaptopDetailsServlet extends HttpServlet {

    GenericDAO<DrinkDTO> drinkDAO;

    public LaptopDetailsServlet() {
        drinkDAO = new DrinkDAOImpl();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<form action='' method='POST'>");
        out.println("<label>Enter Product (Laptop) ID: <input type='text' name='laptop-id'></input></label>");
        out.println("<input type='submit'>Get Details</input>");
        out.println("</form>");
    }

    public void doPost(HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {
        String laptopId = request.getParameter("laptop-id");
        PrintWriter out = response.getWriter();

        Session session = HibernateUtils.buildSessionFactory().openSession();
        LaptopEntity laptopEntity = session.get(
                LaptopEntity.class,
                Long.parseLong(laptopId)
        );
        if (laptopEntity != null) {
            out.println("Found laptop: " + laptopEntity.getName() + " with price: " + laptopEntity.getPrice());
        } else {
            out.println("No laptop found for id: " + laptopId);
        }
    }

}
