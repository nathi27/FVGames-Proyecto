package com.Controlador;

import com.Modelo.Producto;
import com.Conexion.ProductoDAO;
import com.Vista.ProductoCRUDView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoController {

    private ProductoCRUDView productoView;
    private ProductoDAO productoDAO;

    public ProductoController(ProductoCRUDView productoView) {
        this.productoView = productoView;
        this.productoDAO = new ProductoDAO();
        initController();
    }

    private void initController() {
        // Cargar productos en la vista al iniciar
        productoView.cargarProductos();
        
        // Configurar los listeners
        productoView.setInsertarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarProducto();
            }
        });

        productoView.setActualizarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        productoView.setEliminarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        productoView.setLimpiarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productoView.limpiarCampos();
            }
        });
    }

    private void insertarProducto() {
        Producto producto = new Producto();
        producto.setNombre(productoView.getNombre());
        producto.setCategoria(productoView.getCategoria());
        producto.setCantidad(productoView.getCantidad());
        producto.setPrecio(productoView.getPrecio());

        if (productoDAO.insertar(producto)) {
            productoView.showMessage("Producto insertado con éxito.");
            productoView.cargarProductos();
            productoView.limpiarCampos();
        } else {
            productoView.showMessage("Error al insertar el producto.");
        }
    }

    private void actualizarProducto() {
        int codigo = productoView.getCodigo();
        Producto producto = productoDAO.obtenerPorCodigo(codigo);

        if (producto != null) {
            producto.setNombre(productoView.getNombre());
            producto.setCategoria(productoView.getCategoria());
            producto.setCantidad(productoView.getCantidad());
            producto.setPrecio(productoView.getPrecio());

            if (productoDAO.actualizar(producto)) {
                productoView.showMessage("Producto actualizado con éxito.");
                productoView.cargarProductos();
                productoView.limpiarCampos();
            } else {
                productoView.showMessage("Error al actualizar el producto.");
            }
        } else {
            productoView.showMessage("El producto con código " + codigo + " no existe.");
        }
    }

    private void eliminarProducto() {
        int codigo = productoView.getCodigo();
        if (productoDAO.eliminar(codigo)) {
            productoView.showMessage("Producto eliminado con éxito.");
            productoView.cargarProductos();
            productoView.limpiarCampos();
        } else {
            productoView.showMessage("Error al eliminar el producto.");
        }
    }
}
