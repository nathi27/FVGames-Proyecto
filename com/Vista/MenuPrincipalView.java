package com.Vista;

import java.awt.event.ActionListener;

public class MenuPrincipalView extends javax.swing.JFrame {

    public MenuPrincipalView() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGestionProductos = new javax.swing.JButton();
        btnGestionUsuarios = new javax.swing.JButton();
        btnGestionPaquetes = new javax.swing.JButton();
        btnGestionMetodosPago = new javax.swing.JButton();
        btnGestionCarritos = new javax.swing.JButton();
        btnGestionTransacciones = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnComprar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGestionProductos.setText("Productos");
        btnGestionProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionProductosActionPerformed(evt);
            }
        });

        btnGestionUsuarios.setText("Usuarios");

        btnGestionPaquetes.setText("Paquetes");

        btnGestionMetodosPago.setText("Métodos Pago");

        btnGestionCarritos.setText("Carritos");

        btnGestionTransacciones.setText("Transacciones");

        btnSalir.setText("Salir");

        btnComprar.setText("Comprar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnGestionPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnGestionTransacciones, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnGestionUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnGestionMetodosPago, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnGestionProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnGestionCarritos, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGestionUsuarios)
                    .addComponent(btnGestionMetodosPago))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGestionProductos)
                    .addComponent(btnGestionCarritos))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGestionPaquetes)
                    .addComponent(btnGestionTransacciones))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnComprar)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGestionProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGestionProductosActionPerformed

    /// Métodos para agregar los listeners
    public void setGestionUsuariosListener(ActionListener listener) {
        btnGestionUsuarios.addActionListener(listener);
    }

    public void setGestionProductosListener(ActionListener listener) {
        btnGestionProductos.addActionListener(listener);
    }

    public void setGestionPaquetesListener(ActionListener listener) {
        btnGestionPaquetes.addActionListener(listener);
    }

    public void setGestionMetodosPagoListener(ActionListener listener) {
        btnGestionMetodosPago.addActionListener(listener);
    }

    public void setGestionCarritosListener(ActionListener listener) {
        btnGestionCarritos.addActionListener(listener);
    }

    public void setGestionTransaccionesListener(ActionListener listener) {
        btnGestionTransacciones.addActionListener(listener);
    }
    
    public void setComprarListener(ActionListener listener) {
        btnComprar.addActionListener(listener);
    }

    public void setSalirListener(ActionListener listener) {
        btnSalir.addActionListener(listener);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnGestionCarritos;
    private javax.swing.JButton btnGestionMetodosPago;
    private javax.swing.JButton btnGestionPaquetes;
    private javax.swing.JButton btnGestionProductos;
    private javax.swing.JButton btnGestionTransacciones;
    private javax.swing.JButton btnGestionUsuarios;
    private javax.swing.JButton btnSalir;
    // End of variables declaration//GEN-END:variables
}
