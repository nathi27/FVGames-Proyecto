package com.Conexion;

import com.Modelo.CarritoProducto;//Importo la clase CarritoProducto desde el paquete modelo, para mapear los datos a la base de datos 
import java.sql.Connection;//maneja la conexion a la base de datos MySQL
import java.sql.PreparedStatement;//se utiliza para ejecutar sentencias SQL precompiladas con parámetros (insertar, actualizar, eliminar y seleccionar datos)
import java.sql.ResultSet;// es una tabla de datos que representa los resultados de una consulta SQL
import java.sql.SQLException;//maneja errores que puedan ocurrir durante la interacción con la base de datos 
import java.util.ArrayList;//implementación de una lista que permite almacenar elementos de forma dinámica
import java.util.List;  //es una colección que representa una secuencia ordenada de elementos

//Cree esta clase que es donde se asocian las compras con los productos
public class CarritoProductoDAO {

    private ConexionMysql conexion;// inicializa la conexión a la base de datos

    public CarritoProductoDAO() {
        this.conexion = new ConexionMysql();
    }
    //Inserta un nuevo registro en la tabla Carrito_Productos a MySQL
    public boolean insertar(CarritoProducto carritoProducto) {
        String sql = "INSERT INTO Carrito_Productos (carrito_id, producto_id, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carritoProducto.getCarritoId());
            stmt.setInt(2, carritoProducto.getProductoId());
            stmt.setInt(3, carritoProducto.getCantidad());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar CarritoProducto: " + e.getMessage());
            return false;
        }
    }
    //Elimina un producto específico de un carrito específico en la base de datos.
    public boolean eliminarPorCarritoIdYProductoId(int carritoId, int productoId) { //carritoId y productoId para identificar el registro que se quiere eliminar.
        String sql = "DELETE FROM Carrito_Productos WHERE carrito_id = ? AND producto_id = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carritoId);
            stmt.setInt(2, productoId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto del carrito: " + e.getMessage());
            return false;
        }
    }

    // Este método recupera todos los productos asociados con un carrito específico.
    public List<CarritoProducto> obtenerPorCarritoId(int carritoId) { //carritoId para identificar los productos del carrito. 
        String sql = "SELECT * FROM Carrito_Productos WHERE carrito_id = ?";
        List<CarritoProducto> productosDelCarrito = new ArrayList<>();
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carritoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CarritoProducto carritoProducto = new CarritoProducto(
                        rs.getInt("id"),
                        rs.getInt("carrito_id"),
                        rs.getInt("producto_id"),
                        rs.getInt("cantidad")
                );
                productosDelCarrito.add(carritoProducto);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos del carrito: " + e.getMessage());
        }
        return productosDelCarrito;
    }
    //Actualiza los datos de un producto específico en un carrito específico.
    public boolean actualizar(CarritoProducto carritoProducto) {//contiene los datos a actualizar (ID del carrito, ID del producto, cantidad, ID del registro).
        String sql = "UPDATE Carrito_Productos SET carrito_id = ?, producto_id = ?, cantidad = ? WHERE id = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carritoProducto.getCarritoId());
            stmt.setInt(2, carritoProducto.getProductoId());
            stmt.setInt(3, carritoProducto.getCantidad());
            stmt.setInt(4, carritoProducto.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar CarritoProducto: " + e.getMessage());
            return false;
        }
    }
    //Elimina todos los productos de un carrito específico.
    public boolean eliminarPorCarritoId(int carritoId) { //identifica el carrito cuyos productos se quieren eliminar.
        String sql = "DELETE FROM Carrito_Productos WHERE carrito_id = ?";
        try (Connection conn = conexion.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carritoId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar productos del carrito: " + e.getMessage());
            return false;
        }
    }
    
    
}
