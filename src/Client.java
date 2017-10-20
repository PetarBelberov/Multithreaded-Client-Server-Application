
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Client extends JFrame implements ActionListener   {
	 		
	private static final long serialVersionUID = 7526472295622776147L;
	//Creating fields, labels and area using JFrame class
    JLabel labelName;
    JLabel labelAge;
    JLabel labelMark;
    JLabel labelEmail;
    
    JTextField txtFieldName;
    JTextField txtFieldAge;
    JTextField txtFieldMark;
    JTextField txtFieldEmail;
    
    JButton btnProcess;
    JButton btnAddClient;
    JButton btnLeave;
    JButton btnShow;
    JButton btnExit;
    
    JTextArea txtArea;
     
    public Client() {
    	
    	 //Set parameters  	
        this.setTitle("Simple Sample");
        this.setSize(420, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        labelName = new JLabel("Full Name: ");
        labelName.setBounds(10, 10, 120, 21);
        add(labelName);
        

        txtFieldName = new JTextField(); 
        txtFieldName.setBounds(105, 10, 120, 21);
        add(txtFieldName);

        labelAge = new JLabel("Age: ");
        labelAge.setBounds(10, 35, 120, 21);
        add(labelAge);

        txtFieldAge = new JTextField();
        txtFieldAge.setBounds(105, 35, 120, 21);
        add(txtFieldAge);

        labelMark = new JLabel("Mark: ");
        labelMark.setBounds(10, 60, 120, 21);
        add(labelMark);

        txtFieldMark = new JTextField();
        txtFieldMark.setBounds(105, 60, 120, 21);
        add(txtFieldMark);
        
        labelEmail = new JLabel("Email: ");
        labelEmail.setBounds(10, 85, 120, 21);
        add(labelEmail);

        txtFieldEmail = new JTextField();
        txtFieldEmail.setBounds(105, 85, 120, 21);
        add(txtFieldEmail);

        btnProcess = new JButton("Process");
        btnProcess.setBounds(250, 5, 120, 18);
        btnProcess.addActionListener(this);
        add(btnProcess);
        
        btnAddClient = new JButton("Add Client");
        btnAddClient.setBounds(250, 28, 120, 18);
        btnAddClient.addActionListener(this);
        add(btnAddClient);
        
        btnLeave = new JButton("Client Leave");
        btnLeave.setBounds(250, 51, 120, 18);
        btnLeave.addActionListener(this);
        add(btnLeave);
        
        btnShow = new JButton("Show clients");
        btnShow.setBounds(250, 74, 120, 18);
        btnShow.addActionListener(this);
        add(btnShow);
        
        btnExit = new JButton("Exit");
        btnExit.setBounds(250, 97, 120, 18);
        btnExit.addActionListener(this);
        add(btnExit);

        txtArea = new JTextArea();
        txtArea.setBounds(10, 120, 385, 200);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        add(txtArea);
        
        this.setVisible(true);
    }

    public static void main(String[] args){
    	new Client(); 
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(btnProcess)) {
            try {
                processInformation();
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if (event.getSource().equals(btnAddClient)){
        	if (!btnProcess.getModel().isEnabled()) {
        		new Client();
        	}
        }
        
        else if (event.getSource().equals(btnLeave)){
        	if (!btnProcess.getModel().isEnabled()) {
        		try {
                    removeClient();
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            	setVisible(false);
                dispose(); //Destroy the JFrame object.
			}
        }
        
        else if (event.getSource().equals(btnShow)){
        	if (!btnProcess.getModel().isEnabled()) {
        		try {
                    showClient();
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
			}
        }
        
        else if (event.getSource().equals(btnExit)){
        	dispose();
        	System.exit(0);
        }
    }

	private void showClient() throws UnknownHostException, IOException {
		Socket s = new Socket("localhost", 5016);
        ObjectOutputStream p = new ObjectOutputStream(s.getOutputStream());
       
    	p.writeObject(new Student("Show Client!", 27, 2, "remove_client@gmx.com"));
        p.flush();
	}

	public void removeClient()throws UnknownHostException, IOException {
		
		Socket s = new Socket("localhost", 5016);
        ObjectOutputStream p = new ObjectOutputStream(s.getOutputStream());
       
    	p.writeObject(new Student("Remove Client!", 27, 2, "remove_client@gmx.com"));
        p.flush();
   	 
       	BufferedReader response = new BufferedReader(new InputStreamReader(s.getInputStream()));

    	txtArea.setText("The server respond: " + response.readLine() + "\r\n");
	    	
		// p.close();   
        //response.close();
        //s.close();
	}

	//Method that process input information
    public void processInformation() throws UnknownHostException, IOException {

        Socket s = new Socket("localhost", 5016);
        ObjectOutputStream p = new ObjectOutputStream(s.getOutputStream());
        
        String name = txtFieldName.getText();
        String markStr = txtFieldMark.getText();
        String ageStr = txtFieldAge.getText();   
        String email = txtFieldEmail.getText();
        
        //Checks if the user entered a properly formatted name, mark, age and email
        //RegEx name
        String patternName = ("^[A-Z]+[a-z]+\\s[A-Z]+[a-z]+\\s[A-Z]+[a-z]+$");
        Pattern patternCompileName = Pattern.compile(patternName);
        Matcher matcherName = patternCompileName.matcher(name);
        
        //RegEx mark
        String patternMark = ("^[0-6]$");
        Pattern patternCompileMark = Pattern.compile(patternMark);
        Matcher matcherMark = patternCompileMark.matcher(String.valueOf(markStr));
        
        //RegEx age
        String patternAge = ("\\d");
        Pattern patternCompileAge = Pattern.compile(patternAge);
        Matcher matcherAge = patternCompileAge.matcher(String.valueOf(ageStr));
        
        //RegEx email
        String patternEmail = ("[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,5}");
        Pattern patternCompileEmail = Pattern.compile(patternEmail);
        Matcher matcherEmail = patternCompileEmail.matcher(email);
 
        int mark = 0;
        int age = 0;
        
        //Match the regular expressions
        if (matcherName.find() && matcherMark.find() && matcherAge.find() && matcherEmail.find()) {

			 mark = Integer.parseInt(markStr);
			 age = Integer.parseInt(ageStr);
			 
			 //writes primitive data of the object to an OutputStream
	    	 p.writeObject(new Student(name, age, mark, email));
	         p.flush();
    	 
        	//Read the details from server
        	 BufferedReader response = new BufferedReader(new InputStreamReader(
                     s.getInputStream()));
 
	    	 txtArea.setText("The server respond: " + response.readLine() + "\r\n");
	    	 btnProcess.setEnabled(false);
    		// p.close();   
	        // response.close();
	         //s.close();
		}
        else {
        	//If RegEx is false, we will set name = "Wrong!"
        	p.writeObject(new Student("Wrong!", age, mark, email));
            p.flush();
   	 
       	 BufferedReader response = new BufferedReader(new InputStreamReader(
                    s.getInputStream()));

	    	 txtArea.setText("The server respond: " + response.readLine() + "\r\n");
	    	 txtArea.append("Example: " + "\r\n");
	    	 txtArea.append(response.readLine() + "\r\n");
	    	 txtArea.append(response.readLine() + "\r\n");
	    	 txtArea.append(response.readLine() + "\r\n");
	    	 txtArea.append(response.readLine());
	    	 
    		// p.close();   
	         //response.close();
	       // s.close();
		}  
    }
}

