package com.modelo;

public class PaqueteProducto {
    private int id;
    private int paqueteId;
    private int productoId;

    // Constructor vacío
    public PaqueteProducto() {}

    // Constructor con todos los parámetros
    public PaqueteProducto(int id, int paqueteId, int productoId) {
        this.id = id;
        this.paqueteId = paqueteId;
        this.productoId = productoId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaqueteId() {
        return paqueteId;
    }

    public void setPaqueteId(int paqueteId) {
        this.paqueteId = paqueteId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }
}
