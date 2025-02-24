package objetos;

import java.util.ArrayList;

public class Area {
    
    private int id;
    private String nombre;
    private String ubicacion;
    
    public Area() {
        
    }

    public Area(int id, String nombre, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public ArrayList<String> getListaAtributos () {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(nombre);
        lista.add(ubicacion);
        
        return lista;
    }

    @Override
    public String toString() {
        return "Area{" + "id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + '}';
    }
}
