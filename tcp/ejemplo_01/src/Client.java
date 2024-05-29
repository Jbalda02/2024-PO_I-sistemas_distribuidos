import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

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

        
    }
    public static void main(String[] args) {
        
    }
}