package windowBuilder.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.awt.Toolkit;

public class CreateChatRoom {

	private JFrame frmCreateGroup;
	private JTextField gNameArea;
	private int UserId;
	private String Name;
	private Statement statement;
	Connection connection=Driver.Driver.connection;
	private LocalDateTime Date;
	private ResultSet resultSet=null;
	JLabel groupNamelbl ;

	public CreateChatRoom(int id) {
		UserId=id;
		initialize();
		

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreateGroup = new JFrame();
		frmCreateGroup.setIconImage(Toolkit.getDefaultToolkit().getImage(CreateChatRoom.class.getResource("/windowBuilder/resources/ekinoks_128_75.png")));
		frmCreateGroup.setTitle("Create Group");
		frmCreateGroup.setResizable(false);
		frmCreateGroup.setAlwaysOnTop(true);
		frmCreateGroup.setBounds(100, 100, 469, 366);
		frmCreateGroup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCreateGroup.getContentPane().setLayout(null);
		frmCreateGroup.setVisible(true);

		gNameArea = new JTextField();
		gNameArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gNameArea.setText("");
			}
		});

		gNameArea.setText("Enter a Group Name:");
		gNameArea.setBounds(111, 163, 255, 32);
		frmCreateGroup.getContentPane().add(gNameArea);
		gNameArea.setColumns(10);

		groupNamelbl = new JLabel("Group Name :");
		groupNamelbl.setBounds(111, 131, 234, 21);
		frmCreateGroup.getContentPane().add(groupNamelbl);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(CreateChatRoom.class.getResource("/windowBuilder/resources/ekinoks_128_75.png")));
		lblNewLabel_1.setBounds(0, 0, 366, 99);
		frmCreateGroup.getContentPane().add(lblNewLabel_1);

		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCreateGroup.dispose();
			}
		});
		closeBtn.setBounds(364, 0, 89, 83);
		frmCreateGroup.getContentPane().add(closeBtn);

		JButton resetBtn = new JButton("Reset");
		resetBtn.setBounds(111, 234, 100, 32);
		frmCreateGroup.getContentPane().add(resetBtn);

		JButton createBtn = new JButton("Create");
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gNameArea.getText().equals("")||gNameArea.getText().equals("Enter a Group Name:")) {
					groupNamelbl.setText("Group Name (Give a valid and unique name) :");
				}else {
					createChatRoom(gNameArea.getText());
					Name=gNameArea.getText();
					addFirstUser(UserId);
				}

			}
		});
		createBtn.setBounds(266, 234, 100, 32);
		frmCreateGroup.getContentPane().add(createBtn);
	}

	public void createChatRoom(String name) {
		try {
			Date  = LocalDateTime.now();
			statement = connection.createStatement();
			int updater=statement.executeUpdate("INSERT INTO public.groupchat( "+
					"gchatname, creationdate)"+
					" VALUES ('"+name+"', '"+Date+"');");
			System.out.println("heyyo"+Date);
		} catch (SQLException e) {
			System.out.println("zaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}

	}
	public int getChatID(String name) {
		int chatID = -1;
		try {
			statement = connection.createStatement();
			resultSet=statement.executeQuery("SELECT gchatid "+
					"FROM public.groupchat "+
					"where gchatname='"+name+"';");
			if(resultSet.next()) {
				chatID=resultSet.getInt(1);
				System.out.println(chatID);
				return chatID;
			}else {
				chatID=-1;
				return chatID;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chatID;
	}
	public void addFirstUser(int userid) {
		int groupid=getChatID(Name);
		System.out.println("Group id: "+groupid);
		if(groupid==-1) {
			
			groupNamelbl.setText("Group Nameasdasdasd (Give a valid and unique name) :");
		}else {
			System.out.println("Executed Perfectly id:"+userid+" group:"+groupid);
			try {
				statement = connection.createStatement();
				int row= statement.executeUpdate("INSERT INTO public.participants("
						+ "	groupid, userid) "
						+ "	VALUES ('"+groupid+"', '"+userid+"');");
				System.out.println("Executed Perfectly"+row+" id:"+userid+" group:"+groupid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
