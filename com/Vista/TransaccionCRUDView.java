package com.Vista;

import com.Conexion.TransaccionDAO;
import com.modelo.Transaccion;
import com.Conexion.UsuarioDAO;
import com.Modelo.Usuario;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TransaccionCRUDView extends javax.swing.JFrame {

    public TransaccionCRUDView() {
        initComponents();
        cargarUsuarios();
        cargarTransacciones();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTransacciones = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();
        comboBoxUsuarios = new javax.swing.JComboBox<>();
        txtDetalle = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Usuario");

        btnActualizar.setText("Actualizar");

        jLabel3.setText("Monto");

        tableTransacciones.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Código", "Cliente", "Monto", "Detalle"}));
    jScrollPane1.setViewportView(tableTransacciones);

    jLabel1.setText("Código");

    btnEliminar.setText("Eliminar");

    btnLimpiar.setText("Limpiar");
    btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnLimpiarActionPerformed(evt);
        }
    });

    btnInsertar.setText("Insertar");

    comboBoxUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

    jLabel4.setText("Detalle");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(19, 19, 19)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(btnInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(txtDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(txtMonto, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo)
                    .addComponent(comboBoxUsuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jLabel4))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
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
                    .addComponent(comboBoxUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnInsertar)
                        .addComponent(btnActualizar)
                        .addComponent(btnEliminar)
                        .addComponent(btnLimpiar)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(26, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    public void cargarUsuarios() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.obtenerTodos();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Usuario usuario : usuarios) {
            model.addElement(usuario.getCedula() + " - " + usuario.getNombre());
        }
        comboBoxUsuarios.setModel(model);
    }

    public void cargarTransacciones() {
        TransaccionDAO transaccionDAO = new TransaccionDAO();
        List<Transaccion> transacciones = transaccionDAO.obtenerTodas();

        DefaultTableModel model = (DefaultTableModel) tableTransacciones.getModel();
        model.setRowCount(0);
        for (Transaccion transaccion : transacciones) {
            model.addRow(new Object[]{
                transaccion.getCodigo(),
                transaccion.getClienteCedula(),
                transaccion.getMonto(),
                transaccion.getDetalle()
            });
        }
    }

    // Métodos para obtener los datos de los campos
    public int getCodigo() {
        return Integer.parseInt(txtCodigo.getText());
    }

    public String getClienteCedula() {
        String selectedUsuario = (String) comboBoxUsuarios.getSelectedItem();
        return selectedUsuario != null ? selectedUsuario.split(" - ")[0] : "";
    }

    public double getMonto() {
        return Double.parseDouble(txtMonto.getText());
    }

    public String getDetalle() {
        return txtDetalle.getText();
    }

    // Métodos para establecer los datos en los campos
    public void setCodigo(int codigo) {
        txtCodigo.setText(String.valueOf(codigo));
    }

    public void setClienteCedula(String clienteCedula) {
        for (int i = 0; i < comboBoxUsuarios.getItemCount(); i++) {
            if (comboBoxUsuarios.getItemAt(i).startsWith(clienteCedula + " - ")) {
                comboBoxUsuarios.setSelectedIndex(i);
                break;
            }
        }
    }

    public void setMonto(double monto) {
        txtMonto.setText(String.valueOf(monto));
    }

    public void setDetalle(String detalle) {
        txtDetalle.setText(detalle);
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
        comboBoxUsuarios.setSelectedIndex(-1);
        txtMonto.setText("");
        txtDetalle.setText("");
    }

    public javax.swing.JTable getTableTransacciones() {
        return tableTransacciones;
    }

    public javax.swing.JComboBox<String> getComboBoxUsuarios() {
        return comboBoxUsuarios;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> comboBoxUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableTransacciones;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDetalle;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
