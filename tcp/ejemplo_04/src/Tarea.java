import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Tarea implements Runnable {

    private Socket socket;

    public Tarea(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(this.socket.getOutputStream());

            String data = (String) in.readObject();
            System.out.println( "Cliente dice: " + data );

            List<RecursoCompartido> lista = new ArrayList<RecursoCompartido>();
            lista.add( new RecursoCompartido("Hola") );
            lista.add( new RecursoCompartido("mundo") );
            lista.add( new RecursoCompartido("Hola") );
            lista.add( new RecursoCompartido("mundo") );

            out.writeObject(lista);
            System.out.println("Lista enviada al cliente.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }   
}