import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public Client(String ip, int puerto) {
        Socket socket = new Socket(ip, puerto);
        System.out.println("Conexion exitosa con el servidor.");
        
        // I/O Buffers
        // Entrada
        InputStreamReader in_reader = new InputStreamReader( socket.getInputStream() );
        BufferedReader in_socket = new BufferedReader(in_reader);

        // Salida
        OutputStreamWriter out_writer = new OutputStreamWriter(socket.getOutputStream());
        PrintWriter out_socket = new PrintWriter(out_writer, true);

        String mensaje = in_socket.readLine();
        System.out.println( "Servidor dice: " + mensaje );

        out_socket.println("Gracias.");

        socket.close();
        System.out.println("Socket cerrado.");
    }
    public static void main(String[] args) {
        Scanner consola = new Scanner();
        System.out.print("Ingresar IP: ");
        String ip = consola.nextLine();
        System.out.print("Ingresar puerto: ");
        Integer puerto = Integer.parseInt( consola.nextLine() );
        
        new Client(ip, puerto);
    }
}