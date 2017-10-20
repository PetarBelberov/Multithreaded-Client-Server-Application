
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] argv) throws Exception {
    	
    	//Create a server socket, bounds to a specified port
        ServerSocket serverSocket = new ServerSocket(5016);
        System.out.println("Server started");
      
        while (true) {
 
            Socket serverClient = serverSocket.accept();	//Wait for client to connect      
            ServerClientThread sct = new ServerClientThread(serverClient);
            sct.start();
        }
    }
}