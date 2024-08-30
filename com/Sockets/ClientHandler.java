//este codigo maneja clientes, se encarga de procesar las solicitudes de los clientes y enviarles 
//respuestas adecuadas. 

package com.Sockets;

import com.Conexion.UsuarioDAO;//importa una clase del paquete conexion
import com.Modelo.Usuario;//importa una clase del paquete modelo
import java.io.*;//Importa clases relacionadas con la entrada/salida
import java.net.Socket;//e utiliza para la comunicación a través de redes.

public class ClientHandler implements Runnable {
    private Socket socket;// representa la conexión con el cliente.
    private BufferedReader in;//lee datos del socket.
    private PrintWriter out;//envia datos al cliente
    //inicializa el socket con la conexión del cliente.
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//leer datos del socket
            out = new PrintWriter(socket.getOutputStream(), true);//envia datos al cliente.

            String inputLine;

            // Lee las solicitudes del cliente y responde
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                String response = handleRequest(inputLine);//Procesa la solicitud del cliente y obtiene una respuesta.
                out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String handleRequest(String request) {//Procesa la solicitud del cliente
        // Aquí es donde se integra con los controladores que se han creado
        if (request.startsWith("LOGIN")) {//Verifica si la solicitud comienza con "LOGIN". Si es así, extrae los datos de inicio de sesión.
            // Ejemplo de manejo de login
            String[] parts = request.split(" ");//Divide la solicitud en partes usando el espacio como delimitador.
            String cedula = parts[1];
            String password = parts[2];
            return login(cedula, password);//autentica al usuario.
        }

        return "Unknown request";
    }
    // Verifica las credenciales del usuario.
    private String login(String cedula, String password) {
        // Integra con tu controlador de Login
        Usuario usuario = new UsuarioDAO().obtenerPorCedula(cedula);//Obtiene el usuario de la base de datos 
        if (usuario != null && usuario.getPassword().equals(password)) { //Verifica si el usuario existe y la contraseña es correcta.
            return "Login successful: " + usuario.getNombre();
        }
        return "Login failed";
    }
}
