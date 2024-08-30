package com.Vista;

import com.Conexion.MetodoPagoDAO;
import com.Modelo.MetodoPago;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class MetodoPagoCRUDView extends javax.swing.JFrame {

    public MetodoPagoCRUDView() {
        initComponents();
        addTableSelectionListener();
        cargarMetodosPago();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMetodosPago = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDetalles = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnActualizar.setText("Actualizar");

        tableMetodosPago.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Código", "Nombre", "Detalles"}));
    jScrollPane1.setViewportView(tableMetodosPago);

    jLabel1.setText("Código");

    btnEliminar.setText("Eliminar");

    btnLimpiar.setText("Limpiar");
    btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnLimpiarActionPerformed(evt);
        }
    });

    btnInsertar.setText("Insertar");

    jLabel2.setText("Nombre");

    jLabel3.setText("Detalles");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(19, 19, 19)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(24, 24, 24))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(txtDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addComponent(txtCodigo))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(22, 22, 22))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(18, 18, 18)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnInsertar)
                        .addComponent(btnActualizar)
                        .addComponent(btnEliminar)
                        .addComponent(btnLimpiar)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(24, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed


    private void addTableSelectionListener() {
        tableMetodosPago.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && tableMetodosPago.getSelectedRow() != -1) {
                    int selectedRow = tableMetodosPago.getSelectedRow();
                    txtCodigo.setText(tableMetodosPago.getValueAt(selectedRow, 0).toString());
                    txtNombre.setText(tableMetodosPago.getValueAt(selectedRow, 1).toString());
                    txtDetalles.setText(tableMetodosPago.getValueAt(selectedRow, 2).toString());
                }
            }
        });
    }

    public void cargarMetodosPago() {
        MetodoPagoDAO metodoPagoDAO = new MetodoPagoDAO();
        List<MetodoPago> metodosPago = metodoPagoDAO.obtenerTodos();

        DefaultTableModel model = (DefaultTableModel) tableMetodosPago.getModel();
        model.setRowCount(0);
        for (MetodoPago metodoPago : metodosPago) {
            model.addRow(new Object[]{
                metodoPago.getCodigo(),
                metodoPago.getNombre(),
                metodoPago.getDetalles()
            });
        }
    }

    // Métodos para obtener los datos de los campos
    public int getCodigo() {
        return Integer.parseInt(txtCodigo.getText());
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getDetalles() {
        return txtDetalles.getText();
    }

    // Métodos para establecer los datos en los campos
    public void setCodigo(int codigo) {
        txtCodigo.setText(String.valueOf(codigo));
    }

    public void setNombre(String nombre) {
        txtNombre.setText(nombre);
    }

    public void setDetalles(String detalles) {
        txtDetalles.setText(detalles);
    }

    // Métodos para agregar los listeners
    public void setInsertarListener(ActionListener actionListener) {
        btnInsertar.addActionListener(actionListener);
    }

    public void setActualizarListener(ActionListener actionListener) {
        btnActualizar.addActionListener(actionListener);
    }

    public void setEliminarListener(ActionListener actionListener) {
        btnEliminar.addActionListener(actionListener);
    }

    public void setLimpiarListener(ActionListener actionListener) {
        btnLimpiar.addActionListener(actionListener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDetalles.setText("");
    }

    public JTable getTableMetodosPago() {
        return tableMetodosPago;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableMetodosPago;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDetalles;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
