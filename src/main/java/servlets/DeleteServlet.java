package servlets;

import net.depo.dao.MainDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by andmat on 2019-08-19.
 */

@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int transport_id = Integer.parseInt(req.getParameter("transport_id"));

        try {
            MainDAO.deleteTransport(transport_id);
            resp.sendRedirect("HomeServlet");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
