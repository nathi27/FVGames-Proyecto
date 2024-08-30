package com.Modelo;

public class Usuario {
    private String cedula;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String password;  // Nuevo campo para la contraseña
    private double dineroCuenta;
    private String permiso;
    private int metodoPagoPreferido;  // Ahora es un int que referencia a MetodosPago

    // Constructor vacío
    public Usuario() {}

    // Constructor con todos los parámetros
    public Usuario(String cedula, String nombre, String apellidos, String direccion, String email, String password, double dineroCuenta, String permiso, int metodoPagoPreferido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.email = email;
        this.password = password;  // Inicialización del nuevo campo
        this.dineroCuenta = dineroCuenta;
        this.permiso = permiso;
        this.metodoPagoPreferido = metodoPagoPreferido;
    }

    // Getters y Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;  // Getter para la contraseña
    }

    public void setPassword(String password) {
        this.password = password;  // Setter para la contraseña
    }

    public double getDineroCuenta() {
        return dineroCuenta;
    }

    public void setDineroCuenta(double dineroCuenta) {
        this.dineroCuenta = dineroCuenta;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public int getMetodoPagoPreferido() {
        return metodoPagoPreferido;
    }

    public void setMetodoPagoPreferido(int metodoPagoPreferido) {
        this.metodoPagoPreferido = metodoPagoPreferido;
    }
}
