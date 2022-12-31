package controllers;

import config.ConfigThymeleaf;
import dao.IProductoDAO;
import dao.impl.ProductoDAOMysqlImpl;
import domain.Producto;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


@WebServlet("/CreateProductController")
public class CreateProductController extends HttpServlet {

    private ConfigThymeleaf primerThymeleaf;
    private ServletContext servletContext;




    @Override
    public void init(ServletConfig config) {
        servletContext = config.getServletContext();
        primerThymeleaf = new ConfigThymeleaf(servletContext);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IProductoDAO dao = new ProductoDAOMysqlImpl();


        String codigo = req.getParameter("Codigo");
        String titulo = req.getParameter("Titulo");
        String precio2 = req.getParameter("Precio");
        double precio= (double) 0;
        String fecha = req.getParameter("Fecha");
        String autor = req.getParameter("Autor");
        String img = req.getParameter("Imagen");

        Date newFecha = null;
        String mensajeError="";
        if(codigo.isEmpty()|| codigo == null){
            mensajeError += "Codigo ,";
        }
        if(titulo.isEmpty()|| titulo == null){
            mensajeError+="Titulo, ";

        }

        if(precio2.isEmpty()){
            mensajeError+="Precio ,";
        }else {
            precio = Double.parseDouble(precio2);
            if(precio <= 0){
                mensajeError+="Precio ,";
            }
        }


        if(fecha.isEmpty()|| fecha == null){
            mensajeError += "Fecha ,";
        }
        if(autor.isEmpty()|| autor == null){
            mensajeError +="Autor ,";
        }

        boolean sinErrores= mensajeError.isEmpty();

        if(!sinErrores){
            mensajeError +=  " tienen valores no validos, por favor revise";
            WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
            ctx.setVariable("listaErrores", mensajeError);
            ctx.setVariable("errores", "si");

            TemplateEngine engine = primerThymeleaf.getTemplateEngine();
            engine.process("crearNuevo", ctx, resp.getWriter());

            return;

        }

        try {
            newFecha = Date.valueOf(fecha);
        }catch (Exception e){
            System.out.println("Error al convertir fecha");
        }

        try {
            dao.create(new Producto(codigo, titulo, precio, newFecha,autor, img));


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el registro");

        }

        resp.setHeader("nuevo","si");

        servletContext.getRequestDispatcher("/FindAllProductController").forward(req, resp);

        /*WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
        ctx.setVariable("grabo", "si");

        TemplateEngine engine = primerThymeleaf.getTemplateEngine();
        engine.process("listado", ctx, resp.getWriter());*/





    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
        ctx.setVariable("listaErrores", "");

        TemplateEngine engine = primerThymeleaf.getTemplateEngine();
        engine.process("crearNuevo", ctx, resp.getWriter());
    }
}
