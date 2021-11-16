package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection {
	static final int PORT = 6666;
//    private ServerSocket serverSocket;
//    private Socket clientSocket;
//    private PrintWriter out;
//    private BufferedReader in;
//    
   

//    public void start(int port) throws IOException {
//        serverSocket = new ServerSocket(port);
//        clientSocket = serverSocket.accept();
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        System.out.println("here is the server");
//        String greeting = in.readLine();
//            if ("hello server".equals(greeting)) {
//                out.println("hello client");
//            }
//            else {
//                out.println("unrecognised greeting");
//            }
//        
//    }
//
//    public void stop() throws IOException {
//        in.close();
//        out.close();
//        clientSocket.close();
//        serverSocket.close();
//    }
    public static void main(String[] args) throws IOException {
    	ServerSocket serverSocket = null;
        Socket socket = null;
        
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
    	while (true) {
            try {
            	System.out.println("Server Started");
                socket = serverSocket.accept();
                new EchoThreadServer(socket).start();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            
        }
    }
}