package windowBuilder.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import windowBuilder.gui.ChatKnoks;
import windowBuilder.localisation.LanguageModifier;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

public class ChatKnoksLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private JTextField loginField;
	private JTextField IpField;
	private JButton SwitchButton;
	private JButton hintButton;
	private JLabel iconLabel;
	private JLabel loginLabel;
	private JLabel ipLabel;
	private JButton resetButton;
	private JButton connectButton;
	private JLabel loginlbl;
	private JButton signinButton;
	private ChatKnoks chatKnoks;

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


	/**
	 * Create the application.
	 */
	public ChatKnoksLogin() {
		initialize();

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER) {
					System.out.print("asdasd");
					login(loginField.getText(),IpField.getText());
				}

			}
		});
		frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				loginField.requestFocusInWindow();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});

		frame.setBackground(Color.DARK_GRAY);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ChatKnoksLogin.class.getResource("/windowBuilder/resources/ekinoks_128_75.png")));
		frame.setType(Type.POPUP);
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);

		frame.setBounds(100, 100, 486, 430);


		setIconImage(Toolkit.getDefaultToolkit().getImage(ChatKnoksLogin.class.getResource("/windowBuilder/resources/ekinoks_128_75.png")));
		frame.setTitle("Login");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{119, 120, 140, 119, 0};
		gridBagLayout.rowHeights = new int[]{42, 0, 44, 41, 39, 39, 39, 57, 45, 44, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		frame.setVisible(true);
		SwitchButton = new JButton("Switch Language");
		SwitchButton.addActionListener(new ActionListener() {
			boolean isTurkish =false;
			public void actionPerformed(ActionEvent e) {
				LanguageModifier a=new LanguageModifier();
				ResourceBundle rb=a.swapLoginLanguage(isTurkish);


				hintButton.setText(rb.getString("Add.hintButton.text"));
				SwitchButton.setText(rb.getString("Add.SwitchButton.text"));
				loginLabel.setText(rb.getString("Add.loginLabel.text"));
				resetButton.setText(rb.getString("Add.resetButton.text"));
				ipLabel.setText(rb.getString("Add.ipLabel.text"));

				connectButton.setText(rb.getString("Add.connectButton.text"));
				SwitchButton.setText(rb.getString("Add.SwitchButton.text"));
				signinButton.setText(rb.getString("Add.signinButton.text"));
				
				loginlbl.setText(rb.getString("Add.loginlbl.text"));
				isTurkish=(!isTurkish);


			}
		});
		GridBagConstraints gbc_SwitchButton = new GridBagConstraints();
		gbc_SwitchButton.fill = GridBagConstraints.VERTICAL;
		gbc_SwitchButton.anchor = GridBagConstraints.WEST;
		gbc_SwitchButton.insets = new Insets(0, 0, 5, 5);
		gbc_SwitchButton.gridx = 0;
		gbc_SwitchButton.gridy = 0;
		frame.getContentPane().add(SwitchButton, gbc_SwitchButton);
		
				iconLabel = new JLabel("");
				iconLabel.setIcon(new ImageIcon(ChatKnoksLogin.class.getResource("/windowBuilder/resources/ekinoks_128_75.png")));
				GridBagConstraints gbc_iconLabel = new GridBagConstraints();
				gbc_iconLabel.gridheight = 2;
				gbc_iconLabel.gridwidth = 2;
				gbc_iconLabel.insets = new Insets(0, 0, 5, 5);
				gbc_iconLabel.gridx = 1;
				gbc_iconLabel.gridy = 0;
				frame.getContentPane().add(iconLabel, gbc_iconLabel);
		
		signinButton = new JButton("Sign in");
		signinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ChatKnoksSignIn();
			}
		});
		GridBagConstraints gbc_signinButton = new GridBagConstraints();
		gbc_signinButton.fill = GridBagConstraints.BOTH;
		gbc_signinButton.insets = new Insets(0, 0, 5, 0);
		gbc_signinButton.gridx = 3;
		gbc_signinButton.gridy = 0;
		frame.getContentPane().add(signinButton, gbc_signinButton);
		
				hintButton = new JButton("Hint");
				hintButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IpField.setText("// eg. 192.158. 1.38");
						loginField.setText("// Jack");
					}
				});
				GridBagConstraints gbc_hintButton = new GridBagConstraints();
				gbc_hintButton.fill = GridBagConstraints.BOTH;
				gbc_hintButton.insets = new Insets(0, 0, 5, 0);
				gbc_hintButton.gridx = 3;
				gbc_hintButton.gridy = 1;
				frame.getContentPane().add(hintButton, gbc_hintButton);
		
		loginlbl = new JLabel("Login");
		loginlbl.setFont(new Font("Arial", Font.ITALIC, 14));
		loginlbl.setBackground(new Color(128, 128, 128));
		GridBagConstraints gbc_loginlbl = new GridBagConstraints();
		gbc_loginlbl.gridwidth = 2;
		gbc_loginlbl.insets = new Insets(0, 0, 5, 5);
		gbc_loginlbl.gridx = 1;
		gbc_loginlbl.gridy = 2;
		frame.getContentPane().add(loginlbl, gbc_loginlbl);

		loginLabel = new JLabel("Login Name :");
		GridBagConstraints gbc_loginLabel = new GridBagConstraints();
		gbc_loginLabel.anchor = GridBagConstraints.WEST;
		gbc_loginLabel.insets = new Insets(0, 0, 5, 5);
		gbc_loginLabel.gridx = 1;
		gbc_loginLabel.gridy = 3;
		frame.getContentPane().add(loginLabel, gbc_loginLabel);

		loginField = new JTextField();
		loginField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				loginField.requestFocusInWindow();
			}
		});
		GridBagConstraints gbc_loginField = new GridBagConstraints();
		gbc_loginField.gridwidth = 2;
		gbc_loginField.insets = new Insets(0, 0, 5, 5);
		gbc_loginField.fill = GridBagConstraints.BOTH;
		gbc_loginField.gridx = 1;
		gbc_loginField.gridy = 4;
		frame.getContentPane().add(loginField, gbc_loginField);
		loginField.setColumns(10);
				
						ipLabel = new JLabel("Password");
						GridBagConstraints gbc_ipLabel = new GridBagConstraints();
						gbc_ipLabel.anchor = GridBagConstraints.WEST;
						gbc_ipLabel.insets = new Insets(0, 0, 5, 5);
						gbc_ipLabel.gridx = 1;
						gbc_ipLabel.gridy = 5;
						frame.getContentPane().add(ipLabel, gbc_ipLabel);
		
				IpField = new JTextField();
				IpField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode()== KeyEvent.VK_ENTER) {
							login(loginField.getText(),IpField.getText());
						}
					}
				});
				GridBagConstraints gbc_IpField = new GridBagConstraints();
				gbc_IpField.gridwidth = 2;
				gbc_IpField.insets = new Insets(0, 0, 5, 5);
				gbc_IpField.fill = GridBagConstraints.BOTH;
				gbc_IpField.gridx = 1;
				gbc_IpField.gridy = 6;
				frame.getContentPane().add(IpField, gbc_IpField);
				IpField.setColumns(10);
		
				connectButton = new JButton("Connect");
				connectButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						login(loginField.getText(),IpField.getText());
					}
				});
				
				

				resetButton = new JButton("Reset");
				resetButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						reset();
					}
				});
				GridBagConstraints gbc_resetButton = new GridBagConstraints();
				gbc_resetButton.anchor = GridBagConstraints.NORTHWEST;
				gbc_resetButton.insets = new Insets(0, 0, 5, 5);
				gbc_resetButton.gridx = 1;
				gbc_resetButton.gridy = 8;
				frame.getContentPane().add(resetButton, gbc_resetButton);
				GridBagConstraints gbc_connectButton = new GridBagConstraints();
				gbc_connectButton.anchor = GridBagConstraints.NORTHEAST;
				gbc_connectButton.insets = new Insets(0, 0, 5, 5);
				gbc_connectButton.gridx = 2;
				gbc_connectButton.gridy = 8;
				frame.getContentPane().add(connectButton, gbc_connectButton);
	}
	public void reset() {

		IpField.setText("");
		loginField.setText("");
	}
	public void hint() {

		IpField.setText("eg. 192.158. 1.38");
		loginField.setText("Jack");
	}

	public void login(String userName, String password) {
		int portInt = 0;

		if(userName!=null && password!=null) {
			if(isValidLogin(userName,password)) {
				System.out.println(userName+" + "+password+" + "+portInt);
				frame.dispose();
				chatKnoks = new ChatKnoks(userName,password);
		
				scheduler.scheduleAtFixedRate(() -> chatKnoks.refresh(), 1, 1, TimeUnit.SECONDS);
				
//				new Thread(()->{
//					
//					
//					chatKnoks.getRefreshButton().doClick();
//					
//					
//				}).start();
			
			
			}else {
				System.out.println("Enter a valid password");
				reset();
			}
			
		}else {
			System.out.println("Please fill all areas");
		}
		
		


	}
	
	public boolean isValidLogin(String userName, String password) {
		ResultSet resultSet=null;
		Connection connection=Driver.Driver.connection;
		Statement statement;
		String UserPassword = null;
		try {
			statement = connection.createStatement();
			resultSet=statement.executeQuery("select password "
					+ "from public.user "
					+ "where username='"+userName+"';");
			resultSet.next();
			UserPassword=resultSet.getString(1);

		} catch (SQLException e) {
			System.out.println("Your username or password is wrong");
			return false;
		}
		if(UserPassword.equals(password)) {
			return true;
		}else {
			return false;
		}
		
		
	}


}