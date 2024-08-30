package com.Conexion;

import com.modelo.Paquete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaqueteDAO {
    private ConexionMysql conexion;

    public PaqueteDAO() {
        this.conexion = new ConexionMysql();
    }
    //Inserta un nuevo registro de paquetes de descuento a MySQL
    public boolean insertar(Paquete paquete) {
        String sql = "INSERT INTO Paquetes (nombre, descuento, precio) VALUES (?, ?, ?)";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, paquete.getNombre());
            stmt.setDouble(2, paquete.getDescuento());
            stmt.setDouble(3, paquete.getPrecio());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar paquete: " + e.getMessage());
            return false;
        }
    }
    //Se obtiene el registro de la tabla Paquete segun el codigo porporcionado 
    public Paquete obtenerPorCodigo(int codigo) {
        String sql = "SELECT * FROM Paquetes WHERE codigo = ?";
        Paquete paquete = null;
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                paquete = new Paquete(
                    rs.getInt("codigo"),
                    rs.getString("nombre"),
                    rs.getDouble("descuento"),
                    rs.getDouble("precio")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener paquete: " + e.getMessage());
        }
        return paquete;
    }
    //Lista que contiene todos los paquetes en MySQL
    public List<Paquete> obtenerTodos() {
        String sql = "SELECT * FROM Paquetes";
        List<Paquete> paquetes = new ArrayList<>();
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Paquete paquete = new Paquete(
                    rs.getInt("codigo"),
                    rs.getString("nombre"),
                    rs.getDouble("descuento"),
                    rs.getDouble("precio")
                );
                paquetes.add(paquete);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener paquetes: " + e.getMessage());
        }
        return paquetes;
    }
    //contiene los datos actualizados
    public boolean actualizar(Paquete paquete) {
        String sql = "UPDATE Paquetes SET nombre = ?, descuento = ?, precio = ? WHERE codigo = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, paquete.getNombre());
            stmt.setDouble(2, paquete.getDescuento());
            stmt.setDouble(3, paquete.getPrecio());
            stmt.setInt(4, paquete.getCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar paquete: " + e.getMessage());
            return false;
        }
    }
     //método que elimina
    public boolean eliminar(int codigo) {
        String sql = "DELETE FROM Paquetes WHERE codigo = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar paquete: " + e.getMessage());
            return false;
        }
    }
    //devuelve el mayor valor encontrado en la columna codigo.
    public int obtenerUltimoCodigo() {
        String sql = "SELECT MAX(codigo) FROM Paquetes";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el último código de paquete: " + e.getMessage());
        }
        return -1; 
    }
}
