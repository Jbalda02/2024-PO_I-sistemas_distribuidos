import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Tarea implements Runnable {

    private Socket socket;
    private RecursoCompartido recurso;

    public Tarea(Socket socket, RecursoCompartido recurso) {
        this.socket = socket;
        this.recurso = recurso;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(this.socket.getOutputStream());

            String data = (String) in.readObject();
            System.out.println( "Cliente dice: " + data );

            synchronized (this.recurso) {
                this.recurso.setData(data);
            }

            out.writeObject("Data recibida y procesada.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }   
}