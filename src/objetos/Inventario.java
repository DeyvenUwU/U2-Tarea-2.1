package objetos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Inventario {
    private int id;
    private String nombreCorto;
    private String descripcion;
    private String serie;
    private String color;
    private Date fechaAdq;
    private String tipoAdq;
    private String observaciones;
    
    private int idArea;
    
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    
    public Inventario() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getFechaAdq() {
        return fechaAdq;
    }

    public void setFechaAdq(Date fechaAdq) {
        this.fechaAdq = fechaAdq;
    }

    public String getTipoAdq() {
        return tipoAdq;
    }

    public void setTipoAdq(String tipoAdq) {
        this.tipoAdq = tipoAdq;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public ArrayList<String> getListaAtributos () {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(nombreCorto);
        lista.add(descripcion);
        lista.add(serie);
        lista.add(color);
        lista.add(formatoFecha.format(fechaAdq));
        lista.add(tipoAdq);
        lista.add(observaciones);
        lista.add("" + idArea);
        
        return lista;
    }
    
    @Override
    public String toString() {
        return "Inventario{" + "id=" + id + ", nombreCorto=" + nombreCorto + ", descripcion=" + descripcion + ", serie=" + serie + ", color=" + color + ", fechaAdq=" + fechaAdq + ", tipoAdq=" + tipoAdq + ", observaciones=" + observaciones + ", idArea=" + idArea + '}';
    }
}
