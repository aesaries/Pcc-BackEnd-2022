package controllers;

import dao.IProductoDAO;
import dao.impl.ProductoDAOMysqlImpl;
import domain.Producto;

import java.util.ArrayList;

public class GetProductoController  {
    IProductoDAO dao = new ProductoDAOMysqlImpl();

    public  Producto  encuentraxId(Long id) throws Exception {

        return dao.getById(id);
    }

    public  void encuentraTodos() throws Exception {

        ArrayList<Producto> lista = new ArrayList<>();
        lista = dao.findAll();
        if (lista !=null) {
            for (Producto p : lista) {
                System.out.println(p);
            }
        }else {
            System.out.println("lista vacia");
        }
    }

    public  boolean borraxId(Long id) throws Exception {

       return dao.delete(id);

    }

    public boolean creaUnProductoNuevo(Producto nuevoProducto) throws Exception {

        return dao.create(nuevoProducto);

    }

    public boolean actualizaProducto(Producto productoModificado) throws Exception {

       return dao.update(productoModificado);

    }

}

