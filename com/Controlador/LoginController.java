package com.Controlador;

import com.Modelo.Usuario;
import com.Conexion.UsuarioDAO;
import com.Vista.LoginView;
import com.Vista.MenuPrincipalView;
import com.Vista.MenuClienteView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    private UsuarioDAO usuarioDAO;
    private LoginView loginView;
    private static Usuario usuarioLogueado;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.usuarioDAO = new UsuarioDAO();
        this.loginView.setLoginListener(new LoginListener());
        this.loginView.setInvitadoListener(new InvitadoListener());  // Listener para el botón de invitado
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cedula = loginView.getCedula();
            String password = loginView.getPassword();

            Usuario usuario = usuarioDAO.obtenerPorCedula(cedula);
            if (usuario != null) {
                // Validación de la contraseña
                if (usuario.getPassword().equals(password)) {
                    usuarioLogueado = usuario;
                    loginView.showMessage("Login exitoso. Bienvenido, " + usuario.getNombre());

                    // Ocultar la vista de login
                    loginView.setVisible(false);

                    // Verificar el permiso del usuario
                    if (usuario.getPermiso().equals("Funcionario")) {
                        // Abrir el menú principal para Funcionarios
                        MenuPrincipalView menuView = new MenuPrincipalView();
                        MenuPrincipalController menuController = new MenuPrincipalController(menuView);
                        menuView.setVisible(true);
                    } else {
                        // Abrir el menú cliente para Clientes
                        MenuClienteView menuClienteView = new MenuClienteView();
                        MenuClienteController menuClienteController = new MenuClienteController(menuClienteView);
                        menuClienteView.setVisible(true);
                    }

                } else {
                    loginView.showMessage("Contraseña incorrecta.");
                }
            } else {
                loginView.showMessage("Usuario no encontrado.");
            }
        }
    }

    // Nueva clase para manejar el acceso como invitado
    class InvitadoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loginView.showMessage("Accediendo como invitado.");

            // Establecer el usuario logueado en null
            usuarioLogueado = null;

            // Ocultar la vista de login
            loginView.setVisible(false);

            // Abrir el menú cliente para invitados (sin necesidad de cédula)
            MenuClienteView menuClienteView = new MenuClienteView();
            MenuClienteController menuClienteController = new MenuClienteController(menuClienteView);
            menuClienteView.setVisible(true);
        }
    }

    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }
}
