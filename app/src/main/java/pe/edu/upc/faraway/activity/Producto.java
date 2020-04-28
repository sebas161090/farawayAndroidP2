package pe.edu.upc.faraway.activity;


import com.google.gson.internal.$Gson$Preconditions;

import java.io.Serializable;

public class Producto implements Serializable {

    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private int imgFoto;
    private double precio;
    private int stock;
    public Producto(Integer idProducto, Integer imgFoto, String nombre, String descripcion, Double precio, Integer stock) {
        //Con parametros
        this.idProducto = idProducto;
        this.imgFoto = imgFoto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;


    }
    public Producto () {
        //Sin Parametros
    }


    public Integer getIdProducto() {
        return idProducto;
    }

    public void setId(Integer idProducto) {
        this.idProducto = idProducto;
    }

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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


}
