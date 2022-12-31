package controllers;


import config.ConfigThymeleaf;
import dao.IProductoDAO;
import dao.impl.ProductoDAOMysqlImpl;
import domain.Producto;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


@WebServlet("/FindAllProductController")

public class FindAllProductController extends HttpServlet {

    private ConfigThymeleaf primerThymeleaf;
    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        primerThymeleaf = new ConfigThymeleaf(servletContext);
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IProductoDAO dao = new ProductoDAOMysqlImpl();
        ArrayList<Producto> lista = new ArrayList<>();
        String nuevo = resp.getHeader("nuevo");
        String borro = resp.getHeader("borro");
        String modifico = resp.getHeader("modifico");






        try{
            lista = dao.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        //req.setAttribute("productos", lista);

        //getServletContext().getRequestDispatcher("/listado.jsp").forward(req, resp);

        /*RequestDispatcher rd = req.getRequestDispatcher(("/nuevo.html"));
        rd.forward(req,resp);*/



        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
        ctx.setVariable("productos", lista);
        if (nuevo != null){
            ctx.setVariable("newRegistro", "si");
        }
        if(borro != null){
            ctx.setVariable("delRegistro", "si");
        }
        if(modifico !=null){
            ctx.setVariable("upRegistro", "si");
        }


        TemplateEngine engine = primerThymeleaf.getTemplateEngine();
        engine.process("listado", ctx, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
