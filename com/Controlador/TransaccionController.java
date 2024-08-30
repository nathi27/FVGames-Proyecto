package com.Controlador;

import com.Conexion.TransaccionDAO;
import com.Conexion.UsuarioDAO;
import com.Vista.TransaccionCRUDView;
import com.modelo.Transaccion;
import com.Modelo.Usuario;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TransaccionController {

    private TransaccionCRUDView transaccionView; //Referencia a la vista de CRUD de transacciones
    private TransaccionDAO transaccionDAO;// DAO para operaciones en la tabla de transaccion
    private UsuarioDAO usuarioDAO;// DAO para operaciones en la tabla de transacciones
    // Constructor que inicializa el controlador, los DAOs y la vista
    public TransaccionController(TransaccionCRUDView transaccionView) {
        this.transaccionView = transaccionView;
        this.transaccionDAO = new TransaccionDAO();
        this.usuarioDAO = new UsuarioDAO();
        initController();
    }

    private void initController() {
        // Cargar datos al iniciar
        transaccionView.cargarUsuarios();//carga los datos de los usuarios 
        transaccionView.cargarTransacciones();//carga los datos de las transacciones

        // Configurar los listeners
        transaccionView.setInsertarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarTransaccion();
            }
        });

        transaccionView.setActualizarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTransaccion();
            }
        });

        transaccionView.setEliminarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTransaccion();
            }
        });

        transaccionView.setLimpiarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transaccionView.limpiarCampos();
            }
        });

        addTableSelectionListener();
    }

    private void addTableSelectionListener() {
        transaccionView.getTableTransacciones().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && transaccionView.getTableTransacciones().getSelectedRow() != -1) {
                    int selectedRow = transaccionView.getTableTransacciones().getSelectedRow();
                    // Aseguramos que los índices coincidan con las columnas correctas
                    int codigo = Integer.parseInt(transaccionView.getTableTransacciones().getValueAt(selectedRow, 0).toString());
                    String clienteCedula = transaccionView.getTableTransacciones().getValueAt(selectedRow, 1).toString();
                    double monto = Double.parseDouble(transaccionView.getTableTransacciones().getValueAt(selectedRow, 2).toString());
                    String detalle = transaccionView.getTableTransacciones().getValueAt(selectedRow, 3).toString();

                    transaccionView.setCodigo(codigo);
                    transaccionView.setClienteCedula(clienteCedula);
                    transaccionView.setMonto(monto);
                    transaccionView.setDetalle(detalle);
                }
            }
        });
    }

    private void insertarTransaccion() {
        Transaccion transaccion = new Transaccion();
        transaccion.setClienteCedula(transaccionView.getClienteCedula());
        transaccion.setMonto(transaccionView.getMonto());
        transaccion.setDetalle(transaccionView.getDetalle());

        if (transaccionDAO.insertar(transaccion)) {
            transaccionView.showMessage("Transacción insertada con éxito.");
            transaccionView.cargarTransacciones();
            transaccionView.limpiarCampos();
        } else {
            transaccionView.showMessage("Error al insertar la transacción.");
        }
    }

    private void actualizarTransaccion() {
        int codigo = transaccionView.getCodigo();
        Transaccion transaccion = transaccionDAO.obtenerPorCodigo(codigo);

        if (transaccion != null) {
            transaccion.setClienteCedula(transaccionView.getClienteCedula());
            transaccion.setMonto(transaccionView.getMonto());
            transaccion.setDetalle(transaccionView.getDetalle());

            if (transaccionDAO.actualizar(transaccion)) {
                transaccionView.showMessage("Transacción actualizada con éxito.");
                transaccionView.cargarTransacciones();
                transaccionView.limpiarCampos();
            } else {
                transaccionView.showMessage("Error al actualizar la transacción.");
            }
        } else {
            transaccionView.showMessage("La transacción con código " + codigo + " no existe.");
        }
    }

    private void eliminarTransaccion() {
        int codigo = transaccionView.getCodigo();
        if (transaccionDAO.eliminar(codigo)) {
            transaccionView.showMessage("Transacción eliminada con éxito.");
            transaccionView.cargarTransacciones();
            transaccionView.limpiarCampos();
        } else {
            transaccionView.showMessage("Error al eliminar la transacción.");
        }
    }
}
