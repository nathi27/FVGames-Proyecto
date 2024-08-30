package com.modelo;

import java.sql.Timestamp;

public class CarritoCompra {
    private int codigo;
    private String clienteCedula;
    private Timestamp fecha;
    private String estado;
    private int metodoPago;
    private String detalle;
    private double subtotal;
    private double total;

    // Constructor vacío
    public CarritoCompra() {}

    // Constructor con todos los parámetros
    public CarritoCompra(int codigo, String clienteCedula, Timestamp fecha, String estado, int metodoPago, String detalle, double subtotal, double total) {
        this.codigo = codigo;
        this.clienteCedula = clienteCedula;
        this.fecha = fecha;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.detalle = detalle;
        this.subtotal = subtotal;
        this.total = total;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getClienteCedula() {
        return clienteCedula;
    }

    public void setClienteCedula(String clienteCedula) {
        this.clienteCedula = clienteCedula;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(int metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
