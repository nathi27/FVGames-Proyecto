package com.Controlador;

import com.Vista.CarritoCompraCRUDView;
import com.Vista.CarritoCompraClienteCRUDView;
import com.Vista.LoginView;
import com.Vista.MenuPrincipalView;
import com.Vista.MetodoPagoCRUDView;
import com.Vista.PaqueteCRUDView;
import com.Vista.ProductoCRUDView;
import com.Vista.TransaccionCRUDView;
import com.Vista.UsuarioCRUDView;

public class MenuPrincipalController {

    private MenuPrincipalView menuView;

    public MenuPrincipalController(MenuPrincipalView menuView) {
        this.menuView = menuView;

        // Asignar listeners a los botones del menú
        this.menuView.setGestionUsuariosListener(e -> abrirGestionUsuarios());
        this.menuView.setGestionProductosListener(e -> abrirGestionProductos());
        this.menuView.setGestionPaquetesListener(e -> abrirGestionPaquetes());
        this.menuView.setGestionMetodosPagoListener(e -> abrirGestionMetodosPago());
        this.menuView.setGestionCarritosListener(e -> abrirGestionCarritos());
        this.menuView.setGestionTransaccionesListener(e -> abrirGestionTransacciones());
        this.menuView.setComprarListener(e -> abrirComprar());
        this.menuView.setSalirListener(e -> salir());
    }

    private void abrirGestionUsuarios() {
        UsuarioCRUDView usuarioView = new UsuarioCRUDView();
        UsuarioController usuarioController = new UsuarioController(usuarioView);
        usuarioView.setVisible(true);
    }

   private void abrirGestionProductos() {
        // Implementa la lógica para abrir la vista de gestión de productos
        ProductoCRUDView productoView = new ProductoCRUDView();
        ProductoController productoController = new ProductoController(productoView);
        productoView.setVisible(true);
    }

    private void abrirGestionPaquetes() {
        // Implementa la lógica para abrir la vista de gestión de paquetes
        PaqueteCRUDView paqueteView = new PaqueteCRUDView();
        PaqueteController paqueteController = new PaqueteController(paqueteView);
        paqueteView.setVisible(true);
    }

    private void abrirGestionMetodosPago() {
        // Implementa la lógica para abrir la vista de gestión de métodos de pago
        MetodoPagoCRUDView metodoPagoView = new MetodoPagoCRUDView();
        MetodoPagoController metodoPagoController = new MetodoPagoController(metodoPagoView);
        metodoPagoView.setVisible(true);
    }

    private void abrirGestionCarritos() {
        // Implementa la lógica para abrir la vista de gestión de carritos de compras
        CarritoCompraCRUDView carritoView = new CarritoCompraCRUDView();
        CarritoCompraController carritoController = new CarritoCompraController(carritoView);
        carritoView.setVisible(true);
    }

    private void abrirGestionTransacciones() {
        TransaccionCRUDView transaccionView = new TransaccionCRUDView();
        TransaccionController transaccionController = new TransaccionController(transaccionView);
        transaccionView.setVisible(true);
    }
    
    private void abrirComprar() {
        String cedula = LoginController.getUsuarioLogueado().getCedula();
        
        CarritoCompraClienteCRUDView comprarView = new CarritoCompraClienteCRUDView();
        CarritoCompraClienteController transaccionController = new CarritoCompraClienteController(comprarView, cedula);
        comprarView.setVisible(true);
    }

    private void salir() {
        System.exit(0);
    }
}
