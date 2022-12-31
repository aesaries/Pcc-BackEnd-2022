package dao.impl;

import dao.IProductoDAO;
import db.AdministradorDeConexiones;
import domain.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductoDAOMysqlImpl implements IProductoDAO {
    private String sql;
    //1 - necesito la Connection
    private Connection connection;
    private PreparedStatement preparedStatement;
    ResultSet resultSet;


    @Override
    public Producto getById(Long id) throws Exception {
        return null;
    }


    @Override
    public List<Producto> getByTitle(String title) throws Exception {

        ArrayList<Producto> listaProductos = new ArrayList<>();

       //2 - arma el statement
        sql = "SELECT * FROM PRODUCTO WHERE UPPER(titulo) LIKE UPPER(?)";
        connection = AdministradorDeConexiones.getConnection();
        assert connection != null;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"%"+title+"%");
        resultSet = preparedStatement.executeQuery();

        //verifico si hay datos
        while(resultSet.next()) {
            //obtengo el dato del campo id
            Long idDb = resultSet.getLong("id");
            String codigo = resultSet.getString("codigo");
            String titulo = resultSet.getString("titulo");
            Double precio = resultSet.getDouble("precio");
            Date fecha = resultSet.getDate("fecha_creacion");
            String autor = resultSet.getString("autor");
            String img = resultSet.getString("img");
            listaProductos.add(new Producto(idDb, codigo, titulo, precio, fecha, autor, img));

        }

            return listaProductos;



    }

    @Override
    public ArrayList<Producto> findAll() throws Exception {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        connection = AdministradorDeConexiones.getConnection();
        sql = "SELECT * FROM PRODUCTO";
        preparedStatement = connection.prepareStatement(sql);
        resultSet= preparedStatement.executeQuery();

        while (resultSet.next()){
            //obtengo el dato del campo id
            Long idDb = resultSet.getLong("id");
            String codigo = resultSet.getString("codigo");
            String titulo = resultSet.getString("titulo");
            Double precio = resultSet.getDouble("precio");
            Date fecha = resultSet.getDate("fecha_creacion");
            String autor = resultSet.getString("autor");
            String img = resultSet.getString("img");

            listaProductos.add(new Producto(idDb, codigo, titulo, precio, fecha, autor, img));
        }
        return listaProductos;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        connection = AdministradorDeConexiones.getConnection();
        sql = "DELETE FROM PRODUCTO WHERE ID = ?";
        assert connection != null;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
       return  preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean update(Producto producto) throws SQLException {
        boolean actualizadoOK = false;

        //2 - arma el statement
        sql = "UPDATE PRODUCTO SET  precio = ?, fecha_creacion = ?, img = ? WHERE id = ?";
        connection = AdministradorDeConexiones.getConnection();
        assert connection != null;
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setDouble(1,producto.getPrecio());
        preparedStatement.setDate(2,producto.getFechaAlta());
        preparedStatement.setString(3,producto.getImg());
        preparedStatement.setLong(4,producto.getId());

        return preparedStatement.executeUpdate()>0;



    }


    @Override
    public boolean create(Producto newProduct) throws SQLException {

        boolean creadoOK = false;

        //2 - arma el statement
        sql = "INSERT INTO PRODUCTO (codigo, titulo, precio, fecha_creacion, autor, img) VALUES (?,?,?,?,?,?)";


        connection = AdministradorDeConexiones.getConnection();
        assert connection != null;
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,newProduct.getCodigo());
        preparedStatement.setString(2,newProduct.getTitulo());
        preparedStatement.setDouble(3,newProduct.getPrecio());
        preparedStatement.setDate(4,newProduct.getFechaAlta());
        preparedStatement.setString(5,newProduct.getAutor());
        preparedStatement.setString(6,newProduct.getImg());

        return preparedStatement.executeUpdate() > 0;



    }


}
