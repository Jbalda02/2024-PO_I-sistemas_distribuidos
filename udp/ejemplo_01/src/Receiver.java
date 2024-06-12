import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Receiver {

    public static final int PORT = 2020;

    public Receiver() {

    }

    public void conectar() {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            System.out.println("Receiver is working...");

            // Request
            byte[] buffer = new byte[1500];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String mensaje = (new String(buffer)).trim();
            System.out.println("Mensaje recibido: " + mensaje);
            
            // Response
            Scanner consola = new Scanner(System.in);
            System.out.print("Ingresar mensaje: ");
            mensaje = consola.nextLine();

            InetAddress nodo_address = packet.getAddress();
            int nodo_port = packet.getPort();
            buffer = mensaje.getBytes();
            packet = new DatagramPacket(buffer, buffer.length, nodo_address, nodo_port);
            socket.send(packet);
            System.out.println("Mensaje enviado: " + mensaje);

            consola.close();
            socket.close();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Receiver objeto = new Receiver();
        objeto.conectar();
    }
}
