package windowBuilder.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Window.Type;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Participants {

	private JFrame frmParticipants;
	private int groupChat=-1;
	private ResultSet resultSet=null;
	private ResultSet resultSet2=null;
	private ResultSet resultSet3=null;
	public JTextArea participants;
	public ArrayList<String> availableUsers =new ArrayList<String>();
	private Choice choice;
	private String selectedName;

	Connection connection=Driver.Driver.connection;
	private Statement statement;
	private Statement statement2;
	private Statement statement3;
	public Participants(int groupChat1) {
		groupChat=groupChat1;
		initialize(groupChat);
		if(groupChat!=-2) {
			console("Here is the participants of the chat ");
			putUsers(groupChat);
			addUsers(groupChat);
		}else {
			console("Select Chat Room to see participants.");
		}
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int groupChat) {
		frmParticipants = new JFrame();
		frmParticipants.setResizable(false);
		frmParticipants.setIconImage(Toolkit.getDefaultToolkit().getImage(Participants.class.getResource("/windowBuilder/resources/ekinoks_128_75.png")));
		frmParticipants.setTitle("Participants");
		frmParticipants.setAlwaysOnTop(true);
		frmParticipants.setType(Type.POPUP);
		frmParticipants.setBounds(100, 100, 408, 445);
		frmParticipants.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmParticipants.getContentPane().setLayout(null);
		frmParticipants.setVisible(true);



		JLabel iconLabel = new JLabel("");
		iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconLabel.setIcon(new ImageIcon(Participants.class.getResource("/windowBuilder/resources/ekinoks_128_75.png")));
		iconLabel.setBounds(-1, 0, 277, 75);
		frmParticipants.getContentPane().add(iconLabel);

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmParticipants.dispose();
			}
		});
		closeButton.setBounds(282, 348, 110, 49);
		frmParticipants.getContentPane().add(closeButton);

		participants = new JTextArea();
		participants.setEditable(false);
		participants.setBounds(-1, 107, 277, 290);
		frmParticipants.getContentPane().add(participants);

		JButton addParticipant = new JButton("Add Participant");
		addParticipant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedName=choice.getSelectedItem();
				addToChat(selectedName);
			}
		});
		addParticipant.setBounds(273, 0, 119, 75);
		frmParticipants.getContentPane().add(addParticipant);

		JLabel lblNewLabel = new JLabel("Select User To Add");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setLabelFor(frmParticipants);
		lblNewLabel.setBounds(282, 86, 108, 19);
		frmParticipants.getContentPane().add(lblNewLabel);

		choice = new Choice();

		choice.setBounds(282, 111, 108, 20);
		frmParticipants.getContentPane().add(choice);


		JLabel participantsLabel = new JLabel("Participants");
		participantsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		participantsLabel.setBounds(-1, 85, 285, 20);
		frmParticipants.getContentPane().add(participantsLabel);

	}

	public  void console(String text) {
		//this.participants.setText(text+"\n\r");
		participants.append(text+"\n\r");
	}

	public void putUsers(int groupChat) {

		try {
			statement = connection.createStatement();
			resultSet=statement.executeQuery("select username "
					+ "from public.user "
					+ "where userid in (select distinct participants.userid "
					+ "				from participants "
					+ "				where groupid='"+groupChat+"');");
			statement3= connection.createStatement();
			resultSet3=statement3.executeQuery("select status "
					+ "from public.user "
					+ "where userid in (select distinct participants.userid "
					+ "				from participants "
					+ "				where groupid='"+groupChat+"');");

			ResultSetMetaData rsmd=resultSet.getMetaData();
			int totalCollumnNum=rsmd.getColumnCount();

			while(resultSet.next() && resultSet3.next()) {
				for(int i=1;i<=totalCollumnNum;i++) {

					console(resultSet.getString(i)+"\t"+resultSet3.getString(i));
					System.out.println(resultSet.getString(i)+"\t"+resultSet3.getString(i));

				}

			}

		} catch (SQLException e) {
			System.out.println("zaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}
	}

	public void addUsers(int groupChat) {
		try {
			statement2 = connection.createStatement();
			resultSet2=statement2.executeQuery("select username "
					+ "from public.user "
					+ "where userid not in (select distinct participants.userid "
					+ "				from participants "
					+ "				where groupid='"+groupChat+"');");
			ResultSetMetaData rsmd1=resultSet2.getMetaData();
			int totalCollumnNum=rsmd1.getColumnCount();
			availableUsers.add("Select User");
			while(resultSet2.next()) {
				for(int i=1;i<=totalCollumnNum;i++) {
					availableUsers.add(resultSet2.getString(i));
					System.out.println(resultSet2.getString(i)+"\n");

				}

			}
		} catch (SQLException e) {
			System.out.println("zaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}
		for(int i=0;i<availableUsers.size();i++) {
			System.out.println(availableUsers.get(i)+"\n");
			choice.add(availableUsers.get(i));
		}
	}

	public void addToChat(String username) {
		try {
			statement = connection.createStatement();
			resultSet=statement.executeQuery("select userid "
					+ "from public.user "
					+ "where username='"+username+"';");
			if(resultSet.next()) {
				int UserID=resultSet.getInt(1);
				System.out.println(UserID);
				insertion(UserID); 
			}


		} catch (SQLException e) {
			System.out.println("zaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}

	}
	public void insertion(int id) {
		int gid=getGroupChat();
		System.out.println("Executed Perfectly id:"+id+" group:"+gid);
		try {
			statement = connection.createStatement();
			int row= statement.executeUpdate("INSERT INTO public.participants("
					+ "	groupid, userid) "
					+ "	VALUES ('"+gid+"', '"+id+"');");
			System.out.println("Executed Perfectly"+row+" id:"+id+" group:"+gid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getGroupChat() {
		return groupChat;
	}

	public void setGroupChat(int groupChat) {
		this.groupChat = groupChat;
	}
	
}