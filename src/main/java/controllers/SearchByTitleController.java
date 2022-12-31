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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchByTitleController")
public class SearchByTitleController extends HttpServlet {

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
        List<Producto> listaConsulta = new ArrayList<>();

        String titulo = req.getParameter("titulo");

        try {
            listaConsulta = dao.getByTitle(titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
        ctx.setVariable("productos", listaConsulta);


        TemplateEngine engine = primerThymeleaf.getTemplateEngine();
        engine.process("listado", ctx, resp.getWriter());


    }
}
