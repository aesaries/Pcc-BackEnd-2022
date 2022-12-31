package dao;

import domain.Producto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IProductoDAO {
    /*define los metodos de acceso a la tabla PRODUCTOS*/
    /*CRUD*/
    Producto getById(Long id) throws Exception;
    //select * from producto where id = id



    List<Producto> getByTitle(String title) throws Exception;

    public ArrayList<Producto> findAll() throws Exception;
    //select * from producto

    public boolean delete(Long id) throws SQLException;
    //delete from producto where id = id

    boolean update(Producto producto) throws SQLException;
    //update producto
    //set campo1 = valor1.....
    //where id = producto.id

    boolean create(Producto newProduct) throws SQLException;
    //insert into producto (campo1, ....m campoN)
    //values(newProducto.campo1, ...newProducto.campoN)

}


