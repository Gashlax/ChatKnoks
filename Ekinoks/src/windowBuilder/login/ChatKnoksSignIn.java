package windowBuilder.login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import windowBuilder.localisation.LanguageModifier;

public class ChatKnoksSignIn {

	private JFrame frame;
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField passwordField;
	private JButton SwitchButton;
	private JButton hintButton;
	private JLabel iconLabel;
	private JLabel usernameLabel;
	private JLabel paswordLabel;
	private JButton resetButton;
	private JButton RegisterButton;
	private JLabel signInlbl;
	private JButton LoginButton;



	public ChatKnoksSignIn() {
		initialize();
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

				}

			}
		});
		frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				nameField.requestFocusInWindow();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});

		frame.setBackground(Color.DARK_GRAY);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ChatKnoksLogin.class.getResource("/windowBuilder/resources/ekinoks_128_75.png")));
		frame.setType(Type.POPUP);
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);

		frame.setBounds(100, 100, 486, 430);


		//setIconImage(Toolkit.getDefaultToolkit().getImage(ChatKnoksLogin.class.getResource("/windowBuilder/resources/ekinoks_128_75.png")));
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
				usernameLabel.setText(rb.getString("Add.loginLabel.text"));
				resetButton.setText(rb.getString("Add.resetButton.text"));
				paswordLabel.setText(rb.getString("Add.ipLabel.text"));
				RegisterButton.setText(rb.getString("Add.connectButton.text"));
				SwitchButton.setText(rb.getString("Add.SwitchButton.text"));
				LoginButton.setText(rb.getString("Add.LoginButton.text"));
				signInlbl.setText(rb.getString("Add.signInlbl.text"));
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

		LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ChatKnoksLogin();
			}
		});
		GridBagConstraints gbc_LoginButton = new GridBagConstraints();
		gbc_LoginButton.fill = GridBagConstraints.BOTH;
		gbc_LoginButton.insets = new Insets(0, 0, 5, 0);
		gbc_LoginButton.gridx = 3;
		gbc_LoginButton.gridy = 0;
		frame.getContentPane().add(LoginButton, gbc_LoginButton);

		hintButton = new JButton("Hint");
		hintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField.setText("// eg. 192.158. 1.38");
				nameField.setText("// Jack");
			}
		});
		GridBagConstraints gbc_hintButton = new GridBagConstraints();
		gbc_hintButton.fill = GridBagConstraints.BOTH;
		gbc_hintButton.insets = new Insets(0, 0, 5, 0);
		gbc_hintButton.gridx = 3;
		gbc_hintButton.gridy = 1;
		frame.getContentPane().add(hintButton, gbc_hintButton);

		signInlbl = new JLabel("Sign In");
		signInlbl.setFont(new Font("Arial", Font.ITALIC, 14));
		signInlbl.setBackground(new Color(128, 128, 128));
		GridBagConstraints gbc_signInlbl = new GridBagConstraints();
		gbc_signInlbl.gridwidth = 2;
		gbc_signInlbl.insets = new Insets(0, 0, 5, 5);
		gbc_signInlbl.gridx = 1;
		gbc_signInlbl.gridy = 2;
		frame.getContentPane().add(signInlbl, gbc_signInlbl);

		usernameLabel = new JLabel("Register Username :");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.anchor = GridBagConstraints.WEST;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 1;
		gbc_usernameLabel.gridy = 3;
		frame.getContentPane().add(usernameLabel, gbc_usernameLabel);

		nameField = new JTextField();
		nameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				nameField.requestFocusInWindow();
			}
		});
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 2;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.BOTH;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 4;
		frame.getContentPane().add(nameField, gbc_nameField);
		nameField.setColumns(10);

		paswordLabel = new JLabel("Password");
		GridBagConstraints gbc_paswordLabel = new GridBagConstraints();
		gbc_paswordLabel.anchor = GridBagConstraints.WEST;
		gbc_paswordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_paswordLabel.gridx = 1;
		gbc_paswordLabel.gridy = 5;
		frame.getContentPane().add(paswordLabel, gbc_paswordLabel);

		passwordField = new JTextField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER) {
					createUser(nameField.getText(),passwordField.getText());
				}
			}
		});
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 6;
		frame.getContentPane().add(passwordField, gbc_passwordField);
		passwordField.setColumns(10);

		RegisterButton = new JButton("Register!");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createUser(nameField.getText(),passwordField.getText());
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
		GridBagConstraints gbc_RegisterButton = new GridBagConstraints();
		gbc_RegisterButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_RegisterButton.insets = new Insets(0, 0, 5, 5);
		gbc_RegisterButton.gridx = 2;
		gbc_RegisterButton.gridy = 8;
		frame.getContentPane().add(RegisterButton, gbc_RegisterButton);
	}
	public void reset() {
		passwordField.setText("");
		nameField.setText("");
	}
	public boolean createUser(String userName, String password) {
		Connection connection=Driver.Driver.connection;
		Statement statement;
		int updater=0;
		if(userName!=null && password!=null) {
			System.out.println("asdasd");
			try {

				statement = connection.createStatement();
				updater=statement.executeUpdate("INSERT INTO public.user (username, password) "+
						"values ('"+userName+"','"+password+"');");

				System.out.println("Valid username and password.");
				return true;
			} catch (SQLException e) {
				System.out.println("Your username or password is wrong");
				reset();
				return false;
			}
			
		}else {
			reset();
			return false;
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


