import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server() {
        ServerSocket server_socket = new ServerSocket( 2020 );
        System.out.println( "Puerto 2020 esta abierto." );

        Socket socket = server_socket.accept();
        System.out.println("Cliente " + socket.getInetAddress() + "se ha conectado.");

        // I/O Buffers
        // Entrada
        InputStreamReader in_reader = new InputStreamReader( socket.getInputStream() );
        BufferedReader in_socket = new BufferedReader(in_reader);

        // Salida
        OutputStreamWriter out_writer = new OutputStreamWriter(socket.getOutputStream());
        PrintWriter out_socket = new PrintWriter(out_writer, true);

        out_socket.println("¡Bienvenido!");

        String mensaje = in_socket.readLine();
        System.out.println( "Cliente dice: " + mensaje );

        socket.close();
        System.out.println("Socket esta cerrado.");
    }

    public static void main(String[] args) {
        new Server();
    }
}
