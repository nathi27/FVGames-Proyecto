package com.Conexion;

import com.modelo.Transaccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDAO {
    private ConexionMysql conexion;

    public TransaccionDAO() {
        this.conexion = new ConexionMysql();
    }

    public boolean insertar(Transaccion transaccion) {
        String sql = "INSERT INTO Transacciones (cliente_cedula, monto, detalle) VALUES (?, ?, ?)";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transaccion.getClienteCedula());
            stmt.setDouble(2, transaccion.getMonto());
            stmt.setString(3, transaccion.getDetalle());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar transacción: " + e.getMessage());
            return false;
        }
    }

    public Transaccion obtenerPorCodigo(int codigo) {
        String sql = "SELECT * FROM Transacciones WHERE codigo = ?";
        Transaccion transaccion = null;
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                transaccion = new Transaccion(
                    rs.getInt("codigo"),
                    rs.getString("cliente_cedula"),
                    rs.getDouble("monto"),
                    rs.getString("detalle")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener transacción: " + e.getMessage());
        }
        return transaccion;
    }

    public List<Transaccion> obtenerTodas() {
        String sql = "SELECT * FROM Transacciones";
        List<Transaccion> transacciones = new ArrayList<>();
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Transaccion transaccion = new Transaccion(
                    rs.getInt("codigo"),
                    rs.getString("cliente_cedula"),
                    rs.getDouble("monto"),
                    rs.getString("detalle")
                );
                transacciones.add(transaccion);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener transacciones: " + e.getMessage());
        }
        return transacciones;
    }

    public boolean actualizar(Transaccion transaccion) {
        String sql = "UPDATE Transacciones SET cliente_cedula = ?, monto = ?, detalle = ? WHERE codigo = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transaccion.getClienteCedula());
            stmt.setDouble(2, transaccion.getMonto());
            stmt.setString(3, transaccion.getDetalle());
            stmt.setInt(4, transaccion.getCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar transacción: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int codigo) {
        String sql = "DELETE FROM Transacciones WHERE codigo = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar transacción: " + e.getMessage());
            return false;
        }
    }
}
