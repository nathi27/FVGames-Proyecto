package com.Conexion;

import com.Modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private ConexionMysql conexion;

    public ProductoDAO() {
        this.conexion = new ConexionMysql();
    }

    public boolean insertar(Producto producto) {
        String sql = "INSERT INTO Productos (nombre, categoria, cantidad, precio, imagen) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCategoria());
            stmt.setInt(3, producto.getCantidad());
            stmt.setDouble(4, producto.getPrecio());
            stmt.setString(5, producto.getImagen());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
            return false;
        }
    }

    public Producto obtenerPorCodigo(int codigo) {
        String sql = "SELECT * FROM Productos WHERE codigo = ?";
        Producto producto = null;
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                producto = new Producto(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"),
                        rs.getString("imagen")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }
        return producto;
    }

    public List<Producto> obtenerTodos() {
        String sql = "SELECT * FROM Productos";
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"),
                        rs.getString("imagen")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }

    public boolean actualizar(Producto producto) {
        String sql = "UPDATE Productos SET nombre = ?, categoria = ?, cantidad = ?, precio = ?, imagen = ? WHERE codigo = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCategoria());
            stmt.setInt(3, producto.getCantidad());
            stmt.setDouble(4, producto.getPrecio());
            stmt.setString(5, producto.getImagen());
            stmt.setInt(6, producto.getCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int codigo) {
        String sql = "DELETE FROM Productos WHERE codigo = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

    public Producto obtenerPorNombre(String nombre) {
        String sql = "SELECT * FROM Productos WHERE nombre = ?";
        Producto producto = null;
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                producto = new Producto(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"),
                        rs.getString("imagen")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }
        return producto;
    }

}
