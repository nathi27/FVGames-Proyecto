package com.Controlador;

import com.Conexion.MetodoPagoDAO;
import com.Modelo.MetodoPago;
import com.Vista.MetodoPagoCRUDView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MetodoPagoController {

    private MetodoPagoCRUDView metodoPagoView;
    private MetodoPagoDAO metodoPagoDAO;

    public MetodoPagoController(MetodoPagoCRUDView metodoPagoView) {
        this.metodoPagoView = metodoPagoView;
        this.metodoPagoDAO = new MetodoPagoDAO();
        initController();
    }

    private void initController() {
        metodoPagoView.cargarMetodosPago();

        metodoPagoView.setInsertarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarMetodoPago();
            }
        });

        metodoPagoView.setActualizarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarMetodoPago();
            }
        });

        metodoPagoView.setEliminarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarMetodoPago();
            }
        });

        metodoPagoView.setLimpiarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodoPagoView.limpiarCampos();
            }
        });
    }

    private void insertarMetodoPago() {
        String nombre = metodoPagoView.getNombre();
        String detalles = metodoPagoView.getDetalles();

        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setNombre(nombre);
        metodoPago.setDetalles(detalles);

        if (metodoPagoDAO.insertar(metodoPago)) {
            metodoPagoView.showMessage("Método de pago insertado con éxito.");
            metodoPagoView.cargarMetodosPago();
            metodoPagoView.limpiarCampos();
        } else {
            metodoPagoView.showMessage("Error al insertar el método de pago.");
        }
    }

    private void actualizarMetodoPago() {
        int codigo = metodoPagoView.getCodigo();
        String nombre = metodoPagoView.getNombre();
        String detalles = metodoPagoView.getDetalles();

        MetodoPago metodoPago = new MetodoPago(codigo, nombre, detalles);

        if (metodoPagoDAO.actualizar(metodoPago)) {
            metodoPagoView.showMessage("Método de pago actualizado con éxito.");
            metodoPagoView.cargarMetodosPago();
            metodoPagoView.limpiarCampos();
        } else {
            metodoPagoView.showMessage("Error al actualizar el método de pago.");
        }
    }

    private void eliminarMetodoPago() {
        int codigo = metodoPagoView.getCodigo();

        if (metodoPagoDAO.eliminar(codigo)) {
            metodoPagoView.showMessage("Método de pago eliminado con éxito.");
            metodoPagoView.cargarMetodosPago();
            metodoPagoView.limpiarCampos();
        } else {
            metodoPagoView.showMessage("Error al eliminar el método de pago.");
        }
    }
}
