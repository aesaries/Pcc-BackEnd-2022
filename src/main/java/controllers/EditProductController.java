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
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/EditProductController")
public class EditProductController extends HttpServlet {
    IProductoDAO dao = new ProductoDAOMysqlImpl();

    //guardar los datos
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //carpturar los parametros que se van a actualizar
        Long id = Long.parseLong(req.getParameter("id")) ;
        String codigo = req.getParameter("codigo");
        String titulo = req.getParameter("titulo");
        Double precio = Double.parseDouble(req.getParameter("precio"));
        String fecha = req.getParameter("fecha");
        String autor = req.getParameter("autor");
        String img = req.getParameter("imagen");

        Date newFecha = null;

        try {
            newFecha = Date.valueOf(fecha);
        }catch (Exception e){
            System.out.println("Error al convertir fecha");
        }

        try {
            dao.update(new Producto(id, codigo, titulo, precio, newFecha,autor, img));


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el registro");

        }
        resp.setHeader("modifico", "si");
        getServletContext().getRequestDispatcher("/FindAllProductController").forward(req, resp);
    }





    //cargar el producto y enviarlo a la jsp
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        //validaciones!!!

        Producto p = null;
        //cargo los datos
        try {
            p = dao.getById(Long.parseLong(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //guardar el producto en request y pasar dicho producto a la jsp
        req.setAttribute("producto", p);

        //redirect
        //irA("/editar.jsp", req, resp);

        RequestDispatcher rd = req.getRequestDispatcher(("/modificarProducto.jsp"));
        rd.forward(req,resp);
    }

}
