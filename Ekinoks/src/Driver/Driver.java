package Driver;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.BlockingQueue;

import Server.ListenNotify;
import windowBuilder.login.ChatKnoksLogin;

public class Driver {
	public static Connection connection=null;


	public static void main(String[] args) {

		final String host ="jdbc:postgresql://127.0.0.1:5432/ChatKnoks";
		final String user ="postgres";
		final String password ="2112131";
		/*
		// Loop forever pulling messages off the queue
		while (true)
		{
			try
			{
				// queue blocks until something is placed on it
				String msg = (String) queue.take();

				// Do something with the event
				System.out.println(msg);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

		}	*/

		try {
			connection = DriverManager.getConnection(host, user, password);
			EventQueue.invokeLater(new Runnable() {
				/*
				String url = "jdbc:pgsql://localhost/test";  
				String user = "test";
				String password = "test";
				Connection connection = DriverManager.getConnection(url, user, password); 
				 */

				public void run() {
					try {

						ChatKnoksLogin window = new ChatKnoksLogin();
						window.frame.setVisible(true);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
