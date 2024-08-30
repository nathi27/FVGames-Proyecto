package com.main;

import com.Controlador.LoginController; //importa una clase del paquete controlador
import com.Vista.LoginView; //importa una clase del paquete vista

public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();//Se crea una instancia de la vista de inicio de sesión
        LoginController loginController = new LoginController(loginView);//Se pasa la instancia de LoginView al controlador, lo que le permite gestionar la vista y la lógica relacionada con el inicio de sesión.
        loginView.setVisible(true);//muestra la interfaz gráfica al usuario.
    }
}
