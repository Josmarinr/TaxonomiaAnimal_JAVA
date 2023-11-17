
package Control.Conexion;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
//establece una conexion con la base de datos 
public class Conexion {
    private static Connection cn = null;
    private static Driver driver = new org.apache.derby.jdbc.ClientDriver();
    private static String URLBD = "jdbc:derby://localhost:1527/TAXONOMIAANIMAL";
    private static String usuario = "taxonomia";
    private static String contrasena = "taxonomia"; 
    
    public static Connection getConexion() throws SQLException {
        DriverManager.registerDriver(driver);
        cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        return cn;
    }
    public static void desconectar(){
      cn = null;
   }
}