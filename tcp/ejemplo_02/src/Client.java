import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private String ip;
    private int puerto;

    public Client(String ip, int puerto) {
        this.ip = ip;
        this.puerto = puerto;
    }

    public void conectar() {
        try {
            Socket socket = new Socket(this.ip, this.puerto);
            System.out.println("Conexion exitosa con el servidor.");
        
            // I/O Buffers
            // Entrada
            InputStreamReader in_reader = new InputStreamReader( socket.getInputStream() );
            BufferedReader in_socket = new BufferedReader(in_reader);
    
            InputStreamReader keyboard = new InputStreamReader( System.in );
            BufferedReader in_keyboard = new BufferedReader( keyboard );

            // Salida
            OutputStreamWriter out_writer = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter out_socket = new PrintWriter(out_writer, true);

            String console = "", input = "";

            while (true) {
                while ((console = in_keyboard.readLine()) != null) {
                    if (console != null) {
                        System.out.println("Cliente dice: " + console);
                        out_socket.println(console);
                        break;
                    }
                }

                while ((input = in_socket.readLine()) != null) {
                    System.out.println("Servidor dice: " + input);
                    break;
                }
                
                if (input != null && input.equals("adios")) {
                    break;
                }
            }

            socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Ingresar IP: ");
        String ip = console.nextLine();
        System.out.print("Ingresar Puerto: ");
        int puerto = Integer.parseInt( console.nextLine() );

        Client objeto = new Client(ip, puerto);
        objeto.conectar();
    }
}
