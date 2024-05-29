import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server_socket = new ServerSocket( 2020 );
        System.out.println( "Esperando clientes..." );

        Socket socket;

        while(true) {
            socket = server_socket.accept();
            HiloSocket hilo = new HiloSocket( socket );
            hilo.start();
        }
    }
}