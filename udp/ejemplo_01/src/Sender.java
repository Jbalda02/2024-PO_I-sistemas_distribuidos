import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Sender {
    public Sender() {

    }

    public void conectar() {
        try {
            // enviar mensaje
            DatagramSocket socket = new DatagramSocket();
            Scanner consola = new Scanner(System.in);
            System.out.print("Ingresar mensaje: ");
            String mensaje = consola.nextLine();

            byte[] buffer = mensaje.getBytes();
            InetAddress nodo_address = InetAddress.getByName("localhost");
            int nodo_port = 2020;
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, nodo_address, nodo_port);
            socket.send(packet);
            System.out.println("Mensaje enviado: " + mensaje);

            // recibir mensaje
            buffer = new byte[1500];
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            mensaje = (new String(buffer)).trim();
            System.out.println("Mensaje recibido: " + mensaje);
            
            consola.close();
            socket.close();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Sender objeto = new Sender();
        objeto.conectar();
    }
}