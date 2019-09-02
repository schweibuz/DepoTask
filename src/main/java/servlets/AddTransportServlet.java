package servlets;

import net.depo.dao.MainDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by andmat on 2019-08-23.
 */

@WebServlet(name = "AddTransportServlet")
public class AddTransportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String number = req.getParameter("number");
        String kind = req.getParameter("kind");
        String park_number = req.getParameter("park");
        String capacity = req.getParameter("capacity");
        String tonnage = req.getParameter("tonnage");
        String transport_type = req.getParameter("type");
        String service = req.getParameter("service");
        String width = req.getParameter("width");
        String length = req.getParameter("length");

        MainDAO.addToTransport(number, kind, park_number);
        MainDAO.addToDetails(capacity);
        MainDAO.addToLocation(width, length);

        String [] types = new String[] {transport_type, service};
        MainDAO.addToTypes(types);

        resp.sendRedirect("HomeServlet");

    }
}
