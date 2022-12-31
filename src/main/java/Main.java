import view.IView;
import view.impl.ViewImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        int menu = 0;
        IView vista = new ViewImpl();
        do {
            menu = vista.menuPrincipal();
            if (menu == 1) vista.crearProducto();
            if (menu == 2) vista.verProductoxID();
            if (menu == 3) vista.verListaProductos();
            if (menu == 4) vista.borrarProductoxID();
            if (menu == 5) vista.modificarProducto();


        }while(menu != 6);
        System.out.println("Gracias por usar la aplicacion");



    }

}

