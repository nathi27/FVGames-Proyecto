package com.Sockets;//sirve para levantar o activar el programa para que esten todos esos clientes

import java.io.IOException;//se utiliza para manejar excepciones relacionadas con la entrada/salida.
import java.net.ServerSocket;//se usa para crear un servidor que escucha en un puerto específico.
import java.net.Socket;//representa la conexión de red entre el servidor y el cliente.


public class Server {
    //Define un puerto en el que el servidor escuchará las conexiones entrantes. 
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) { //El método accept() bloquea la ejecución hasta que un cliente se conecte.
                Socket socket = serverSocket.accept();//Acepta una conexión entrante de un cliente. 
                System.out.println("New client connected");
                
                // Crea un nuevo hilo para manejar el cliente
                ClientHandler clientHandler = new ClientHandler(socket);//Crea una instancia de ClientHandler, que es responsable de manejar la comunicación con el cliente.
                new Thread(clientHandler).start();//Crea un nuevo hilo para ejecutar el ClientHandler, permitiendo que el servidor continúe aceptando nuevas conexiones mientras maneja la comunicación con el cliente actual.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
