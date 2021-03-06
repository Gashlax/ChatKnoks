package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class ServerClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private int userId;
    
    
    public ServerClient(int userid) throws UnknownHostException, IOException {
    	userId=userid;
        startConnection("127.0.0.1", 6666);
    }

    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
    
    public void sendit() throws UnknownHostException, IOException {
        
        String response = sendMessage("hello server "+userId );
        System.out.println(response+" asasklalsklas");
    }
//    public static void main(String[] args) throws IOException {
//    	givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect();
//    }
}