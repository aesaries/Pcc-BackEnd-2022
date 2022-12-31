package controllers;

import dao.IProductoDAO;
import dao.impl.ProductoDAOMysqlImpl;
import domain.Producto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/DeleteProductController")
public class DeleteProductController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IProductoDAO dao = new ProductoDAOMysqlImpl();
        Long id = Long.valueOf(req.getParameter("idBorrar"));
        //ArrayList<Producto> lista = new ArrayList<>();

        try {
            dao.delete(id);
            //lista = dao.findAll();
            //getServletContext().getRequestDispatcher("/exito.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
            //getServletContext().getRequestDispatcher("/falla.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            //getServletContext().getRequestDispatcher("/falla.jsp").forward(req, resp);
        }
        resp.setHeader("borro","si");
        RequestDispatcher rd = req.getRequestDispatcher(("/FindAllProductController"));
        rd.forward(req,resp);



    }
}
