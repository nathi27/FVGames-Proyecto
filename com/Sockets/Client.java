package com.Sockets; //crea el nuevo cliente para que se pueda unir al sistema

import java.io.*;//Importa todas las clases relacionadas con la entrada/salida
import java.net.Socket;//se utiliza para la comunicación a través de redes.

public class Client {
    private Socket socket;//se utilizará para la conexión con el servidor.
    private BufferedReader in;//lee datos de la entrada del socket.
    private PrintWriter out;//envia datos a través del socket.

    public Client(String serverAddress, int port) throws IOException {//crea una instancia del cliente, conectándose al servidor en la dirección y puerto especificados.
        socket = new Socket(serverAddress, port);//Establece una conexión con el servidor en la dirección y puerto proporcionados.
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));// lee datos del socket.
        out = new PrintWriter(socket.getOutputStream(), true);// envia datos al servidor. El segundo parámetro true activa el auto-flush, lo que asegura que los datos se envíen de inmediato.
    }

    public void sendRequest(String request) {//Envía una solicitud al servidor.
        out.println(request);//envia la cadena de texto a través del socket
    }

    public String getResponse() throws IOException {// Lee una línea de la respuesta del servidor y la devuelve como una cadena de texto
        return in.readLine();
    }

    public void close() throws IOException {//Cierra la conexión del socket. Esto debe ser llamado cuando ya no se necesite la conexión.
        socket.close();
    }

    public static void main(String[] args) {
        try {
            //Crea una nueva instancia de Client y se conecta al servidor en localhost y el puerto 12345.
            Client client = new Client("localhost", 12345);
            client.sendRequest("LOGIN 12345678 password");//Envía una solicitud de inicio de sesión al servidor.
            String response = client.getResponse();//Lee la respuesta del servidor.
            System.out.println("Server response: " + response);
            client.close();// Cierra la conexión del socket.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
