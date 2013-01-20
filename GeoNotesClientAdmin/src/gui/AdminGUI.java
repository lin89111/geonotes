package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AdminGUI extends JFrame {

	private static final long serialVersionUID = 2919321929898956769L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JTextField txtPassword;
	private JTextField txtNote;
	private JTextField txtLatitude;
	private JTextField txtLongitude;
	private JTextField txtRoute;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI frame = new AdminGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(6, 1, 600, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDatabase = new JLabel("Database:");
		lblDatabase.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatabase.setBounds(6, 6, 80, 16);
		contentPane.add(lblDatabase);

		JLabel lblConnection = new JLabel("Connection:");
		lblConnection.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConnection.setBounds(6, 40, 80, 16);
		contentPane.add(lblConnection);

		JLabel lblRoute = new JLabel("Route:");
		lblRoute.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRoute.setBounds(6, 80, 80, 16);
		contentPane.add(lblRoute);

		JLabel lblNote = new JLabel("Note:");
		lblNote.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNote.setBounds(6, 119, 80, 16);
		contentPane.add(lblNote);

		JLabel lblStep = new JLabel("Step:");
		lblStep.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStep.setBounds(6, 194, 80, 16);
		contentPane.add(lblStep);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(91, 1, 117, 29);
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminActions.clearDatabase();
			}
		});
		contentPane.add(btnClear);

		JButton btnInitialize = new JButton("Initialize");
		btnInitialize.setBounds(220, 1, 117, 29);
		btnInitialize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminActions.initializeDatabase();
			}
		});
		contentPane.add(btnInitialize);

		txtLogin = new JTextField();
		txtLogin.setText("bob");
		txtLogin.setBounds(98, 34, 100, 28);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setText("leponge");
		txtPassword.setColumns(10);
		txtPassword.setBounds(210, 34, 100, 28);
		contentPane.add(txtPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(322, 35, 117, 29);
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminActions.login(txtLogin.getText(), txtPassword.getText());
			}
		});
		contentPane.add(btnLogin);

		JButton btnAddUser = new JButton("Add User");
		btnAddUser.setBounds(451, 35, 117, 29);
		btnAddUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminActions.addUser(txtLogin.getText(), txtPassword.getText());
			}
		});
		contentPane.add(btnAddUser);

		JButton btnAddRoute = new JButton("Add Route");
		btnAddRoute.setBounds(322, 75, 117, 29);
		btnAddRoute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminActions.addRoute(txtRoute.getText());
			}
		});
		contentPane.add(btnAddRoute);

		txtNote = new JTextField();
		txtNote.setText("Central Park");
		txtNote.setBounds(98, 113, 134, 28);
		contentPane.add(txtNote);
		txtNote.setColumns(10);

		JLabel lblLatitude = new JLabel("Latitude:");
		lblLatitude.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLatitude.setBounds(244, 119, 61, 16);
		contentPane.add(lblLatitude);

		txtLatitude = new JTextField();
		txtLatitude.setText("40.714623");
		txtLatitude.setBounds(317, 113, 90, 28);
		contentPane.add(txtLatitude);
		txtLatitude.setColumns(10);

		JLabel lblLongitude = new JLabel("Longitude:");
		lblLongitude.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLongitude.setBounds(419, 119, 70, 16);
		contentPane.add(lblLongitude);

		txtLongitude = new JTextField();
		txtLongitude.setText("-74.006605");
		txtLongitude.setColumns(10);
		txtLongitude.setBounds(501, 113, 90, 28);
		contentPane.add(txtLongitude);

		JButton btnDeleteRoute = new JButton("Delete Route");
		btnDeleteRoute.setBounds(451, 75, 117, 29);
		btnDeleteRoute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminActions.deleteRoute(txtRoute.getText());
			}
		});
		contentPane.add(btnDeleteRoute);

		JButton btnAddNote = new JButton("Add Note");
		btnAddNote.setBounds(98, 153, 117, 29);
		btnAddNote.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminActions.addNote(txtNote.getText(), txtLatitude.getText(),
						txtLongitude.getText());
			}
		});
		contentPane.add(btnAddNote);

		JButton btnDeleteNote = new JButton("Delete Note");
		btnDeleteNote.setBounds(220, 153, 117, 29);
		btnDeleteNote.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminActions.deleteNote(txtNote.getText());
			}
		});
		contentPane.add(btnDeleteNote);

		JLabel lblAddTheNote = new JLabel("add the note");
		lblAddTheNote.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddTheNote.setBounds(98, 194, 80, 16);
		contentPane.add(lblAddTheNote);

		JLabel lblToTheRoute = new JLabel("to the route");
		lblToTheRoute.setHorizontalAlignment(SwingConstants.CENTER);
		lblToTheRoute.setBounds(352, 194, 80, 16);
		contentPane.add(lblToTheRoute);

		JComboBox cBoxNote = new JComboBox();
		cBoxNote.setBounds(190, 190, 150, 27);
		contentPane.add(cBoxNote);

		JComboBox cBoxRoute = new JComboBox();
		cBoxRoute.setBounds(441, 190, 150, 27);
		contentPane.add(cBoxRoute);

		JButton btnAddStep = new JButton("Add Step");
		btnAddStep.setBounds(98, 222, 117, 29);
		contentPane.add(btnAddStep);

		txtRoute = new JTextField();
		txtRoute.setText("Route of Central Park");
		txtRoute.setColumns(10);
		txtRoute.setBounds(98, 74, 212, 28);
		contentPane.add(txtRoute);
	}
}