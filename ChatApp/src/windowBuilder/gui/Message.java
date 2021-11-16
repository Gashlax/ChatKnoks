package windowBuilder.gui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;


public class Message {
	private String username;
	private LocalDateTime Date;
	private String Context;
	private int MessageId;
	private int userId;
	private static ResultSet resultSet=null;
	Connection connection=Driver.Driver.connection;
	private static int updater=0;
	private static Statement statement;

	public Message(String name, String message,int Groupid) {
		this.username=name;
		this.Context=message;
		Date  = LocalDateTime.now();
		try {
			statement = connection.createStatement();
			updater=statement.executeUpdate("INSERT INTO message (musername, mdate, mcontext,mgroupid) "+
			"values ('"+username+"','"+Date+"','"+Context+"','"+Groupid+"')");
			System.out.println("heyyo"+Date);
			close();
		} catch (SQLException e) {
			System.out.println("zaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}
		//statement.executeQuery("select * from employee order by employeeid asc");
		
		
		
	}

	public static void close() {
		try {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(statement!=null) {
				statement.close();
			}
		}catch(Exception e) {
			System.out.println("\nAn Error Occurred");
		}

	}
	

	public void insertMessage() {
		try {
			Statement statement = connection.createStatement();
			System.out.println("heyyo"+Date);
		} catch (SQLException e) {
			System.out.println("zaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}
	}



	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public LocalDateTime getDate() {
		return Date;
	}

	public void setDate(LocalDateTime date) {
		Date = date;
	}

	public String getContext() {
		return Context;
	}

	public void setContext(String context) {
		Context = context;
	}

	public int getMessageId() {
		return MessageId;
	}

	public void setMessageId(int messageId) {
		MessageId = messageId;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
}
