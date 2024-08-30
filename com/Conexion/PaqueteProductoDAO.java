package com.Conexion;

import com.modelo.PaqueteProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaqueteProductoDAO {

    private ConexionMysql conexion;

    public PaqueteProductoDAO() {
        this.conexion = new ConexionMysql();
    }

    public boolean insertar(PaqueteProducto paqueteProducto) {
        String sql = "INSERT INTO Paquetes_Productos (paquete_id, producto_id) VALUES (?, ?)";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paqueteProducto.getPaqueteId());
            stmt.setInt(2, paqueteProducto.getProductoId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar PaqueteProducto: " + e.getMessage());
            return false;
        }
    }

    public PaqueteProducto obtenerPorId(int id) {
        String sql = "SELECT * FROM Paquetes_Productos WHERE id = ?";
        PaqueteProducto paqueteProducto = null;
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                paqueteProducto = new PaqueteProducto(
                        rs.getInt("id"),
                        rs.getInt("paquete_id"),
                        rs.getInt("producto_id")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener PaqueteProducto: " + e.getMessage());
        }
        return paqueteProducto;
    }
    public List<PaqueteProducto> obtenerPorPaqueteId(int paqueteId) {
    String sql = "SELECT * FROM Paquetes_Productos WHERE paquete_id = ?";
    List<PaqueteProducto> productos = new ArrayList<>();
    try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, paqueteId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            productos.add(new PaqueteProducto(rs.getInt("id"), paqueteId, rs.getInt("producto_id")));
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener productos del paquete: " + e.getMessage());
    }
    return productos;
}


    public List<PaqueteProducto> obtenerTodos() {
        String sql = "SELECT * FROM Paquetes_Productos";
        List<PaqueteProducto> lista = new ArrayList<>();
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PaqueteProducto paqueteProducto = new PaqueteProducto(
                        rs.getInt("id"),
                        rs.getInt("paquete_id"),
                        rs.getInt("producto_id")
                );
                lista.add(paqueteProducto);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener PaquetesProductos: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(PaqueteProducto paqueteProducto) {
        String sql = "UPDATE Paquetes_Productos SET paquete_id = ?, producto_id = ? WHERE id = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paqueteProducto.getPaqueteId());
            stmt.setInt(2, paqueteProducto.getProductoId());
            stmt.setInt(3, paqueteProducto.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar PaqueteProducto: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Paquetes_Productos WHERE id = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar PaqueteProducto: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPorPaqueteId(int paqueteId) {
        String sql = "DELETE FROM Paquetes_Productos WHERE paquete_id = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paqueteId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar asociaciones de productos: " + e.getMessage());
            return false;
        }
    }

}
