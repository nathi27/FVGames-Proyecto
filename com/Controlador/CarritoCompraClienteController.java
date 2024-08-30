package com.Controlador;

import com.Conexion.CarritoCompraDAO;
import com.Conexion.CarritoProductoDAO;
import com.Conexion.ProductoDAO;
import com.Conexion.UsuarioDAO;
import com.Modelo.CarritoProducto;
import com.Modelo.Producto;
import com.Modelo.Usuario;
import com.Vista.CarritoCompraClienteCRUDView;
import com.modelo.CarritoCompra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class CarritoCompraClienteController {

    private CarritoCompraClienteCRUDView carritoView;
    private CarritoCompraDAO carritoDAO;
    private CarritoProductoDAO carritoProductoDAO;
    private ProductoDAO productoDAO;
    private String clienteCedula;

    public CarritoCompraClienteController(CarritoCompraClienteCRUDView carritoView, String clienteCedula) {
        this.carritoView = carritoView;
        this.carritoDAO = new CarritoCompraDAO();
        this.carritoProductoDAO = new CarritoProductoDAO();
        this.productoDAO = new ProductoDAO();
        this.clienteCedula = clienteCedula;

        // Validar si el usuario está logueado
        if (LoginController.getUsuarioLogueado() == null) {
            carritoView.showMessage("Debe iniciar sesión para realizar esta acción.");
            return;
        }

        initController();

        // Actualizar el dinero restante en la cuenta del cliente
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.obtenerPorCedula(clienteCedula);
        if (usuario != null) {
            double dineroDisponible = usuario.getDineroCuenta();
            carritoView.setDineroCuenta(dineroDisponible);
        }
    }

    private void initController() {
        cargarCarritosDelCliente();

        carritoView.setInsertarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LoginController.getUsuarioLogueado() != null) {
                    insertarCarrito();
                } else {
                    carritoView.showMessage("Debe iniciar sesión para realizar esta acción.");
                }
            }
        });

        carritoView.setActualizarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LoginController.getUsuarioLogueado() != null) {
                    actualizarCarrito();
                } else {
                    carritoView.showMessage("Debe iniciar sesión para realizar esta acción.");
                }
            }
        });

        carritoView.setEliminarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LoginController.getUsuarioLogueado() != null) {
                    eliminarCarrito();
                } else {
                    carritoView.showMessage("Debe iniciar sesión para realizar esta acción.");
                }
            }
        });

        carritoView.setPagarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LoginController.getUsuarioLogueado() != null) {
                    pagarCarrito();
                } else {
                    carritoView.showMessage("Debe iniciar sesión para realizar esta acción.");
                }
            }
        });

        carritoView.setLimpiarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LoginController.getUsuarioLogueado() != null) {
                    carritoView.limpiarCampos();
                } else {
                    carritoView.showMessage("Debe iniciar sesión para realizar esta acción.");
                }
            }
        });

        carritoView.setAgregarProductoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LoginController.getUsuarioLogueado() != null) {
                    agregarProductoAlCarrito();
                } else {
                    carritoView.showMessage("Debe iniciar sesión para realizar esta acción.");
                }
            }
        });

        carritoView.setEliminarProductoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LoginController.getUsuarioLogueado() != null) {
                    eliminarProductoDelCarrito();
                } else {
                    carritoView.showMessage("Debe iniciar sesión para realizar esta acción.");
                }
            }
        });

        addTableSelectionListener();
    }

    private void cargarCarritosDelCliente() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.obtenerPorCedula(clienteCedula);
        if (usuario != null) {
            carritoView.setDineroCuenta(usuario.getDineroCuenta());
        }

        List<CarritoCompra> carritos = carritoDAO.obtenerPorUsuario(clienteCedula);
        carritoView.cargarCarritos(carritos);
    }

    private void insertarCarrito() {
        if (!validarProductosSeleccionados()) {
            carritoView.showMessage("Debe agregar al menos un producto al carrito.");
            return;
        }

        CarritoCompra carrito = new CarritoCompra();
        carrito.setClienteCedula(clienteCedula);  // Usa la cédula del cliente logueado
        carrito.setFecha(Timestamp.valueOf(LocalDateTime.now()));
        carrito.setEstado("Sin Pagar");
        carrito.setMetodoPago(carritoView.getMetodoPago());
        carrito.setDetalle(carritoView.getDetalle());
        carrito.setSubtotal(carritoView.getSubtotal());
        carrito.setTotal(carritoView.getTotal());

        int carritoId = carritoDAO.insertar(carrito);
        if (carritoId > 0) {
            guardarProductosDelCarrito(carritoId);
            carritoView.showMessage("Carrito insertado con éxito.");
            cargarCarritosDelCliente();
            carritoView.limpiarCampos();
        } else {
            carritoView.showMessage("Error al insertar el carrito.");
        }
    }

    private void actualizarCarrito() {
        int codigo = carritoView.getCodigo();
        CarritoCompra carrito = carritoDAO.obtenerPorCodigo(codigo);

        if (carrito != null) {
            carrito.setEstado(carritoView.getEstado());
            carrito.setMetodoPago(carritoView.getMetodoPago());
            carrito.setDetalle(carritoView.getDetalle());
            carrito.setSubtotal(carritoView.getSubtotal());
            carrito.setTotal(carritoView.getTotal());

            if (carritoDAO.actualizar(carrito)) {
                eliminarProductosDelCarrito(codigo);
                guardarProductosDelCarrito(codigo);
                carritoView.showMessage("Carrito actualizado con éxito.");
                cargarCarritosDelCliente();
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
            cargarCarritosDelCliente();
            carritoView.limpiarCampos();
        } else {
            carritoView.showMessage("Error al eliminar el carrito.");
        }
    }

    private void pagarCarrito() {
        int selectedRow = carritoView.getTableCarritos().getSelectedRow();
        if (selectedRow != -1) {
            int codigo = (int) carritoView.getTableCarritos().getValueAt(selectedRow, 0);
            CarritoCompra carrito = carritoDAO.obtenerPorCodigo(codigo);
            if (carrito != null) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.obtenerPorCedula(clienteCedula);

                double totalCarrito = carrito.getTotal();
                double dineroCuenta = usuario.getDineroCuenta();

                if (dineroCuenta >= totalCarrito) {
                    // Reducir stock y actualizar estado del carrito
                    List<CarritoProducto> productosDelCarrito = carritoProductoDAO.obtenerPorCarritoId(codigo);
                    boolean hayStockSuficiente = true;

                    for (CarritoProducto carritoProducto : productosDelCarrito) {
                        Producto producto = productoDAO.obtenerPorCodigo(carritoProducto.getProductoId());
                        if (producto != null) {
                            int nuevaCantidad = producto.getCantidad() - carritoProducto.getCantidad();
                            if (nuevaCantidad < 0) {
                                hayStockSuficiente = false;
                                carritoView.showMessage("No hay suficiente stock para el producto: " + producto.getNombre());
                                break;
                            }
                        }
                    }

                    if (hayStockSuficiente) {
                        for (CarritoProducto carritoProducto : productosDelCarrito) {
                            Producto producto = productoDAO.obtenerPorCodigo(carritoProducto.getProductoId());
                            if (producto != null) {
                                int nuevaCantidad = producto.getCantidad() - carritoProducto.getCantidad();
                                producto.setCantidad(nuevaCantidad);
                                productoDAO.actualizar(producto);
                            }
                        }

                        // Marcar el carrito como "Pagado"
                        carrito.setEstado("Pagado");
                        carritoDAO.actualizar(carrito);

                        // Descontar el dinero del cliente
                        usuario.setDineroCuenta(dineroCuenta - totalCarrito);
                        usuarioDAO.actualizar(usuario);
                        carritoView.setDineroCuenta(usuario.getDineroCuenta());

                        cargarCarritosDelCliente();
                        carritoView.showMessage("El carrito ha sido pagado.");
                    }
                } else {
                    carritoView.showMessage("No tienes suficiente dinero para pagar este carrito.");
                }
            } else {
                carritoView.showMessage("Error al procesar el pago.");
            }
        } else {
            carritoView.showMessage("Seleccione un carrito para pagar.");
        }
    }

    private void agregarProductoAlCarrito() {
        String productoSeleccionado = carritoView.getProductoSeleccionado();
        if (!productoSeleccionado.isEmpty()) {
            int productoId = Integer.parseInt(productoSeleccionado);
            Producto producto = productoDAO.obtenerPorCodigo(productoId);
            if (producto != null) {
                int cantidad = carritoView.getCantidad();
                if (cantidad > 0) {
                    if (producto.getCantidad() >= cantidad) {
                        DefaultTableModel model = (DefaultTableModel) carritoView.getTableProductos().getModel();
                        model.addRow(new Object[]{producto.getNombre(), cantidad});
                        carritoView.showMessage("Producto agregado al carrito.");
                        recalcularTotales();
                    } else {
                        carritoView.showMessage("Stock insuficiente para el producto: " + producto.getNombre());
                    }
                } else {
                    carritoView.showMessage("La cantidad debe ser mayor que cero.");
                }
            } else {
                carritoView.showMessage("El producto seleccionado no existe.");
            }
        } else {
            carritoView.showMessage("Debe seleccionar un producto.");
        }
    }

    private void eliminarProductoDelCarrito() {
        int selectedRow = carritoView.getTableProductos().getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) carritoView.getTableProductos().getModel();
            model.removeRow(selectedRow);
            carritoView.showMessage("Producto eliminado del carrito.");
            recalcularTotales();
        } else {
            carritoView.showMessage("Seleccione un producto para eliminar.");
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

        // Actualizar el dinero restante en la cuenta del cliente
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.obtenerPorCedula(clienteCedula);
        if (usuario != null) {
            double dineroDisponible = usuario.getDineroCuenta();
            carritoView.setDineroCuenta(dineroDisponible);
        }
    }

    private boolean validarProductosSeleccionados() {
        return carritoView.getTableProductos().getRowCount() > 0;
    }

    private void addTableSelectionListener() {
        carritoView.getTableCarritos().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && carritoView.getTableCarritos().getSelectedRow() != -1) {
                    int selectedRow = carritoView.getTableCarritos().getSelectedRow();
                    int codigo = (int) carritoView.getTableCarritos().getValueAt(selectedRow, 0);
                    cargarCarrito(codigo);
                }
            }
        });
    }

    private void cargarCarrito(int codigo) {
        CarritoCompra carrito = carritoDAO.obtenerPorCodigo(codigo);
        if (carrito != null) {
            carritoView.setCodigo(carrito.getCodigo());
            carritoView.setEstado(carrito.getEstado());
            carritoView.setMetodoPago(carrito.getMetodoPago());
            carritoView.setDetalle(carrito.getDetalle());
            carritoView.setSubtotal(carrito.getSubtotal());
            carritoView.setTotal(carrito.getTotal());
            cargarProductosDelCarrito(carrito.getCodigo());
        }
    }

    private void cargarProductosDelCarrito(int carritoId) {
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
}
