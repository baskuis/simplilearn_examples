package widgetstore.web;

import desserts.DrinkDAOImpl;
import desserts.DrinkDTO;
import desserts.GenericDAO;
import hibernate.HibernateUtils;
import org.hibernate.Session;
import robot.RobotEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RobotDetailsServlet extends HttpServlet {

    GenericDAO<DrinkDTO> drinkDAO;

    Session session;

    public RobotDetailsServlet() {
        drinkDAO = new DrinkDAOImpl();
    }

    public void init() {
        session = HibernateUtils.buildSessionFactory().openSession();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<form action='' method='POST'>");
        out.println("<label>Enter Product (Robot) ID: " +
                "<input type='text' name='robot-id'></input></label>");
        out.println("<input type='submit'>Get Details</input>");
        out.println("</form>");
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String robotId = request.getParameter("robot-id");
        PrintWriter out = response.getWriter();

        RobotEntity robot = session.get(
                RobotEntity.class,
                Long.parseLong(robotId)
        );
        if (robot != null) {
            out.println("Found robot: " + robot.describeRobot());
        } else {
            out.println("No laptop found for id: " + robotId);
        }
    }

}
