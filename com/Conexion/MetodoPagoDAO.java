package com.Conexion;

import com.Modelo.MetodoPago;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MetodoPagoDAO {
    private ConexionMysql conexion;// inicializa la conexión a la base de datos

    public MetodoPagoDAO() {
        this.conexion = new ConexionMysql();
    }
    //Inserta un nuevo registro de metodo de pago a MySQL
    public boolean insertar(MetodoPago metodoPago) {
        //Sentencia SQL para insertar un nuevo registro en la tabla metodosPago
        String sql = "INSERT INTO MetodosPago (nombre, detalles) VALUES (?, ?)";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, metodoPago.getNombre());
            stmt.setString(2, metodoPago.getDetalles());
            stmt.executeUpdate();// Ejecuta la sentencia SQL de inserción
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar método de pago: " + e.getMessage());
            return false;
        }
    }
    //Se obtiene el registro de la tabla metodosPago segun el codigo porporcionado 
    public MetodoPago obtenerPorCodigo(int codigo) {
        //Sentencia SQL para seleccionar un codigo en la tabla metodosPago
        String sql = "SELECT * FROM MetodosPago WHERE codigo = ?";
        MetodoPago metodoPago = null;//Objeto MetodoPago que representa el metodo de pago recuperado o obtenido, o null si no se encuentra.
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                metodoPago = new MetodoPago(
                    rs.getInt("codigo"),
                    rs.getString("nombre"),
                    rs.getString("detalles")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener método de pago: " + e.getMessage());
        }
        return metodoPago;
    }
    //Lista que contiene todos los metodos de pago en MySQL
    public List<MetodoPago> obtenerTodos() {
        String sql = "SELECT * FROM MetodosPago";//Sentencia SQL para obtener todos los registros de la tabla 
        List<MetodoPago> metodosPago = new ArrayList<>();
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                MetodoPago metodoPago = new MetodoPago(
                    rs.getInt("codigo"),
                    rs.getString("nombre"),
                    rs.getString("detalles")
                );
                metodosPago.add(metodoPago);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener métodos de pago: " + e.getMessage());
        }
        return metodosPago;
    }
    //contiene los datos actualizados del metodo de pago.
    public boolean actualizar(MetodoPago metodoPago) {
        //consulta SQL UPDATE para modificar los datos
        String sql = "UPDATE MetodosPago SET nombre = ?, detalles = ? WHERE codigo = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, metodoPago.getNombre());
            stmt.setString(2, metodoPago.getDetalles());
            stmt.setInt(3, metodoPago.getCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar método de pago: " + e.getMessage());
            return false;
        }
    }
    //método que elimina un metodo de pago de la base de datos según su código
    public boolean eliminar(int codigo) {
        // consulta SQL DELETE para eliminar el metodopago especificado.
        String sql = "DELETE FROM MetodosPago WHERE codigo = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar método de pago: " + e.getMessage());
            return false;
        }
    }
        //método que obtiene todos los metodos de pago de un cliente específico (según su nombre).
    public MetodoPago obtenerPorNombre(String nombre) {
        String sql = "SELECT * FROM MetodosPago WHERE nombre = ?";
        MetodoPago metodoPago = null;
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                metodoPago = new MetodoPago(
                    rs.getInt("codigo"),
                    rs.getString("nombre"),
                    rs.getString("detalles")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener método de pago: " + e.getMessage());
        }
        return metodoPago;
    }
}
