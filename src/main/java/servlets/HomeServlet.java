package servlets;

import net.depo.dao.DbAttributeDAO;
import net.depo.dao.MainDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by andmat on 2019-08-18.
 */
@WebServlet(name = "HomeServlet")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            req.setAttribute("transport", MainDAO.getAllTransport());
            req.setAttribute("kind", DbAttributeDAO.getKinds());
            req.setAttribute("park", DbAttributeDAO.getParks());
            req.getRequestDispatcher("/jsp/HomePage.jsp").forward(req, resp);
        } catch (Throwable theException) {
            System.out.println(theException);
        }
    }
}
