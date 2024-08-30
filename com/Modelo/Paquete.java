package com.modelo;

public class Paquete {
    private int codigo;
    private String nombre;
    private double descuento;
    private double precio;

    // Constructor vacío
    public Paquete() {}

    // Constructor con todos los parámetros
    public Paquete(int codigo, String nombre, double descuento, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descuento = descuento;
        this.precio = precio;
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

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
