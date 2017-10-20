
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerClientThread extends Thread{
	
	Socket serverClient;

	ServerClientThread(Socket inSocket) {
	   serverClient = inSocket;
	}
 
	public void run(){  
		
		  try {  
			  ObjectInputStream inputValues = new ObjectInputStream(serverClient.getInputStream());
			  Student received = (Student) inputValues.readObject();
			  
			  if (received.getName().equals("Wrong!")) {
				 
            	 PrintWriter output = new PrintWriter(serverClient.getOutputStream(), true);
                 output.println("Wrong typed value.Try again!");
                 output.println("Name: Johny Domino Domino");
                 output.println("Age: 27");
                 output.println("Mark: 2");
                 output.println("Email: petar_belberov@gmx.com");
				} 
			 
			  else if (received.getName().equals("Remove Client!")) {
				 
				  Counter.clientsStorage.take();
				  System.out.println("Client removed");
				  System.out.println("Clients: " + Counter.clientsStorage.size());
				  PrintWriter output = new PrintWriter(serverClient.getOutputStream(), true);
	              output.println("Student removed");
				}
			  
			  else if (received.getName().equals("Show Client!")) {
					 
				  JOptionPane.showMessageDialog(null, "Clients accepted: " + Counter.clientsStorage.size());
				}
			  
			  else {
					 
			  	Counter.clientsStorage.add(received.getName());
			  
				System.out.println("Client accepted: " + received.getName());
				System.out.println("Clients: " + Counter.clientsStorage.size());
	        	
				PrintWriter output = new PrintWriter(serverClient.getOutputStream(), true);
                output.println("Student " + received.getName() + received.getEmail() + " has been received");
				} 
		} catch (Exception e) {
			// TODO: handle exception
		}
	  }
	 
}
