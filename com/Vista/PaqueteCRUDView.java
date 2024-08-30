package com.Vista;

import com.Conexion.PaqueteDAO;
import com.Conexion.PaqueteProductoDAO;
import com.Conexion.ProductoDAO;
import com.Modelo.Producto;
import com.modelo.Paquete;
import com.modelo.PaqueteProducto;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class PaqueteCRUDView extends javax.swing.JFrame {

    public PaqueteCRUDView() {
        initComponents();
        addTableSelectionListener();
        addProductSelectionListener();
        cargarProductos();
        cargarPaquetes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePaquetes = new javax.swing.JTable();
        btnLimpiar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Código");

        jLabel2.setText("Nombre");

        jLabel3.setText("Descuento");

        jLabel5.setText("Precio");

        txtPrecio.setEditable(false);

        tablePaquetes.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"Código", "Nombre", "Descuento","Precio"}
        ));
        jScrollPane1.setViewportView(tablePaquetes);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");

        btnActualizar.setText("Actualizar");

        btnInsertar.setText("Insertar");

        tableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Código", "Nombre", "Categoría", "Cantidad", "Precio"}
        ));
        jScrollPane3.setViewportView(tableProductos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addComponent(txtDescuento)
                                .addComponent(jLabel2)
                                .addComponent(txtNombre)
                                .addComponent(jLabel1)
                                .addComponent(txtCodigo))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInsertar)
                            .addComponent(btnActualizar)
                            .addComponent(btnEliminar)
                            .addComponent(btnLimpiar))))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed


    private void addTableSelectionListener() {
        tablePaquetes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && tablePaquetes.getSelectedRow() != -1) {
                    int selectedRow = tablePaquetes.getSelectedRow();
                    txtCodigo.setText(tablePaquetes.getValueAt(selectedRow, 0).toString());
                    txtNombre.setText(tablePaquetes.getValueAt(selectedRow, 1).toString());
                    txtDescuento.setText(tablePaquetes.getValueAt(selectedRow, 2).toString());
                    txtPrecio.setText(tablePaquetes.getValueAt(selectedRow, 3).toString());

                    cargarProductosSeleccionados(Integer.parseInt(txtCodigo.getText()));
                }
            }
        });
    }

    private void addProductSelectionListener() {
        tableProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    recalcularPrecioTotal();
                }
            }
        });

        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recalcularPrecioTotal();
            }
        });
    }

    private void cargarProductosSeleccionados(int paqueteId) {
        PaqueteProductoDAO paqueteProductoDAO = new PaqueteProductoDAO();
        List<PaqueteProducto> productosAsociados = paqueteProductoDAO.obtenerPorPaqueteId(paqueteId);

        tableProductos.clearSelection();
        DefaultTableModel model = (DefaultTableModel) tableProductos.getModel();

        for (PaqueteProducto paqueteProducto : productosAsociados) {
            int productoId = paqueteProducto.getProductoId();

            for (int i = 0; i < model.getRowCount(); i++) {
                int codigoProducto = Integer.parseInt(model.getValueAt(i, 0).toString());
                if (codigoProducto == productoId) {
                    tableProductos.addRowSelectionInterval(i, i);
                    break;
                }
            }
        }
    }

    public void cargarProductos() {
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.obtenerTodos();

        DefaultTableModel model = (DefaultTableModel) tableProductos.getModel();
        model.setRowCount(0);
        for (Producto producto : productos) {
            model.addRow(new Object[]{
                producto.getCodigo(),
                producto.getNombre(),
                producto.getCategoria(),
                producto.getCantidad(),
                producto.getPrecio()
            });
        }
    }

    public void cargarPaquetes() {
        PaqueteDAO paqueteDAO = new PaqueteDAO();
        List<Paquete> paquetes = paqueteDAO.obtenerTodos();

        DefaultTableModel model = (DefaultTableModel) tablePaquetes.getModel();
        model.setRowCount(0);
        for (Paquete paquete : paquetes) {
            model.addRow(new Object[]{
                paquete.getCodigo(),
                paquete.getNombre(),
                paquete.getDescuento(),
                paquete.getPrecio()
            });
        }
    }

    private void recalcularPrecioTotal() {
        double descuento = Double.parseDouble(txtDescuento.getText());
        int[] selectedRows = tableProductos.getSelectedRows();
        double total = 0.0;

        DefaultTableModel model = (DefaultTableModel) tableProductos.getModel();

        for (int row : selectedRows) {
            double precioProducto = Double.parseDouble(model.getValueAt(row, 4).toString());
            total += precioProducto;
        }

        total = total * (1 - descuento / 100);
        txtPrecio.setText(String.valueOf(total));
    }

    // Métodos para obtener los datos de los campos
    public int getCodigo() {
        return Integer.parseInt(txtCodigo.getText());
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public double getDescuento() {
        return Double.parseDouble(txtDescuento.getText());
    }

    public double getPrecio() {
        return Double.parseDouble(txtPrecio.getText());
    }

    public List<String> getProductosSeleccionados() {
        int[] selectedRows = tableProductos.getSelectedRows();
        List<String> productosSeleccionados = new ArrayList<>();
        for (int row : selectedRows) {
            productosSeleccionados.add(tableProductos.getValueAt(row, 1).toString());
        }
        return productosSeleccionados;
    }

    // Métodos para establecer los datos en los campos
    public void setCodigo(int codigo) {
        txtCodigo.setText(String.valueOf(codigo));
    }

    public void setNombre(String nombre) {
        txtNombre.setText(nombre);
    }

    public void setDescuento(double descuento) {
        txtDescuento.setText(String.valueOf(descuento));
    }

    public void setPrecio(double precio) {
        txtPrecio.setText(String.valueOf(precio));
    }

    public void setProductosSeleccionados(List<PaqueteProducto> productosAsociados) {
        tableProductos.clearSelection();

        DefaultTableModel model = (DefaultTableModel) tableProductos.getModel();

        for (PaqueteProducto paqueteProducto : productosAsociados) {
            int productoId = paqueteProducto.getProductoId();

            for (int i = 0; i < model.getRowCount(); i++) {
                int codigoProducto = Integer.parseInt(model.getValueAt(i, 0).toString());
                if (codigoProducto == productoId) {
                    tableProductos.addRowSelectionInterval(i, i);
                    break;
                }
            }
        }
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
        txtDescuento.setText("15"); // Restablece el descuento por defecto
        txtPrecio.setText("");
    }

    public JTable getTablePaquetes() {
        return tablePaquetes;
    }

    public JTable getTableProductos() {
        return tableProductos;
    }

    // Clase para hacer que la tabla no sea editable
    class NonEditableTableModel extends DefaultTableModel {

        public NonEditableTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablePaquetes;
    private javax.swing.JTable tableProductos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
