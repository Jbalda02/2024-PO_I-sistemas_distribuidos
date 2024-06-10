import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private static final int PORT = 2020;
    private static RecursoCompartido recurso = new RecursoCompartido();

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            while(true) {
                Socket socket = server.accept();
                Thread hilo = new Thread( new Tarea(socket, recurso) );
                hilo.start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
