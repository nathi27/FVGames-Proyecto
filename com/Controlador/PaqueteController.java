package com.Controlador;

import com.Conexion.PaqueteDAO;
import com.Conexion.PaqueteProductoDAO;
import com.Conexion.ProductoDAO;
import com.Modelo.Producto;
import com.Vista.PaqueteCRUDView;
import com.modelo.Paquete;
import com.modelo.PaqueteProducto;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PaqueteController {

    private PaqueteCRUDView paqueteView;
    private PaqueteDAO paqueteDAO;
    private PaqueteProductoDAO paqueteProductoDAO;

    public PaqueteController(PaqueteCRUDView paqueteView) {
        this.paqueteView = paqueteView;
        this.paqueteDAO = new PaqueteDAO();
        this.paqueteProductoDAO = new PaqueteProductoDAO();
        initController();
    }

    private void initController() {
        paqueteView.cargarPaquetes();
        paqueteView.cargarProductos();

        paqueteView.setInsertarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarPaquete();
            }
        });

        paqueteView.setActualizarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPaquete();
            }
        });

        paqueteView.setEliminarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPaquete();
            }
        });

        paqueteView.setLimpiarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paqueteView.limpiarCampos();
            }
        });

        addTableSelectionListener();
    }

    private void addTableSelectionListener() {
        paqueteView.getTablePaquetes().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && paqueteView.getTablePaquetes().getSelectedRow() != -1) {
                    int selectedRow = paqueteView.getTablePaquetes().getSelectedRow();
                    int paqueteId = Integer.parseInt(paqueteView.getTablePaquetes().getValueAt(selectedRow, 0).toString());
                    Paquete paquete = paqueteDAO.obtenerPorCodigo(paqueteId);

                    if (paquete != null) {
                        paqueteView.setCodigo(paquete.getCodigo());
                        paqueteView.setNombre(paquete.getNombre());
                        paqueteView.setDescuento(paquete.getDescuento());
                        paqueteView.setPrecio(paquete.getPrecio());

                        List<PaqueteProducto> productosAsociados = paqueteProductoDAO.obtenerPorPaqueteId(paqueteId);
                        paqueteView.setProductosSeleccionados(productosAsociados);
                    }
                }
            }
        });
    }

    private void insertarPaquete() {
        List<String> productosSeleccionados = paqueteView.getProductosSeleccionados();

        if (productosSeleccionados.size() < 2 || productosSeleccionados.size() > 5) {
            paqueteView.showMessage("El paquete debe contener entre 2 y 5 productos.");
            return;
        }

        Paquete paquete = new Paquete();
        paquete.setNombre(paqueteView.getNombre());
        paquete.setDescuento(paqueteView.getDescuento());
        paquete.setPrecio(paqueteView.getPrecio()); // El precio ya fue calculado en la vista

        if (paqueteDAO.insertar(paquete)) {
            int paqueteId = paqueteDAO.obtenerUltimoCodigo();
            asociarProductosAPaquete(paqueteId);
            paqueteView.showMessage("Paquete insertado con éxito.");
            paqueteView.cargarPaquetes();
            paqueteView.limpiarCampos();
        } else {
            paqueteView.showMessage("Error al insertar el paquete.");
        }
    }

    private void actualizarPaquete() {
        int codigo = paqueteView.getCodigo();
        Paquete paquete = paqueteDAO.obtenerPorCodigo(codigo);

        if (paquete != null) {
            List<String> productosSeleccionados = paqueteView.getProductosSeleccionados();

            if (productosSeleccionados.size() < 2 || productosSeleccionados.size() > 5) {
                paqueteView.showMessage("El paquete debe contener entre 2 y 5 productos.");
                return;
            }

            paquete.setNombre(paqueteView.getNombre());
            paquete.setDescuento(paqueteView.getDescuento());
            paquete.setPrecio(paqueteView.getPrecio()); // El precio ya fue calculado en la vista

            if (paqueteDAO.actualizar(paquete)) {
                paqueteView.showMessage("Paquete actualizado con éxito.");
                eliminarAsociacionesProductos(paquete.getCodigo());
                asociarProductosAPaquete(paquete.getCodigo());
                paqueteView.cargarPaquetes();
                paqueteView.limpiarCampos();
            } else {
                paqueteView.showMessage("Error al actualizar el paquete.");
            }
        } else {
            paqueteView.showMessage("El paquete con código " + codigo + " no existe.");
        }
    }

    private void eliminarPaquete() {
        int codigo = paqueteView.getCodigo();
        if (paqueteDAO.eliminar(codigo)) {
            eliminarAsociacionesProductos(codigo);
            paqueteView.showMessage("Paquete eliminado con éxito.");
            paqueteView.cargarPaquetes();
            paqueteView.limpiarCampos();
        } else {
            paqueteView.showMessage("Error al eliminar el paquete.");
        }
    }

    private void asociarProductosAPaquete(int paqueteId) {
        List<String> productosSeleccionados = paqueteView.getProductosSeleccionados();
        for (String productoStr : productosSeleccionados) {
            int productoId = obtenerProductoIdPorNombre(productoStr);
            PaqueteProducto paqueteProducto = new PaqueteProducto(0, paqueteId, productoId);
            paqueteProductoDAO.insertar(paqueteProducto);
        }
    }

    private void eliminarAsociacionesProductos(int paqueteId) {
        paqueteProductoDAO.eliminarPorPaqueteId(paqueteId);
    }

    private int obtenerProductoIdPorNombre(String nombre) {
        ProductoDAO productoDAO = new ProductoDAO();
        Producto producto = productoDAO.obtenerPorNombre(nombre);
        return producto != null ? producto.getCodigo() : -1;
    }
}
