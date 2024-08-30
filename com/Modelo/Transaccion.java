package com.modelo;

public class Transaccion {
    private int codigo;
    private String clienteCedula;
    private double monto;
    private String detalle;

    // Constructor vacío
    public Transaccion() {}

    // Constructor con todos los parámetros
    public Transaccion(int codigo, String clienteCedula, double monto, String detalle) {
        this.codigo = codigo;
        this.clienteCedula = clienteCedula;
        this.monto = monto;
        this.detalle = detalle;
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
