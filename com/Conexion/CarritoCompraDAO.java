package com.Conexion;

import com.modelo.CarritoCompra; //Importo la clase CarritoCompra desde el paquete modelo, para mapear los datos a la base de datos 

import java.sql.Connection; //maneja la conexion a la base de datos MySQL
import java.sql.PreparedStatement; //se utiliza para ejecutar sentencias SQL precompiladas con parámetros (insertar, actualizar, eliminar y seleccionar datos)
import java.sql.ResultSet; // es una tabla de datos que representa los resultados de una consulta SQL
import java.sql.SQLException; //maneja errores que puedan ocurrir durante la interacción con la base de datos 
import java.sql.Timestamp; // maneja las fechas de los carritos de compras
import java.util.ArrayList; //implementación de una lista que permite almacenar elementos de forma dinámica
import java.util.List; //es una colección que representa una secuencia ordenada de elementos

public class CarritoCompraDAO {
    private ConexionMysql conexion; // inicializa la conexión a la base de datos

    public CarritoCompraDAO() {
        this.conexion = new ConexionMysql();
    }

    //Inserta un nuevo registro de carrito de compras a MySQL
    public int insertar(CarritoCompra carrito) {
        //Sentencia SQL para insertar un nuevo registro en la tabla CarritoCompras
        String sql = "INSERT INTO CarritoCompras (cliente_cedula, fecha, estado, metodo_pago, detalle, subtotal, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        //almacena el ID generado automáticamente por la base de datos
        int generatedId = -1; //Exitosa: se genera un ID, el valor de generatedId se actualizará con ese ID. Error: la variable retendrá el valor -1, indicando que la inserción no fue exitosa.

        //Asegura que los valores dentro del () se cierren automáticamente al final del try
        try (Connection conn = conexion.getConection(); //obtiene la conexion con MySQL
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Establece los valores de los parámetros de la sentencia SQL.
            stmt.setString(1, carrito.getClienteCedula());
            stmt.setTimestamp(2, carrito.getFecha());
            stmt.setString(3, carrito.getEstado());
            stmt.setInt(4, carrito.getMetodoPago());
            stmt.setString(5, carrito.getDetalle());
            stmt.setDouble(6, carrito.getSubtotal());
            stmt.setDouble(7, carrito.getTotal());
            stmt.executeUpdate();// Ejecuta la sentencia SQL de inserción.
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();// Obtiene las claves generadas automáticamente (ID del carrito insertado).
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar carrito de compras: " + e.getMessage());
        }

        return generatedId;
    }

    //Se obtiene el registro de la tabla CarritoCompras segun el codigo porporcionado 
    public CarritoCompra obtenerPorCodigo(int codigo) {
        //Sentencia SQL para seleccionar un codigo en la tabla CarritoCompras
        String sql = "SELECT * FROM CarritoCompras WHERE codigo = ?";
        CarritoCompra carrito = null; //Objeto CarritoCompra que representa el carrito recuperado, o null si no se encuentra.
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                carrito = new CarritoCompra(
                    rs.getInt("codigo"),
                    rs.getString("cliente_cedula"),
                    rs.getTimestamp("fecha"),
                    rs.getString("estado"),
                    rs.getInt("metodo_pago"),
                    rs.getString("detalle"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("total")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener carrito de compras: " + e.getMessage());
        }
        return carrito;
    }
    //Lista que contiene todos los carritos en MySQL
    public List<CarritoCompra> obtenerTodos() {
        String sql = "SELECT * FROM CarritoCompras"; //Sentencia SQL para obtener todos los registros de la tabla 
        List<CarritoCompra> carritos = new ArrayList<>();//Para cada registro obtenido, se crea un objeto CarritoCompra y se añade a la lista carritos.
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CarritoCompra carrito = new CarritoCompra(
                    rs.getInt("codigo"),
                    rs.getString("cliente_cedula"),
                    rs.getTimestamp("fecha"),
                    rs.getString("estado"),
                    rs.getInt("metodo_pago"),
                    rs.getString("detalle"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("total")
                );
                carritos.add(carrito); //añade el carrito obtenido a la base de datos
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener carritos de compras: " + e.getMessage());
        }
        return carritos;
    }
    //contiene los datos actualizados del carrito.
    public boolean actualizar(CarritoCompra carrito) {
        //consulta SQL UPDATE para modificar los datos de un carrito de compras específico 
        String sql = "UPDATE CarritoCompras SET cliente_cedula = ?, fecha = ?, estado = ?, metodo_pago = ?, detalle = ?, subtotal = ?, total = ? WHERE codigo = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carrito.getClienteCedula());
            stmt.setTimestamp(2, carrito.getFecha());
            stmt.setString(3, carrito.getEstado());
            stmt.setInt(4, carrito.getMetodoPago());
            stmt.setString(5, carrito.getDetalle());
            stmt.setDouble(6, carrito.getSubtotal());
            stmt.setDouble(7, carrito.getTotal());
            stmt.setInt(8, carrito.getCodigo());
            stmt.executeUpdate(); // Ejecuta la sentencia SQL de inserción.
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar carrito de compras: " + e.getMessage());
            return false;
        }
    }
    //método que elimina un carrito de compras de la base de datos según su código
    public boolean eliminar(int codigo) {
        // consulta SQL DELETE para eliminar el carrito especificado.
        String sql = "DELETE FROM CarritoCompras WHERE codigo = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();// Ejecuta la sentencia SQL de inserción.
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar carrito de compras: " + e.getMessage());
            return false;
        }
    }
    //método que obtiene todos los carritos de compras de un cliente específico (según su cédula).
    public List<CarritoCompra> obtenerPorUsuario(String cedulaUsuario) {
        String sql = "SELECT * FROM CarritoCompras WHERE cliente_cedula = ?";
        List<CarritoCompra> carritos = new ArrayList<>(); //lo añade a la lista
        try (Connection conn = conexion.getConection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, cedulaUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CarritoCompra carrito = new CarritoCompra(
                    rs.getInt("codigo"),
                    rs.getString("cliente_cedula"),
                    rs.getTimestamp("fecha"),
                    rs.getString("estado"),
                    rs.getInt("metodo_pago"),
                    rs.getString("detalle"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("total")
                );
                carritos.add(carrito);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener carritos por usuario: " + e.getMessage());
        }
        return carritos;
    }
    //obtiene todos los carritos de compras que se crearon en un intervalo de fechas especificado.
    public List<CarritoCompra> obtenerPorIntervaloFecha(Timestamp fechaInicio, Timestamp fechaFin) {
        String sql = "SELECT * FROM CarritoCompras WHERE fecha BETWEEN ? AND ?";
        List<CarritoCompra> carritos = new ArrayList<>();
        try (Connection conn = conexion.getConection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setTimestamp(1, fechaInicio);
            stmt.setTimestamp(2, fechaFin);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CarritoCompra carrito = new CarritoCompra(
                    rs.getInt("codigo"),
                    rs.getString("cliente_cedula"),
                    rs.getTimestamp("fecha"),
                    rs.getString("estado"),
                    rs.getInt("metodo_pago"),
                    rs.getString("detalle"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("total")
                );
                carritos.add(carrito);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener carritos por intervalo de fecha: " + e.getMessage());
        }
        return carritos;
    }
}
