package DAO;

import Entities.Articulo;
import FactoryMethodPattern.FactoryBaseDatos;
import FactoryMethodPattern.IntAdaptador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOArticulos {
    private IntAdaptador intAdaptador;

    public DAOArticulos() {
        this.intAdaptador = FactoryBaseDatos.getDefaultDBAdapter();
    }

    public List<Articulo> findAllProducts(){
        Connection connection = this.intAdaptador.getConnection();
        List<Articulo> productList = new ArrayList<>();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT idarticulo ,nombrearticulo"
                            + ",valor FROM articulos");
            ResultSet results = statement.executeQuery();
            while(results.next()){
                productList.add(new Articulo(results.getLong(1),
                        results.getString(2), results.getDouble(3)));
            }
            return productList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                connection.close();
            } catch (Exception e) {}
        }
    }

    public boolean saveProduct(Articulo articulo){
        Connection connection = this.intAdaptador.getConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO articulos(idarticulo,"
                            + "nombrearticulo,valor) VALUES (?,?,?)");
            statement.setLong(1, articulo.getIdarticulo());
            statement.setString(2, articulo.getNombrearticulo());
            statement.setDouble(3, articulo.getValor());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            try {
                connection.close();
            } catch (Exception e) {}
        }
    }
}
