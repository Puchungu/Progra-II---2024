package com.ugb.controlesbasicos;

public class Productos {
   String idProd;
    String nombre;
    String descripcion;
    String marca;
    String presentacion;
    String precio;
    String urlFotoProd;

    public Productos(String idProd, String nombre, String descripcion, String marca, String presentacion, String precio, String urlFoto) {
        this.idProd = idProd;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.presentacion = presentacion;
        this.precio = precio;
        this.urlFotoProd = urlFoto;

    }

    public String getUrlFotoProd() {
        return urlFotoProd;
    }

    public void setUrlFotoProd(String urlFotoProd) {
        this.urlFotoProd = urlFotoProd;
    }
    public String getIdProd() {
        return idProd;
    }

    public void setIdProd(String idAmigo) {
        this.idProd = idAmigo;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String tmarca) {
        this.marca = marca;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
