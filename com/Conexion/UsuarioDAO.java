package com.Conexion;

import com.Modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private ConexionMysql conexion;

    public UsuarioDAO() {
        this.conexion = new ConexionMysql();
    }

    public boolean insertar(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (cedula, nombre, apellidos, direccion, email, password, dinero_cuenta, permiso, metodo_pago_preferido) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getCedula());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellidos());
            stmt.setString(4, usuario.getDireccion());
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getPassword());
            stmt.setDouble(7, usuario.getDineroCuenta());
            stmt.setString(8, usuario.getPermiso());
            stmt.setInt(9, usuario.getMetodoPagoPreferido());  // Insertar el ID de metodo_pago_preferido
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuario obtenerPorCedula(String cedula) {
        String sql = "SELECT * FROM Usuarios WHERE cedula = ?";
        Usuario usuario = null;
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("direccion"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getDouble("dinero_cuenta"),
                    rs.getString("permiso"),
                    rs.getInt("metodo_pago_preferido")  // Obtener el ID de metodo_pago_preferido
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuario: " + e.getMessage());
        }
        return usuario;
    }

    public List<Usuario> obtenerTodos() {
        String sql = "SELECT * FROM Usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("direccion"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getDouble("dinero_cuenta"),
                    rs.getString("permiso"),
                    rs.getInt("metodo_pago_preferido")  // Obtener el ID de metodo_pago_preferido
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE Usuarios SET nombre = ?, apellidos = ?, direccion = ?, email = ?, password = ?, dinero_cuenta = ?, permiso = ?, metodo_pago_preferido = ? WHERE cedula = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellidos());
            stmt.setString(3, usuario.getDireccion());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getPassword());
            stmt.setDouble(6, usuario.getDineroCuenta());
            stmt.setString(7, usuario.getPermiso());
            stmt.setInt(8, usuario.getMetodoPagoPreferido()); 
            stmt.setString(9, usuario.getCedula());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(String cedula) {
        String sql = "DELETE FROM Usuarios WHERE cedula = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cedula);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}
