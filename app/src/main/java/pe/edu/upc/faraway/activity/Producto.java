package pe.edu.upc.faraway.activity;


import java.io.Serializable;

public class Producto implements Serializable {


    private String nombre;
    private String descripcion;
    private int imgFoto;
    public Producto(Integer imgFoto, String nombre, String descripcion) {
        //Con parametros
        this.imgFoto = imgFoto;
        this.nombre = nombre;
        this.descripcion = descripcion;


    }
    public Producto () {
        //Sin Parametros
    }
    //IdProducto


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getFoto() {
        return imgFoto;
    }

    public void setFoto(Integer imgFoto) {
        this.imgFoto = imgFoto;
    }


}
