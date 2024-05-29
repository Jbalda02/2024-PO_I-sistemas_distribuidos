import java.net.ServerSocket;
import java.net.Socket;

public class HiloSocket extends Thread {
    
    private Socket socket;

    public HiloSocket(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        Servicio servicio = new Servicio();
        servicio.inicio(this.socket);
    }
}
