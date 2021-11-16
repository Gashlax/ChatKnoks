package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import windowBuilder.gui.ChatKnoks;

public class Server {

	Connection connection=Driver.Driver.connection;
	public  ArrayList<String> compare=new ArrayList<String>();
	static ResultSet resultSet=null;
	static ResultSet resultSetNames=null;
	static ResultSet resultSetNumber=null;
	static Statement statement=null;
	static Statement statement2=null;
	static Statement statementNumber=null;
	private int groupChatN =-1;
	public Server(String groupChatName) {	
		int numero=getChatID(groupChatName);
		if(numero!=-2) {
			reloadBack(numero);
			boolean status=isEqual(compare);
			if(!status) {
				print(compare);
			}
		}else {
			
		}

	}

	public int getChatID(String groupChatName) {
		try {
			statementNumber = connection.createStatement();
			resultSet=statementNumber.executeQuery("select gchatid "
					+ "FROM groupchat "
					+ "where gchatname='"+groupChatName+"';");

			//			while(resultSet.)
			//System.out.println(groupChatN);
			if(resultSet.next()) {
				groupChatN=resultSet.getInt(1);
				//System.out.println(groupChatN+"asd");
			}
			if(groupChatName.equals("Chat Rooms")) {
				//ChatKnoks.chatHistory.setText("");
				return -2;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groupChatN;
	}

	public  void reloadBack(int groupChatNumber) {
		try {
			statement = connection.createStatement();
			statement2 = connection.createStatement();
			resultSet=statement.executeQuery("select mcontext "
					+ "FROM message "
					+ "where mgroupid="+groupChatNumber
					+ " order by mdate asc ;");
			resultSetNames=statement2.executeQuery("select musername "
					+ "FROM message "
					+ "where mgroupid="+groupChatNumber 
					+ " order by mdate asc ;");
			printResult(resultSetNames,resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void printResult(ResultSet resultSetNames,ResultSet resultset) throws SQLException{
		ResultSetMetaData rsmd=resultset.getMetaData();
		ResultSetMetaData rsmd2=resultSetNames.getMetaData();
		int totalCollumnNum=rsmd.getColumnCount();
		int totalCollumnNum2=rsmd2.getColumnCount();
		if(totalCollumnNum!=totalCollumnNum2) {
			return;
		}else {
			while(resultset.next() && resultSetNames.next()) {
				for(int i=1;i<=totalCollumnNum;i++) {
					//System.out.print(resultSetNames.getString(i)+": "+resultset.getString(i));
					compare.add(resultSetNames.getString(i)+": "+resultset.getString(i));
					//ChatKnoks.console(resultSetNames.getString(i)+": "+resultset.getString(i));
				}

			}
		}

	}

	public boolean isEqual(ArrayList<String> compare) {

		if(compare.equals(ChatKnoks.ChatMessages)) {

			return true;
		}else {

			return false;
		}

	}

	public void print(ArrayList<String> compare) {
		System.out.println("Entered To Room");
		ChatKnoks.chatHistory.setText("");
		for(int i=0;i<compare.size();i++) {
			//System.out.println(compare.get(i));
			ChatKnoks.console(compare.get(i));
		}
		ChatKnoks.ChatMessages=compare;

	}


}
