package com.Controlador;

import com.Conexion.CarritoCompraDAO;
import com.Conexion.CarritoProductoDAO;
import com.Conexion.ProductoDAO;
import com.Modelo.CarritoProducto;
import com.Modelo.Producto;
import com.Vista.CarritoCompraCRUDView;
import com.modelo.CarritoCompra;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CarritoCompraController {

    private CarritoCompraCRUDView carritoView;
    private CarritoCompraDAO carritoDAO;
    private CarritoProductoDAO carritoProductoDAO;
    private ProductoDAO productoDAO;

    public CarritoCompraController(CarritoCompraCRUDView carritoView) {
        this.carritoView = carritoView;
        this.carritoDAO = new CarritoCompraDAO();
        this.carritoProductoDAO = new CarritoProductoDAO();
        this.productoDAO = new ProductoDAO();
        initController();
    }

    private void initController() {
        carritoView.cargarClientes();
        carritoView.cargarProductos();
        carritoView.cargarMetodosPago();
        cargarCarritos();

        carritoView.setInsertarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarCarrito();
            }
        });

        carritoView.setActualizarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCarrito();
            }
        });

        carritoView.setEliminarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCarrito();
            }
        });

        carritoView.setLimpiarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carritoView.limpiarCampos();
            }
        });

        carritoView.setAgregarProductoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProductoAlCarrito();
            }
        });

        carritoView.setEliminarProductoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProductoDelCarrito();
            }
        });

        carritoView.setFiltrarPorUsuarioListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarPorUsuario();
            }
        });

        carritoView.setFiltrarPorFechaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarPorFecha();
            }
        });

        carritoView.setTodasComprasListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarCarritos();
            }
        });

        addTableSelectionListener();
    }

    private void cargarCarritos() {
        List<CarritoCompra> carritos = carritoDAO.obtenerTodos();
        cargarCarritosEnVista(carritos);
    }

    private void filtrarPorUsuario() {
        String cedula = carritoView.getCedulaUsuario();
        if (!cedula.isEmpty()) {
            List<CarritoCompra> carritos = carritoDAO.obtenerPorUsuario(cedula);
            cargarCarritosEnVista(carritos);
        } else {
            carritoView.showMessage("Debe ingresar una cédula para filtrar.");
        }
    }

    private void filtrarPorFecha() {
        Timestamp fechaInicio = carritoView.getFechaInicio();
        Timestamp fechaFin = carritoView.getFechaFin();
        if (fechaInicio != null && fechaFin != null) {
            List<CarritoCompra> carritos = carritoDAO.obtenerPorIntervaloFecha(fechaInicio, fechaFin);
            cargarCarritosEnVista(carritos);
        } else {
            carritoView.showMessage("Debe seleccionar un rango de fechas válido.");
        }
    }

    private void cargarCarritosEnVista(List<CarritoCompra> carritos) {
        DefaultTableModel model = (DefaultTableModel) carritoView.getTableCarritos().getModel();
        model.setRowCount(0);
        for (CarritoCompra carrito : carritos) {
            model.addRow(new Object[]{
                carrito.getCodigo(),
                carrito.getClienteCedula(),
                carrito.getFecha(),
                carrito.getEstado(),
                carrito.getMetodoPago(),
                carrito.getSubtotal(),
                carrito.getTotal()
            });
        }
    }

    private void addTableSelectionListener() {
        carritoView.getTableCarritos().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && carritoView.getTableCarritos().getSelectedRow() != -1) {
                    int selectedRow = carritoView.getTableCarritos().getSelectedRow();
                    String codigoStr = carritoView.getTableCarritos().getValueAt(selectedRow, 0).toString(); // Convertir a String primero
                    try {
                        int codigo = Integer.parseInt(codigoStr); // Convertir el String a Integer
                        cargarCarrito(codigo);
                    } catch (NumberFormatException ex) {
                        carritoView.showMessage("Error al convertir el código: " + ex.getMessage());
                    }
                }
            }
        });
    }

    private void cargarCarrito(int codigo) {
        CarritoCompra carrito = carritoDAO.obtenerPorCodigo(codigo);
        if (carrito != null) {
            carritoView.setCodigo(carrito.getCodigo());
            carritoView.setClienteCedula(carrito.getClienteCedula());
            carritoView.setEstado(carrito.getEstado());
            carritoView.setMetodoPago(carrito.getMetodoPago());
            carritoView.setDetalle(carrito.getDetalle());
            carritoView.setSubtotal(carrito.getSubtotal());
            carritoView.setTotal(carrito.getTotal());

            cargarProductosDelCarrito(carrito.getCodigo());
        }
    }

    private void cargarProductosDelCarrito(int carritoId) {
        // Aquí utilizamos la instancia de carritoProductoDAO para llamar al método obtenerPorCarritoId
        List<CarritoProducto> productosDelCarrito = carritoProductoDAO.obtenerPorCarritoId(carritoId);

        DefaultTableModel model = (DefaultTableModel) carritoView.getTableProductos().getModel();
        model.setRowCount(0);

        for (CarritoProducto carritoProducto : productosDelCarrito) {
            Producto producto = productoDAO.obtenerPorCodigo(carritoProducto.getProductoId());
            if (producto != null) {
                model.addRow(new Object[]{
                    producto.getNombre(),
                    carritoProducto.getCantidad()
                });
            }
        }

        recalcularTotales();
    }

    private void insertarCarrito() {
        if (validarProductosSeleccionados()) {
            CarritoCompra carrito = new CarritoCompra();
            carrito.setClienteCedula(carritoView.getClienteCedula());
            carrito.setFecha(Timestamp.valueOf(LocalDateTime.now()));
            carrito.setEstado(carritoView.getEstado());
            carrito.setMetodoPago(carritoView.getMetodoPago());
            carrito.setDetalle(carritoView.getDetalle());
            carrito.setSubtotal(carritoView.getSubtotal());
            carrito.setTotal(carritoView.getTotal());

            int carritoId = carritoDAO.insertar(carrito);
            if (carritoId > 0) {
                guardarProductosDelCarrito(carritoId);
                carritoView.showMessage("Carrito insertado con éxito.");
                cargarCarritos();
                carritoView.limpiarCampos();
            } else {
                carritoView.showMessage("Error al insertar el carrito.");
            }
        } else {
            carritoView.showMessage("Debe agregar al menos un producto al carrito.");
        }
    }

    private void actualizarCarrito() {
        int codigo = carritoView.getCodigo();
        CarritoCompra carrito = carritoDAO.obtenerPorCodigo(codigo);

        if (carrito != null) {
            carrito.setClienteCedula(carritoView.getClienteCedula());
            carrito.setEstado(carritoView.getEstado());
            carrito.setMetodoPago(carritoView.getMetodoPago());
            carrito.setDetalle(carritoView.getDetalle());
            carrito.setSubtotal(carritoView.getSubtotal());
            carrito.setTotal(carritoView.getTotal());

            if (carritoDAO.actualizar(carrito)) {
                eliminarProductosDelCarrito(codigo);
                guardarProductosDelCarrito(codigo);
                carritoView.showMessage("Carrito actualizado con éxito.");
                cargarCarritos();
                carritoView.limpiarCampos();
            } else {
                carritoView.showMessage("Error al actualizar el carrito.");
            }
        } else {
            carritoView.showMessage("El carrito con código " + codigo + " no existe.");
        }
    }

    private void eliminarCarrito() {
        int codigo = carritoView.getCodigo();
        if (carritoDAO.eliminar(codigo)) {
            eliminarProductosDelCarrito(codigo);
            carritoView.showMessage("Carrito eliminado con éxito.");
            cargarCarritos();
            carritoView.limpiarCampos();
        } else {
            carritoView.showMessage("Error al eliminar el carrito.");
        }
    }

    private void agregarProductoAlCarrito() {
        String productoSeleccionado = carritoView.getProductoSeleccionado();
        if (!productoSeleccionado.isEmpty()) {
            int productoId = Integer.parseInt(productoSeleccionado);
            Producto producto = productoDAO.obtenerPorCodigo(productoId);
            if (producto != null) {
                // Obtener la cantidad desde el campo de texto
                int cantidad;
                try {
                    cantidad = carritoView.getCantidad();
                    if (cantidad <= 0) {
                        carritoView.showMessage("La cantidad debe ser mayor que cero.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    carritoView.showMessage("Cantidad inválida.");
                    return;
                }

                if (producto.getCantidad() >= cantidad) {
                    // Obtener el código del carrito desde txtCodigo
                    int carritoId;
                    try {
                        carritoId = carritoView.getCodigo();
                    } catch (NumberFormatException e) {
                        carritoView.showMessage("Código de carrito inválido.");
                        return;
                    }

                    // Añadir el producto a la tabla de la interfaz
                    DefaultTableModel model = (DefaultTableModel) carritoView.getTableProductos().getModel();
                    model.addRow(new Object[]{producto.getNombre(), cantidad});

                    // Asociar el producto con el carrito existente en la base de datos
                    CarritoProducto carritoProducto = new CarritoProducto();
                    carritoProducto.setCarritoId(carritoId);
                    carritoProducto.setProductoId(productoId);
                    carritoProducto.setCantidad(cantidad);
                    if (carritoProductoDAO.insertar(carritoProducto)) {
                        recalcularTotales();
                    } else {
                        carritoView.showMessage("Error al agregar el producto al carrito.");
                    }
                } else {
                    carritoView.showMessage("No hay suficiente stock del producto seleccionado.");
                }
            }
        }
    }

    private void guardarProductosDelCarrito(int carritoId) {
        DefaultTableModel model = (DefaultTableModel) carritoView.getTableProductos().getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String nombreProducto = model.getValueAt(i, 0).toString();
            int cantidad = (int) model.getValueAt(i, 1);

            Producto producto = productoDAO.obtenerPorNombre(nombreProducto);
            if (producto != null) {
                CarritoProducto carritoProducto = new CarritoProducto();
                carritoProducto.setCarritoId(carritoId);
                carritoProducto.setProductoId(producto.getCodigo());
                carritoProducto.setCantidad(cantidad);
                carritoProductoDAO.insertar(carritoProducto);
            }
        }
    }

    private void eliminarProductoDelCarrito() {
        int selectedRow = carritoView.getTableProductos().getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) carritoView.getTableProductos().getModel();
            String nombreProducto = model.getValueAt(selectedRow, 0).toString();
            int carritoId = carritoView.getCodigo();

            // Obtener el producto a eliminar
            Producto producto = productoDAO.obtenerPorNombre(nombreProducto);
            if (producto != null) {
                // Eliminar la relación producto-carrito de la base de datos
                carritoProductoDAO.eliminarPorCarritoIdYProductoId(carritoId, producto.getCodigo());

                // Eliminar el producto de la tabla de la interfaz
                model.removeRow(selectedRow);

                // Recalcular los totales
                recalcularTotales();

                carritoView.showMessage("Producto eliminado del carrito.");
            }
        } else {
            carritoView.showMessage("Seleccione un producto para eliminar.");
        }
    }

    private void eliminarProductosDelCarrito(int carritoId) {
        carritoProductoDAO.eliminarPorCarritoId(carritoId);
    }

    private void recalcularTotales() {
        DefaultTableModel model = (DefaultTableModel) carritoView.getTableProductos().getModel();
        double subtotal = 0.0;

        for (int i = 0; i < model.getRowCount(); i++) {
            String nombreProducto = model.getValueAt(i, 0).toString();
            int cantidad = (int) model.getValueAt(i, 1);
            Producto producto = productoDAO.obtenerPorNombre(nombreProducto);
            if (producto != null) {
                subtotal += producto.getPrecio() * cantidad;
            }
        }

        double total = subtotal * 1.13; // Incluyendo IVA del 13%
        carritoView.setSubtotal(subtotal);
        carritoView.setTotal(total);
    }

    private boolean validarProductosSeleccionados() {
        return carritoView.getTableProductos().getRowCount() > 0;
    }
}
