package com.Modelo;

public class MetodoPago {
    private int codigo;
    private String nombre;
    private String detalles;

    // Constructor vacío
    public MetodoPago() {}

    // Constructor con todos los parámetros
    public MetodoPago(int codigo, String nombre, String detalles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.detalles = detalles;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
