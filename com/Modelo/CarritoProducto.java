package com.Modelo;

public class CarritoProducto {
    private int id;
    private int carritoId;
    private int productoId;
    private int cantidad;

    // Constructor vacío
    public CarritoProducto() {}

    // Constructor con todos los parámetros
    public CarritoProducto(int id, int carritoId, int productoId, int cantidad) {
        this.id = id;
        this.carritoId = carritoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(int carritoId) {
        this.carritoId = carritoId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
