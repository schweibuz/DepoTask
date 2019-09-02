package servlets;

import net.depo.dao.MainDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by andmat on 2019-08-25.
 */
@WebServlet (name = "AddParkServlet")
public class AddParkServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String park_number = req.getParameter("park_number");
        String park_type = req.getParameter("park_type");
        MainDAO.addToPark(park_number, park_type);

        resp.sendRedirect("HomeServlet");
    }
}
