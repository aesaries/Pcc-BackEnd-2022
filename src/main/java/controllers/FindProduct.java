package controllers;

import dao.IProductoDAO;
import dao.impl.ProductoDAOMysqlImpl;
import domain.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FindProduct")

public class FindProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IProductoDAO dao = new ProductoDAOMysqlImpl();
        Producto producto = null;

        Long id = Long.valueOf(req.getParameter("id"));
        try {
            producto = dao.getById(id);
        }catch (Exception e ){
            e.printStackTrace();
        }
        req.setAttribute("producto",producto);
        getServletContext().getRequestDispatcher("/buscaProducto.jsp").forward(req, resp);
    }



}
