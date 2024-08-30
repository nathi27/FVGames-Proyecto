package com.Controlador;

import com.Vista.CarritoCompraClienteCRUDView;
import com.Vista.MenuClienteView;

public class MenuClienteController {

    private MenuClienteView menuView;

    public MenuClienteController(MenuClienteView menuView) {
        this.menuView = menuView;

        // Asignar listeners a los botones del menú
        this.menuView.setComprarListener(e -> abrirComprar());
        this.menuView.setSalirListener(e -> salir());
    }

    private void abrirComprar() {
        String cedula;
        
        if (LoginController.getUsuarioLogueado() == null) {
            // Si el usuario es null, asignamos un valor genérico o manejamos como invitado
            cedula = null;
        } else {
            // Si el usuario está logueado, tomamos su cédula
            cedula = LoginController.getUsuarioLogueado().getCedula();
        }
        
        CarritoCompraClienteCRUDView comprarView = new CarritoCompraClienteCRUDView();
        CarritoCompraClienteController transaccionController = new CarritoCompraClienteController(comprarView, cedula);
        comprarView.setVisible(true);
    }

    private void salir() {
        System.exit(0);
    }
}
