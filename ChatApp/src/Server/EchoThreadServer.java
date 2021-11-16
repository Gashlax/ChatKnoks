package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoThreadServer extends Thread {
	protected Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private InputStream inp = null;


	public EchoThreadServer(Socket clientSocket) {
		this.socket = clientSocket;
	}

	public void run() {
		String[] arrOfStr = new String[100];
		String greeting = null;
		try {
			inp=socket.getInputStream();
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while(true){
				greeting = in.readLine();
				if(greeting==null) {
					break;
				}else {
					arrOfStr = greeting.split(" ", 2);

					if (!"hello server".equals(greeting)) {

						out.println("hello client "+arrOfStr[1]);
						
					}
					else {
						out.println("hello client "+arrOfStr[1]);

						out.println("unrecognised greeting");
						try {
							socket.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return;
					}  


				} 
				}
				



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
