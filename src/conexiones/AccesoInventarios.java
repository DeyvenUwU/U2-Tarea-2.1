package conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objetos.Inventario;

public class AccesoInventarios {
    
    private Connection conexion;
    private PreparedStatement consulta;
    private ResultSet resultados;
    
    public AccesoInventarios() {
        
    }
    
    public ArrayList<Inventario> getTodosLosInventarios() {
        ArrayList<Inventario> inventarios = new ArrayList<>();
        
        String select = "select * from INVENTARIOS";
        try {
            conexion = new ConexionDB().getConexion();
            consulta = conexion.prepareStatement(select);
            resultados = consulta.executeQuery();
                
            while (resultados.next()) {
                Inventario i = new Inventario();
                i.setId(resultados.getInt("id"));
                i.setNombreCorto(resultados.getString("nombreCorto"));
                i.setDescripcion(resultados.getString("descripcion"));
                i.setSerie(resultados.getString("serie"));
                i.setColor(resultados.getString("color"));
                i.setFechaAdq(resultados.getDate("fechaAdq"));
                i.setTipoAdq(resultados.getString("tipoAdq"));
                i.setObservaciones(resultados.getString("observaciones"));
                i.setIdArea(resultados.getInt("idArea"));

                inventarios.add(i);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR accesoInventarios: Falló 'getTodosLosInventarios'");
        } finally {
            try {
                if (resultados != null) resultados.close();
                if (consulta != null) consulta.close();
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                System.out.println("ERROR al cerrar la conexión: " + ex);}
        }
        
        return inventarios;
    }
    
    public boolean guardarInventario(Inventario inventario) {
        boolean ok = false;
        
        String insert = "insert into INVENTARIOS"
                + "(nombreCorto, descripcion, serie, color, fechaAdq, tipoAdq, observaciones, idArea) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?)";
        String update = "update INVENTARIOS set nombreCorto = ?, descripcion = ?, serie = ?, color = ?, "
                + "fechaAdq = ?, tipoAdq = ?, observaciones = ?, idArea = ? where id = ?";
        try {
            conexion = new ConexionDB().getConexion();
            conexion.setAutoCommit(false);
            
            if (inventario.getId() == 0) 
                consulta = conexion.prepareStatement(insert);
            else {
                consulta = conexion.prepareStatement(update);
                consulta.setInt(9, inventario.getId());
            }
            ArrayList<String> datos = inventario.getListaAtributos();
            for (int i=1; i<=datos.size(); i++)
                consulta.setString(i, datos.get(i-1));
            
            if (consulta.executeUpdate() > 0)
                ok = true;

            conexion.commit();
        } catch (SQLException e) {
            System.out.println("ERROR accesoInventarios: Falló 'guardarInventario'");
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
    
    public boolean eliminarInventario(int id) {
        boolean ok = false;
        
        String delete = "delete from INVENTARIOS where id = ?";
        try {
            conexion = new ConexionDB().getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement(delete);
            consulta.setInt(1, id);
                
            if (consulta.executeUpdate() > 0)
                ok = true;
            
            conexion.commit();
        } catch (SQLException e) {
            System.out.println("ERROR accesoInventarios: Fallo 'eliminarInventario");
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
