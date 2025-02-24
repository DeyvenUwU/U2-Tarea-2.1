package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    
    private static final String URL = "jdbc:mysql://20.64.236.176:3306/itsur";
    private static final String USER = "David";
    private static final String PASSWORD = "Pokem@ster101";

    private Connection conexion;
    
    public ConexionDB () {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (SQLException ex) {
            System.out.println("¡ERROR! No se pudo conectar con la base de datos");
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}
