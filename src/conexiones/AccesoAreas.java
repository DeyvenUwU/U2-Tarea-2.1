package conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objetos.Area;

public class AccesoAreas {
    
    private Connection conexion;
    private PreparedStatement consulta;
    private ResultSet resultados;
    
    public AccesoAreas() {
        
    }
    
    public ArrayList<Area> getTodasLasAreas() {
        ArrayList<Area> areas = new ArrayList<>();
        
        String select = "select * from AREAS";
        try {
            conexion = new ConexionDB().getConexion();
            consulta = conexion.prepareStatement(select);
            resultados = consulta.executeQuery();
            
            while (resultados.next()) {
                Area a = new Area();
                a.setId(resultados.getInt("id"));
                a.setNombre(resultados.getString("nombre"));
                a.setUbicacion(resultados.getString("ubicacion"));

                areas.add(a);
            }
            
        } catch (SQLException ex) {
            System.out.println("ERROR accesoInventarios: Fallo 'getTodosLosInventarios'");
        } finally {
            try {
                if (resultados != null) resultados.close();
                if (consulta != null) consulta.close();
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                System.out.println("ERROR al cerrar la conexión: " + ex);}
        }
        
        return areas;
    }
    
    public boolean guardarInventario(Area area) {
        boolean ok = false;
        
        String insert = "insert into AREAS(nombre, ubicacion) values (?, ?)";
        String update = "update AREAS set nombre = ?, ubicacion = ? where id = ?";
        try {
            conexion = new ConexionDB().getConexion();
            conexion.setAutoCommit(false);
            
            if (area.getId() == 0) 
                consulta = conexion.prepareStatement(insert);
            else {
                consulta = conexion.prepareStatement(update);
                consulta.setInt(3, area.getId());
            }
            ArrayList<String> datos = area.getListaAtributos();
            for (int i=1; i<=datos.size(); i++)
                consulta.setString(i, datos.get(i-1));
            
            if (consulta.executeUpdate() > 0)
                ok = true;

            conexion.commit();
        } catch (SQLException e) {
            System.out.println("ERROR accesoAreas: Falló 'guardarArea'");
            try {
                if (conexion != null)
                    conexion.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println("ERROR en el rollback: " + rollbackEx);}
        } finally {
            try {
                conexion.setAutoCommit(true);
                if (consulta != null) consulta.close();
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                System.out.println("ERROR al cerrar la conexión: " + ex);}
        }
        
        return ok;
    }
    
    public boolean eliminarArea(int id) {
        boolean ok = false;
        
        String delete = "delete from AREAS where id = ?";
        try {
            conexion = new ConexionDB().getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement(delete);
            consulta.setInt(1, id);
                
            if (consulta.executeUpdate() > 0)
                ok = true;
            
            conexion.commit();
        } catch (SQLException e) {
            System.out.println("ERROR accesoAreas: Fallo 'eliminarArea");
            try {
                if (conexion != null)
                    conexion.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println("ERROR en el rollback: " + rollbackEx);}
        } finally {
            try {
                conexion.setAutoCommit(true);
                if (consulta != null) consulta.close();
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                System.out.println("ERROR al cerrar la conexión: " + ex);}
        }
        
        return ok;
    }
}
