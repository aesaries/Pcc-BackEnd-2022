package view.impl;

import controllers.GetProductoController;
import view.IView;
import domain.Producto;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ViewImpl implements IView {
    Scanner sc = new Scanner(System.in);
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    GetProductoController pc = new GetProductoController();


    @Override
    public int menuPrincipal() {

        String eleccion;

        int num;


        System.out.println("Elija una opcion");
        System.out.println("------------------------");
        System.out.println("1 - Crear Producto");
        System.out.println("2 - Buscar un Producto");
        System.out.println("3 - Ver Todos los Productos");
        System.out.println("4 - Borrar un Producto");
        System.out.println("5 - Modificar un Producto");
        System.out.println("6 - Salir");
        System.out.println(" ");
        System.out.print("Su Opcion: ");
        eleccion = sc.nextLine();

        num = validaEntero(eleccion);

        if (num < 1 || num > 6) {
            System.out.println("No elegiste una opcion valida !!");
            return 0;
        } else {
            if (num != 6) return num;
        }

        return 5;
    }

    @Override
    public void crearProducto() throws Exception {
        String newCodigo;
        String newTitulo;
        Double newPrecio;
        String newFechaAux;
        String newAutor;
        String newImg;
        Date newFecha = null;


        System.out.println("Crear Nuevo Producto Ingrese Los Datos: ");
        System.out.print("Codigo: ");
        newCodigo = sc.nextLine();
        System.out.print("Titulo: ");
        newTitulo = sc.nextLine();
        System.out.print("Fecha: ");
        newFechaAux = sc.nextLine();
        System.out.print("Autor: ");
        newAutor = sc.nextLine();
        System.out.print("URL Imagen: ");
        newImg = sc.nextLine();

        System.out.print("Precio: ");
        newPrecio = sc.nextDouble();

        sc.nextLine();

        try {
            newFecha = new Date(formato.parse(newFechaAux).getTime());
        }catch (Exception e){
            System.out.println("Error al convertir fecha");
        }



        Producto nuevoProducto = new Producto(newCodigo, newTitulo, newPrecio,newFecha, newAutor, newImg);

        if (pc.creaUnProductoNuevo(nuevoProducto)){
            System.out.println("Producto creado con Exito !!!");
        }else{
            System.out.println("Ocurrio un error al crear el producto");
        }



    }

    @Override
    public void verListaProductos() throws Exception {
        System.out.println(" " );
        System.out.println("---------- Lista de todos los productos---------------");
        pc.encuentraTodos();


    }


    @Override
    public void verProductoxID() throws Exception {
        Long idProducto;



        System.out.println("-------Busqueda de Producto por ID----------");
        System.out.println(" ");
        System.out.print("Ingrese del id a buscar : ");
        idProducto = sc.nextLong();
        sc.nextLine();
        Producto encontrado = pc.encuentraxId(idProducto);
        if(encontrado != null){
            System.out.println(encontrado.toString());
        }else{
            System.out.println("No se encuentra el producto buscado");
        }

        System.out.println("------------------------------------------");
        System.out.println(" ");
        }



    @Override
    public void borrarProductoxID() throws Exception {

        long id;
        Producto borrado;

        System.out.println(" ");
        System.out.println("----------Borrar un Producto por ID--------------");
        System.out.println(" ");
        System.out.print("Indique ID del Producto: ");
        id = sc.nextLong();
        sc.nextLine();
        borrado = pc.encuentraxId(id);
        if(borrado!= null){
            if(pc.borraxId(borrado.getId())){
                System.out.println("producto Borrado con exito");
            }else{
                System.out.println("Ocurrio un problema al intentar borrar");
            }
        }else{
            System.out.println("NO se encuentra el producto que busca");
        }



    }

    @Override
    public void modificarProducto() throws Exception {
        long id;
        Producto modificado;

        System.out.println(" ");
        System.out.println("----------Modificar un producto un Producto por ID--------------");
        System.out.println(" ");
        System.out.print("Indique ID del Producto: ");
        id = sc.nextLong();
        sc.nextLine();
        modificado = pc.encuentraxId(id);
        if(modificado!= null) {
            System.out.print("Producto ID " + modificado.getId() + " Titulo : " + modificado.getTitulo());
            System.out.print("Ingrese nueva fecha: ");
            String fechaNueva = sc.nextLine();
            System.out.print("Ingrese nueva Imagen: ");
            String nuevaImagen = sc.nextLine();
            System.out.print("Ingrese nuevo Precio: ");
            Double nuevoPrecio = sc.nextDouble();
            sc.nextLine();

            Date fechaModif = null;
            try {
                fechaModif = new Date(formato.parse(fechaNueva).getTime());
            } catch (Exception e) {
                System.out.println("Error al convertir fecha");
            }
            modificado.setFechaAlta(fechaModif);
            modificado.setImg(nuevaImagen);
            modificado.setPrecio(nuevoPrecio);


            if (pc.actualizaProducto(modificado)) {
                System.out.println("Se han modificado los campos con EXITO !!!");
            }else{
                System.out.println("No se pudo Actualizar");
            }

        }else{
            System.out.println("NO se encuentra el producto que busca");
        }
    }


    public static int validaEntero(String seraNumero) {
        int numero;
        try {
            numero = Integer.parseInt(seraNumero);
        } catch (Exception e) {
            return 0;
        }
        return numero;
    }

}
