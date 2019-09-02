package servlets;

import net.depo.dao.MainDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by andmat on 2019-08-19.
 */
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String current_park_number = req.getParameter("current_park_number");
        String edited_park_type = req.getParameter("edited_park_type");
        String edited_park_number = req.getParameter("edited_park_number");

        MainDAO.updatePark(current_park_number, edited_park_type, edited_park_number);
        resp.sendRedirect("HomeServlet");

    }

}
