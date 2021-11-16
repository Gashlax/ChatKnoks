package windowBuilder.gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class User {
	private String Username;
	private int UserID;
	Connection connection=Driver.Driver.connection;
	private Statement statement;
	private Statement statement2;
	public ArrayList<String> ChatNames=new ArrayList<String>();
	private ResultSet resultSet=null;
	private ResultSet resultSet2=null;

	public User(String username) {
		this.Username=username;
		dbUserID(Username);
		
		dbUserOnline(Username);
		dbUserCheck(UserID); 
	}

	public int dbUserID(String username) {
		try {
			statement = connection.createStatement();
			resultSet=statement.executeQuery("select userid "
					+ "from public.user "
					+ "where username='"+username+"';");
			if(resultSet.next()) {
				UserID=resultSet.getInt(1);
				
				
			}


		} catch (SQLException e) {
			System.out.println("zaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}
		return UserID;
	}

	public void dbUserCheck(int UserID) {
		try {
			statement = connection.createStatement();
			resultSet=statement.executeQuery("select distinct gchatname "
					+ "from public.groupchat "
					+ "where gchatid in (select groupid "
					+ "				from participants "
					+ "				where userid='"+UserID+"');");
			ResultSetMetaData rsmd=resultSet.getMetaData();
			int totalCollumnNum=rsmd.getColumnCount();
			ChatNames.add("Chat Rooms");
			while(resultSet.next()) {
				for(int i=1;i<=totalCollumnNum;i++) {
					ChatNames.add(resultSet.getString(i));
					System.out.println(ChatNames.get(i-1));

				}

				System.out.println();
			}

		} catch (SQLException e) {
			System.out.println("zaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}

	}
	
	public void dbUserOnline(String Username) {
		String online="Online";
		try {
			int UserID=dbUserID(Username);
			statement2 = connection.createStatement();
			int update=statement2.executeUpdate("UPDATE public.user "+
					"SET status='"+online+"' "+
					"WHERE userid='"+UserID+"';");
		
		} catch (SQLException e) {
			System.out.println("zaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}

	}
	public void dbUserOffline() {
		
		String offline="Offline";
		try {
			statement2 = connection.createStatement();
			int update=statement2.executeUpdate("UPDATE public.user "+
					"SET status='"+offline+"' "+
					"WHERE userid='"+UserID+"';");
		
		} catch (SQLException e) {
			System.out.println("zaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}

	}
	

	private String getUsername() {
		return Username;
	}

	private void setUsername(String username) {
		Username = username;
	}

	private int getUserID() {
		return UserID;
	}

	private void setUserID(int userID) {
		UserID = userID;
	}

	private ArrayList<String> getChatNames() {
		return ChatNames;
	}

	private void setChatNames(ArrayList<String> chatNames) {
		ChatNames = chatNames;
	}



}
