package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

public class ClientGUI extends JFrame {

	private static final long serialVersionUID = -3515617517031295475L;

	private JPanel contentPane;
	private JTable table;

	private long idRoute;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ClientActions.init();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI frame = new ClientGUI();
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
	public ClientGUI() {
		setTitle("ClientGUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// /*
		Object[][] data = ClientActions.getRoutes();
		String[] columnNames = { "Id", "Route name" };
		table = new JTable(data, columnNames);
		// */table = new JTable();
		table.setBounds(20, 20, 410, 100);
		contentPane.add(table);

		final JButton btnLoadRoute = new JButton("Load route");
		btnLoadRoute.setEnabled(false);
		btnLoadRoute.setBounds(20, 132, 117, 29);
		contentPane.add(btnLoadRoute);

		final JButton btnNextStep = new JButton("Next step");
		btnNextStep.setEnabled(false);
		btnNextStep.setBounds(313, 132, 117, 29);
		contentPane.add(btnNextStep);

		final JLabel lblIdRoute = new JLabel("Id Route:");
		lblIdRoute.setBounds(20, 173, 410, 16);
		contentPane.add(lblIdRoute);

		final JLabel lblRouteName = new JLabel("Route name:");
		lblRouteName.setBounds(20, 201, 410, 16);
		contentPane.add(lblRouteName);

		final JLabel lblNbstep = new JLabel("Step number:");
		lblNbstep.setBounds(20, 229, 117, 16);
		contentPane.add(lblNbstep);

		final JLabel lblLatitude = new JLabel("Latitude:");
		lblLatitude.setBounds(20, 257, 200, 16);
		contentPane.add(lblLatitude);

		final JLabel lblLongitude = new JLabel("Longitude:");
		lblLongitude.setBounds(232, 257, 198, 16);
		contentPane.add(lblLongitude);

		final JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(20, 285, 410, 16);
		contentPane.add(lblDescription);

		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(
							ListSelectionEvent listSelectionEvent) {
						if (listSelectionEvent.getValueIsAdjusting())
							return;
						ListSelectionModel lsm = (ListSelectionModel) listSelectionEvent
								.getSource();
						if (lsm.isSelectionEmpty()) {
							btnLoadRoute.setEnabled(false);
							btnNextStep.setEnabled(false);
						} else {
							int selectedRow = lsm.getMinSelectionIndex();
							Object cellData = table.getModel().getValueAt(
									selectedRow, 0);
							idRoute = Integer.parseInt(cellData.toString());
							btnLoadRoute.setEnabled(true);
							btnNextStep.setEnabled(true);
						}
					}
				});

		btnLoadRoute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] data = ClientActions.selectRoute(idRoute);

				lblIdRoute.setText("Id Route: " + data[0]);
				lblRouteName.setText("Route name: " + data[1]);

				if (data.length > 2) {
					lblNbstep.setText("Step number: " + data[2]);
					lblLatitude.setText("Latitude: " + data[3]);
					lblLongitude.setText("Longitude: " + data[4]);
					lblDescription.setText("Description: " + data[5]);
				}
			}
		});

		btnNextStep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String data[] = ClientActions.getNextStepData();

				if (data == null) {
					System.out.println("No next step.");
					return;
				}

				lblNbstep.setText("Step number: " + data[0]);
				lblLatitude.setText("Latitude: " + data[1]);
				lblLongitude.setText("Longitude: " + data[2]);
				lblDescription.setText("Description: " + data[3]);
			}
		});
	}
}
