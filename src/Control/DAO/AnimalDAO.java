   package Control.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Control.Conexion.*;
import Modelo.Animal;
import java.util.ArrayList;

public class AnimalDAO {

    private Connection con;
    private Statement st;
    private ResultSet rs;
//inicia las variables de conexion con la base de datos

    public AnimalDAO() {
        con = null;
        st = null;
        rs = null;
    }

    //inserta todos los valores del animal a la base de datos 
    public void insertarDatos(Animal animal) {
        try {
            //obtiene la conexion 
            con = Conexion.getConexion();
            //crea el statement respecto a la conexion con la base de datos
            st = con.createStatement();
            String insercion = "INSERT INTO Animales VALUES ('" + animal.getFilum() + "','" + animal.getSubfilum() + "','"
                    + animal.getClase() + "','" + animal.getOrden() + "','" + animal.getFamilia() + "','" + animal.getGenero()
                    + "','" + animal.getEspecie() + "','" + animal.getFoto() + "','" + animal.getSonido() + "','" + animal.getNombre() + "')";
            //sube los datos get a la base de datos 
            st.executeUpdate(insercion);
            //cierra la conexion con la base de datos 
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.print("No se pudo realizar la insercion");
        }
    }

    //modifica los atributos de un animal de la base de datos 
    public void modificarAnimal(Animal animal) {

        String mod = "UPDATE Animales SET filum = '" + animal.getFilum() + "',subfilum = '" + animal.getSubfilum() + "' WHERE nombre='" + animal.getNombre() + "'";

        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            st.executeUpdate(mod);
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la modifcacion");
        }
    }
    //elimina un animal de la base de datos 

    public void eliminarMascota(Animal animal) {
        String consulta = "DELETE FROM animales WHERE nombre='" + animal.getNombre() + "'";
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            st.executeUpdate(consulta);
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la consulta");
        }
    }

}
