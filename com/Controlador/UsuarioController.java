package com.Controlador;

import com.Conexion.UsuarioDAO;
import com.Conexion.MetodoPagoDAO;
import com.Modelo.Usuario;
import com.Modelo.MetodoPago;
import com.Vista.UsuarioCRUDView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UsuarioController {

    private UsuarioCRUDView vista;// Referencia a la vista de CRUD de usuarios
    private UsuarioDAO usuarioDAO; // DAO para operaciones en la tabla de usuarios
    private MetodoPagoDAO metodoPagoDAO; // DAO para operaciones en la tabla de métodos de pago
    
     // Constructor que inicializa el controlador, los DAOs y la vista
    public UsuarioController(UsuarioCRUDView vista) {
        this.vista = vista;
        this.usuarioDAO = new UsuarioDAO();
        this.metodoPagoDAO = new MetodoPagoDAO();
        inicializarVista();// Cargar datos en la tabla al iniciar la vista
        inicializarControladores();// Configurar los listeners de los botones
    }
    // Método para inicializar la vista cargando los datos en la tabla
    private void inicializarVista() {
        cargarDatosEnTabla();// Llama a la función para cargar los datos de la BD en la tabla
        vista.setVisible(true);// Hace visible la vista
    }
    // Método para inicializar los controladores (listeners) de los botones en la vista
    private void inicializarControladores() {
        // Listener para el botón de insertar un usuario
        vista.setInsertarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarUsuario();// Llama a la función para insertar un nuevo usuario
            }
        });
        // Listener para el botón de actualizar un usuario
        vista.setActualizarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();// Llama a la función para actualizar un usuario existente
            }
        });
        // Listener para el botón de eliminar un usuario
        vista.setEliminarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();// Llama a la función para eliminar un usuario
            }
        });
        // Listener para el botón de limpiar los campos del formulario
        vista.setLimpiarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.limpiarCampos();// Llama a la función para limpiar los campos de entrada
            }
        });
    }
    // Método para cargar los datos de los usuarios en la tabla de la vista
    private void cargarDatosEnTabla() {
        List<Usuario> usuarios = usuarioDAO.obtenerTodos();// Obtener todos los usuarios de la BD
        DefaultTableModel modeloTabla = new DefaultTableModel();// Modelo de la tabla para mostrar los datos
        // Añadir columnas al modelo de la tabla
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Dinero en Cuenta");
        modeloTabla.addColumn("Permiso");
        modeloTabla.addColumn("Método de Pago Preferido");
        
        // Iterar sobre la lista de usuarios y añadir cada uno al modelo de la tabla
        for (Usuario usuario : usuarios) {
            Object[] fila = new Object[8];// Array para almacenar los datos de una fila
            fila[0] = usuario.getCedula();
            fila[1] = usuario.getNombre();
            fila[2] = usuario.getApellidos();
            fila[3] = usuario.getDireccion();
            fila[4] = usuario.getEmail();
            fila[5] = usuario.getDineroCuenta();
            fila[6] = usuario.getPermiso();
            fila[7] = usuario.getMetodoPagoPreferido();// Obtiene el nombre del método de pago
            modeloTabla.addRow(fila); // Añade la fila al modelo de la tabla
        }

        vista.setTableData(modeloTabla);
    }
     // Método para insertar un nuevo usuario en la base de datos
    private void insertarUsuario() {
        String password = vista.getPassword();// Obtiene la contraseña desde la vista

        if (password == null || password.isEmpty()) { // Verifica que la contraseña no esté vacía
            vista.showMessage("La contraseña no puede estar vacía."); // Muestra un mensaje de error
            return;
        }
        // Crea un nuevo objeto Usuario con los datos ingresados en la vista
        Usuario usuario = new Usuario(
                vista.getCedula(),
                vista.getNombre(),
                vista.getApellidos(),
                vista.getDireccion(),
                vista.getEmail(),
                password,
                vista.getDineroCuenta(),
                vista.getPermiso(),
                obtenerMetodoPagoID(vista.getMetodoPago())// Obtiene el ID del método de pago preferido
        );
         // Inserta el usuario en la base de datos
        if (usuarioDAO.insertar(usuario)) {
            vista.showMessage("Usuario insertado exitosamente.");// Muestra mensaje de éxito
            cargarDatosEnTabla(); // Recarga los datos en la tabla
            vista.limpiarCampos();// Limpia los campos de entrada
        } else {
            vista.showMessage("Error al insertar el usuario.");// Muestra mensaje de error
        }
    }
    // Método para actualizar los datos de un usuario existente en la base de datos
    private void actualizarUsuario() {
        String password = vista.getPassword(); // Obtiene la contraseña desde la vista

        if (password == null || password.isEmpty()) {// Verifica que la contraseña no esté vacía
            vista.showMessage("La contraseña no puede estar vacía.");// Muestra un mensaje de error
            return;
        }
        // Crea un objeto Usuario con los datos actualizados desde la vista
        Usuario usuario = new Usuario(
                vista.getCedula(),
                vista.getNombre(),
                vista.getApellidos(),
                vista.getDireccion(),
                vista.getEmail(),
                password,
                vista.getDineroCuenta(),
                vista.getPermiso(),
                obtenerMetodoPagoID(vista.getMetodoPago())// Obtiene el ID del método de pago preferido
        );
         // Actualiza los datos del usuario en la base de datos
        if (usuarioDAO.actualizar(usuario)) {
            vista.showMessage("Usuario actualizado exitosamente.");// Muestra mensaje de éxito
            cargarDatosEnTabla();// Recarga los datos en la tabla
            vista.limpiarCampos();// Limpia los campos de entrada
        } else {
            vista.showMessage("Error al actualizar el usuario.");// Muestra mensaje de error
        }
    }
    // Método para eliminar un usuario de la base de datos
    private void eliminarUsuario() {
        String cedula = vista.getCedula();// Obtiene la cédula del usuario desde la vista
        // Elimina el usuario de la base de datos usando la cédula como clave
        if (usuarioDAO.eliminar(cedula)) {
            vista.showMessage("Usuario eliminado exitosamente.");
            cargarDatosEnTabla();
            vista.limpiarCampos();
        } else {
            vista.showMessage("Error al eliminar el usuario.");
        }
    }
    // Método para obtener el ID del método de pago basado en su nombre
    private int obtenerMetodoPagoID(String nombreMetodoPago) {
        MetodoPago metodoPago = metodoPagoDAO.obtenerPorNombre(nombreMetodoPago);// Busca el método de pago por su nombre
        return metodoPago != null ? metodoPago.getCodigo() : -1; // Retorna -1 si no encuentra el método de pago
    }
}
