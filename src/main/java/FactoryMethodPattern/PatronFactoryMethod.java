package FactoryMethodPattern;

import DAO.DAOArticulos;
import Entities.Articulo;

import java.util.List;

public class PatronFactoryMethod {
    public static void main(String[] args) {
        DAOArticulos daoArticulos = new DAOArticulos();

        List<Articulo> art = daoArticulos.findAllProducts();
        long totalregistros=art.size()+1;
        //Creamos los nuevos productos a registrar
        Articulo elementoA = new Articulo(totalregistros, "Articulo A"+totalregistros, 120);
        totalregistros=totalregistros+1;
        Articulo elementoB = new Articulo(totalregistros, "Articulo B"+totalregistros, 130);
        // Product productA = new Product(1L, "Product A", 120);
        //Product productB = new Product(2L, "Product B", 130);

        //Product persist
        daoArticulos.saveProduct(elementoA);
        daoArticulos.saveProduct(elementoB);

        //Create the products
        List<Articulo> elementos = daoArticulos.findAllProducts();
        System.out.println("Cantidad Articulos ==> " + elementos.size());
        for(Articulo registro : elementos){
            System.out.println(registro);
        }
    }
}
