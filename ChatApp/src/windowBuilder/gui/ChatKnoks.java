package windowBuilder.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import javax.swing.JTextPane;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultCaret;


import Server.Server;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import windowBuilder.localisation.LanguageModifier;
import windowBuilder.login.ChatKnoksLogin;
import Server.ServerClient;

import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.SystemColor;
import java.awt.event.WindowFocusListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.WindowEvent;
import java.awt.Choice;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;




public class ChatKnoks extends JFrame{
	public static ArrayList<String> ChatMessages=new ArrayList<String>();
	
	public static Connection connection=null;
	private static final long serialVersionUID = 1L;
	
	private JFrame frmChatknoks;
	private JTextField chatField;
	private JButton sendButton;
	private JButton closeButton;
	private JButton languageButton;
	private JLabel logo;
	private JButton userButton;
	public static JTextArea chatHistory;
	private DefaultCaret caret;
	
	private String Name;
	private String Ip;
	private Choice choice;
	private User userS;
	private JLabel lblNewLabel;
	Server server;
	private String selectedGroup;
	private JLabel chatRoomLabel;
	private JButton createChatButton;
	private ServerClient connectionServer;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


	// Database connection

	/**
	 * Create the application.
	 */
	public ChatKnoks(String name, String ip) {

		connection = Driver.Driver.connection;

		//ListenNotify ln = new ListenNotify();
		//queue = ln.getQueue();


		System.out.println("Java JDBC PostgreSQL Example");
		System.out.println("Connected to PostgreSQL database!");
		Name=name;
		Ip=ip;
		userS=new User(name);
		initialize(Name, Ip);
		int userid=userS.dbUserID(name);
		
		


		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}


		console("\nWelcome to the ChatKnoks\n");
		console("\nPlease Select a Chat Room from List\n");
		//server= new Server(userS.ChatNames.get(0));
		
		try {
			connectionServer=new ServerClient(userid);
			scheduler.scheduleAtFixedRate(() -> {
				try {
					System.out.println("on repeat");
					connectionServer.sendit();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, 1, 1, TimeUnit.SECONDS);
			

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name, String ip) {
		frmChatknoks = new JFrame();
		frmChatknoks.getContentPane().setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 13));
		frmChatknoks.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				userS.dbUserOffline();
				frmChatknoks.dispose();
				try {
					connectionServer.stopConnection();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmChatknoks.dispose();
			}
		});
		frmChatknoks.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				chatField.requestFocusInWindow();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});

		frmChatknoks.setType(Type.POPUP);
		frmChatknoks.getContentPane().setBackground(SystemColor.control);
		frmChatknoks.setAlwaysOnTop(true);
		frmChatknoks.setFont(new Font("Agency FB", Font.PLAIN, 15));
		frmChatknoks.setTitle("ChatKnoks");
		frmChatknoks.setIconImage(Toolkit.getDefaultToolkit().getImage(ChatKnoks.class.getResource("/windowBuilder/resources/ekinoks_128_75.png")));
		frmChatknoks.setBounds(100, 100, 816, 582);
		frmChatknoks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{99, 200, 397, 121, 0};
		gridBagLayout.rowHeights = new int[]{40, 36, 40, 91, 41, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frmChatknoks.getContentPane().setLayout(gridBagLayout);
		frmChatknoks.setVisible(true);




		languageButton = new JButton("Switch Language");
		languageButton.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_languageButton = new GridBagConstraints();
		gbc_languageButton.fill = GridBagConstraints.BOTH;
		gbc_languageButton.insets = new Insets(0, 0, 5, 5);
		gbc_languageButton.gridx = 0;
		gbc_languageButton.gridy = 0;

		languageButton.addActionListener(new ActionListener() {
			boolean isTurkish =false;	
			public void actionPerformed(ActionEvent e) {

				LanguageModifier a=new LanguageModifier();
				ResourceBundle rb=a.swapLanguage(isTurkish);

				closeButton.setText(rb.getString("Add.closeButton.text"));
				sendButton.setText(rb.getString("Add.sendButton.text"));
				languageButton.setText(rb.getString("Add.languageButton.text"));
				userButton.setText(rb.getString("Add.userButton.text"));
				createChatButton.setText(rb.getString("Add.createChatButton.text"));
				isTurkish=(!isTurkish);

			}
		});
		frmChatknoks.getContentPane().add(languageButton, gbc_languageButton);

		logo = new JLabel("");
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(ChatKnoks.class.getResource("/windowBuilder/resources/ekinoks_128_75.png")));
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.gridheight = 3;
		gbc_logo.gridwidth = 2;
		gbc_logo.insets = new Insets(0, 0, 5, 5);
		gbc_logo.gridx = 1;
		gbc_logo.gridy = 0;
		frmChatknoks.getContentPane().add(logo, gbc_logo);

		closeButton = new JButton("Close");
		closeButton.setForeground(new Color(165, 42, 42));
		closeButton.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.fill = GridBagConstraints.BOTH;
		gbc_closeButton.insets = new Insets(0, 0, 5, 0);
		gbc_closeButton.gridx = 3;
		gbc_closeButton.gridy = 0;
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userS.dbUserOffline();
				frmChatknoks.dispose();
				try {
					connectionServer.stopConnection();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		frmChatknoks.getContentPane().add(closeButton, gbc_closeButton);

		userButton = new JButton("Participants");
		userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("SERVER IDD "+server.getChatID(selectedGroup));
				Participants parti=new Participants(server.getChatID(selectedGroup));

			}
		});
		
		createChatButton = new JButton("Create Chat Room");
		createChatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateChatRoom chatRoom=new CreateChatRoom(userS.dbUserID(Name));
			}
		});
		GridBagConstraints gbc_createChatButton = new GridBagConstraints();
		gbc_createChatButton.fill = GridBagConstraints.BOTH;
		gbc_createChatButton.insets = new Insets(0, 0, 5, 5);
		gbc_createChatButton.gridx = 0;
		gbc_createChatButton.gridy = 1;
		frmChatknoks.getContentPane().add(createChatButton, gbc_createChatButton);

		lblNewLabel = new JLabel("Chat Rooms");
		lblNewLabel.setFont(new Font("Arial", Font.ITALIC, 15));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 1;
		frmChatknoks.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		userButton.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_userButton = new GridBagConstraints();
		gbc_userButton.fill = GridBagConstraints.BOTH;
		gbc_userButton.insets = new Insets(0, 0, 5, 5);
		gbc_userButton.gridx = 0;
		gbc_userButton.gridy = 2;
		frmChatknoks.getContentPane().add(userButton, gbc_userButton);

		chatHistory = new JTextArea();
		chatHistory.setFont(new Font("Arial", Font.PLAIN, 15));
		chatHistory.setBackground(new Color(230	, 230, 230));
		chatHistory.setEditable(false);
		caret = (DefaultCaret)chatHistory.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		choice = new Choice();
//		choice.addMouseListener(new MouseAdapter() {
//
//			public void mousePressed(MouseEvent e) {
//				selectedGroup=choice.getSelectedItem();
//				System.out.println(selectedGroup);
//				chatHistory.setText("");
//				server=new Server(selectedGroup);
//				chatRoomLabel.setText(selectedGroup);
//			}
//		});
		GridBagConstraints gbc_choice = new GridBagConstraints();
		gbc_choice.anchor = GridBagConstraints.NORTH;
		gbc_choice.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice.insets = new Insets(0, 0, 5, 0);
		gbc_choice.gridx = 3;
		gbc_choice.gridy = 2;
		frmChatknoks.getContentPane().add(choice, gbc_choice);
		int totalChatNumber=userS.ChatNames.size();
		if(totalChatNumber==1) {
			choice.add("No chat room");
			console("\nThere is no chat room to select\n");

		}else {
			for(int i=0;i<totalChatNumber;i++) {
				choice.add(userS.ChatNames.get(i));

			}
		}

		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedGroup=choice.getSelectedItem();
				chatRoomLabel.setText(selectedGroup);
				server=new Server(selectedGroup);
				if(selectedGroup.equals("Chat Rooms")) {
					chatHistory.setText("");
					console("\nPlease select Chat Room From Upper Panel");
					ChatMessages.remove(0);
					
				}

			}
		});






		JScrollPane scroll= new JScrollPane(chatHistory);
		GridBagConstraints scrollConstraints = new GridBagConstraints();
		scrollConstraints.gridwidth = 4;
		scrollConstraints.insets = new Insets(0, 10, 10, 10);
		scrollConstraints.fill = GridBagConstraints.BOTH;
		scrollConstraints.gridx = 0;
		scrollConstraints.gridy = 3;
		frmChatknoks.getContentPane().add(scroll, scrollConstraints);

		chatRoomLabel = new JLabel("Select a Chat Room");
		chatRoomLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scroll.setColumnHeaderView(chatRoomLabel);

		chatField = new JTextField();
		chatField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		chatField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER) {
					send(chatField.getText());
				}
			}
		});
		GridBagConstraints gbc_chatField = new GridBagConstraints();
		gbc_chatField.gridwidth = 3;
		gbc_chatField.insets = new Insets(10, 10, 10, 10);
		gbc_chatField.fill = GridBagConstraints.BOTH;
		gbc_chatField.gridx = 0;
		gbc_chatField.gridy = 4;
		frmChatknoks.getContentPane().add(chatField, gbc_chatField);
		chatField.setColumns(10);

		sendButton = new JButton("Send");
		sendButton.setForeground(new Color(0, 128, 0));
		sendButton.setBackground(Color.LIGHT_GRAY);
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(chatField.getText());

			}
		});
		sendButton.setIcon(new ImageIcon(ChatKnoks.class.getResource("/windowBuilder/resources/mes_icon_40_30.png")));
		GridBagConstraints gbc_sendButton = new GridBagConstraints();
		gbc_sendButton.gridx = 3;
		gbc_sendButton.gridy = 4;
		gbc_sendButton.insets = new Insets(0, 0, 10, 10);
		frmChatknoks.getContentPane().add(sendButton, gbc_sendButton);


		/*
		pgConnection.addNotificationListener(new PGNotificationListener() { 
			Statement statement1 = pgConnection.createStatement();
			statement1.execute("LISTEN test");
			statement1.close();
			public void notification(int processId, String channelName, String payload) {
				System.out.println("Received Notification: " + processId + ", " + channelName + ", " + payload); 
			}


		});
		 */

	}


	public void send(String message) {

		if(!message.equals("")) {
			System.out.println(server.getChatID(selectedGroup)+"server id ");
			Message newMessage =new Message(Name,message,server.getChatID(selectedGroup));

			message=Name+": "+message;
			console(message);
			chatField.setText("");
		}else {
			chatHistory.setText("");
			server= new Server(choice.getSelectedItem());

			/*
			//queue = ln.getQueue();

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
			 */
			return;
		}
	}

	public static void sendit(String message) {
		console(message);

	}
	public static void console(String text) {

		chatHistory.append(text+"\n\r");
	}




	public void refresh() {
		//		choice.add("No chat room");
		//		console("\nThere is no chat room to select\n");
		if(choice.getSelectedItem()=="No chat room") {

		}else {
			
			server= new Server(choice.getSelectedItem());
		}


	}

	/*
	public static void ListenNotify()
	{
		// Get database info from environment variables


		// Create the listener callback
		PGNotificationListener listener = new PGNotificationListener()
		{
			@Override
			public void notification(int processId, String channelName, String payload)
			{
				// Add event and payload to the queue
				queue.add("/channels/" + channelName + " " + payload);
			}
		};

		try
		{
			// Create a data source for logging into the db
			PGDataSource dataSource = new PGDataSource();
			dataSource.setHost("localhost");
			dataSource.setPort(5432);
			dataSource.setDatabaseName("ChatKnoks");
			dataSource.setUser("postgres");
			dataSource.setPassword("2112131");

			// Log into the db
			pgConnection = (PGConnection) dataSource.getConnection();

			// add the callback listener created earlier to the connection
			pgConnection.addNotificationListener(listener);

			// Tell Postgres to send NOTIFY q_event to our connection and listener
			Statement statement = connection.createStatement();
			statement.execute("LISTEN q_event");
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @return shared queue

	public BlockingQueue getQueue()
	{
		return queue;
	}
	 */

	/*

	public void notificationListener() throws Throwable {

        System.out.println("Notification****************");
        try
        {


            this.pgConnection = (PGConnection) connection;

            System.out.println("PG CONNECTON: "+ pgConnection);
            Statement listenStatement = conn.createStatement();
            listenStatement.execute("LISTEN newmes");
            listenStatement.close();

            pgConnection.addNotificationListener(new PGNotificationListener() {

                @Override
                public void notification(int processId, String channelName, String payload){

                    System.out.println("*********INSIDE NOTIFICATION*************");

                    System.out.println("Payload: " );
                }
            });
        }catch(Exception e) {
        	System.out.println("broken");
        }
	}
				/*
	public static void close() {
		try {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(statement!=null) {
				statement.close();
			}
			if(connect!=null) {
				connect.close();
			}
		}catch(Exception e) {
			System.out.println("\nAn Error Occurred");
		}

	}
	 */





}
