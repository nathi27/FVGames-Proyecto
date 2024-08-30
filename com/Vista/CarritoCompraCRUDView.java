package com.Vista;

import com.Conexion.CarritoCompraDAO;
import com.Conexion.MetodoPagoDAO;
import com.Conexion.ProductoDAO;
import com.Conexion.UsuarioDAO;
import com.Modelo.MetodoPago;
import com.Modelo.Producto;
import com.Modelo.Usuario;
import com.modelo.CarritoCompra;

import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CarritoCompraCRUDView extends javax.swing.JFrame {
    // Declarar e inicializar el formato de fecha
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    
    public CarritoCompraCRUDView() {
        initComponents();
        cargarClientes();
        cargarProductos();
        cargarMetodosPago();
        cargarCarritos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEliminar = new javax.swing.JButton();
        txtSubtotal = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCarritos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btnInsertar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        comboBoxClientes = new javax.swing.JComboBox<>();
        comboBoxMetodoPago = new javax.swing.JComboBox<>();
        comboBoxProductos = new javax.swing.JComboBox<>();
        comboBoxEstado = new javax.swing.JComboBox<>();
        txtDetalle = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnEliminarProducto = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnAgregarProducto = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCedulaUsuario = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtFFechaInicio = new javax.swing.JTextField();
        txtFechaFinal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnFiltrarPorUsuario = new javax.swing.JButton();
        btnFiltrarPorFecha = new javax.swing.JButton();
        btnTodasCompras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnEliminar.setText("Eliminar");

        txtSubtotal.setEditable(false);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel2.setText("Cliente");

        jLabel8.setText("Subtotal");

        jLabel4.setText("Estado");

        tableCarritos.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"Código", "Cliente", "Fecha", "Estado", "Método de Pago", "Subtotal", "Total"}
        ));
        jScrollPane1.setViewportView(tableCarritos);

        jLabel5.setText("Método Pago");

        btnInsertar.setText("Insertar");

        jLabel6.setText("Detalle");

        btnActualizar.setText("Actualizar");

        jLabel1.setText("Productos");

        jLabel7.setText("Total");

        comboBoxClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin Pagar", "Pagado" }));

        tableProductos.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"Producto", "Cantidad"}
        ));
        jScrollPane2.setViewportView(tableProductos);

        jLabel3.setText("Código");

        jLabel9.setText("Búsqueda");

        btnEliminarProducto.setText("Eliminar Producto");
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        txtTotal.setEditable(false);

        jLabel10.setText("Cant");

        btnAgregarProducto.setText("Agregar Producto");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        jLabel11.setText("Carritos");

        jLabel12.setText("Cédula");

        jLabel13.setText("Fecha Inicio");

        jLabel14.setText("Fecha final");

        btnFiltrarPorUsuario.setText("Filtrar Usuario");

        btnFiltrarPorFecha.setText("Filtrar Fecha");

        btnTodasCompras.setText("Todo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDetalle, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, 366, Short.MAX_VALUE)
                                    .addComponent(comboBoxMetodoPago, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxClientes, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar)
                                .addGap(38, 38, 38))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(comboBoxProductos, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnFiltrarPorUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCedulaUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(txtFFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnFiltrarPorFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnTodasCompras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(764, Short.MAX_VALUE)
                    .addComponent(jLabel11)
                    .addGap(545, 545, 545)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(218, 218, 218)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboBoxProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnEliminarProducto)
                                    .addComponent(btnAgregarProducto)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCedulaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnFiltrarPorUsuario)
                                    .addComponent(btnFiltrarPorFecha))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTodasCompras))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4)
                        .addComponent(comboBoxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5)
                        .addGap(4, 4, 4)
                        .addComponent(comboBoxMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInsertar)
                            .addComponent(btnActualizar)
                            .addComponent(btnEliminar)
                            .addComponent(btnLimpiar))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(33, 33, 33))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(jLabel11)
                    .addContainerGap(662, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    // Métodos para cargar datos
    public void cargarClientes() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.obtenerTodos();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Usuario usuario : usuarios) {
            model.addElement(usuario.getCedula() + " - " + usuario.getNombre());
        }
        comboBoxClientes.setModel(model);
    }

    public void cargarProductos() {
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.obtenerTodos();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Producto producto : productos) {
            model.addElement(producto.getCodigo() + " - " + producto.getNombre());
        }
        comboBoxProductos.setModel(model);
    }

    public void cargarMetodosPago() {
        MetodoPagoDAO metodoPagoDAO = new MetodoPagoDAO();
        List<MetodoPago> metodosPago = metodoPagoDAO.obtenerTodos();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (MetodoPago metodoPago : metodosPago) {
            model.addElement(metodoPago.getCodigo() + " - " + metodoPago.getNombre());
        }
        comboBoxMetodoPago.setModel(model);
    }

    public void cargarCarritos() {
        CarritoCompraDAO carritoDAO = new CarritoCompraDAO();
        List<CarritoCompra> carritos = carritoDAO.obtenerTodos();

        DefaultTableModel model = (DefaultTableModel) tableCarritos.getModel();
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

    // Métodos para obtener los datos de los campos de filtrado
    public String getCedulaUsuario() {
        return txtCedulaUsuario.getText();
    }

    public Timestamp getFechaInicio() {
        return convertirATimestamp(txtFFechaInicio.getText());
    }

    public Timestamp getFechaFin() {
        return convertirATimestamp(txtFechaFinal.getText());
    }

    // Método para convertir una cadena a Timestamp con validación
    private Timestamp convertirATimestamp(String fechaStr) {
        try {
            return new Timestamp(dateFormat.parse(fechaStr).getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use 'yyyy-MM-dd HH:mm:ss'", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Métodos para agregar los listeners de filtrado
    public void setFiltrarPorUsuarioListener(ActionListener actionListener) {
        btnFiltrarPorUsuario.addActionListener(actionListener);
    }

    public void setFiltrarPorFechaListener(ActionListener actionListener) {
        btnFiltrarPorFecha.addActionListener(actionListener);
    }

    public void setTodasComprasListener(ActionListener actionListener) {
        btnTodasCompras.addActionListener(actionListener);
    }

    // Métodos para obtener los datos de los campos
    public int getCodigo() {
        return Integer.parseInt(txtCodigo.getText());
    }

    public String getClienteCedula() {
        String selectedUsuario = (String) comboBoxClientes.getSelectedItem();
        return selectedUsuario != null ? selectedUsuario.split(" - ")[0] : "";
    }

    public String getProductoSeleccionado() {
        String selectedProducto = (String) comboBoxProductos.getSelectedItem();
        return selectedProducto != null ? selectedProducto.split(" - ")[0] : "";
    }

    public int getCantidad() {
        return Integer.parseInt(txtCantidad.getText());
    }

    public String getEstado() {
        return (String) comboBoxEstado.getSelectedItem();
    }

    public int getMetodoPago() {
        String selectedMetodoPago = (String) comboBoxMetodoPago.getSelectedItem();
        return selectedMetodoPago != null ? Integer.parseInt(selectedMetodoPago.split(" - ")[0]) : -1;
    }

    public String getDetalle() {
        return txtDetalle.getText();
    }

    public double getSubtotal() {
        return Double.parseDouble(txtSubtotal.getText());
    }

    public double getTotal() {
        return Double.parseDouble(txtTotal.getText());
    }

    public void setCodigo(int codigo) {
        txtCodigo.setText(String.valueOf(codigo));
    }

    public void setClienteCedula(String clienteCedula) {
        for (int i = 0; i < comboBoxClientes.getItemCount(); i++) {
            if (comboBoxClientes.getItemAt(i).startsWith(clienteCedula + " - ")) {
                comboBoxClientes.setSelectedIndex(i);
                break;
            }
        }
    }

    public void setEstado(String estado) {
        comboBoxEstado.setSelectedItem(estado);
    }

    public void setMetodoPago(int metodoPago) {
        for (int i = 0; i < comboBoxMetodoPago.getItemCount(); i++) {
            if (comboBoxMetodoPago.getItemAt(i).startsWith(metodoPago + " - ")) {
                comboBoxMetodoPago.setSelectedIndex(i);
                break;
            }
        }
    }

    public void setDetalle(String detalle) {
        txtDetalle.setText(detalle);
    }

    public void setSubtotal(double subtotal) {
        txtSubtotal.setText(String.valueOf(subtotal));
    }

    public void setTotal(double total) {
        txtTotal.setText(String.valueOf(total));
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

    public void setAgregarProductoListener(ActionListener actionListener) {
        btnAgregarProducto.addActionListener(actionListener);  // Asociar correctamente al botón agregar
    }

    public void setEliminarProductoListener(ActionListener actionListener) {
        btnEliminarProducto.addActionListener(actionListener);  // Asociar correctamente al botón eliminar
    }

    public JTable getTableCarritos() {
        return tableCarritos;
    }

    public JTable getTableProductos() {
        return tableProductos;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        comboBoxClientes.setSelectedIndex(-1);
        comboBoxProductos.setSelectedIndex(-1);
        comboBoxEstado.setSelectedIndex(-1);
        comboBoxMetodoPago.setSelectedIndex(-1);
        txtDetalle.setText("");
        txtSubtotal.setText("");
        txtTotal.setText(""); // Limpiar el campo de total
        txtCantidad.setText(""); // Limpiar el campo de cantidad

        DefaultTableModel model = (DefaultTableModel) tableProductos.getModel();
        model.setRowCount(0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnFiltrarPorFecha;
    private javax.swing.JButton btnFiltrarPorUsuario;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnTodasCompras;
    private javax.swing.JComboBox<String> comboBoxClientes;
    private javax.swing.JComboBox<String> comboBoxEstado;
    private javax.swing.JComboBox<String> comboBoxMetodoPago;
    private javax.swing.JComboBox<String> comboBoxProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableCarritos;
    private javax.swing.JTable tableProductos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCedulaUsuario;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDetalle;
    private javax.swing.JTextField txtFFechaInicio;
    private javax.swing.JTextField txtFechaFinal;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
