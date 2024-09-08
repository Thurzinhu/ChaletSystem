package br.com.chaletmanagement.view;

import java.util.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.chaletmanagement.model.Address;
import br.com.chaletmanagement.util.Util;
import br.com.chaletmanagement.controller.ClientController;
import br.com.chaletmanagement.model.Client;
import br.com.chaletmanagement.model.Phone;

public class FrmClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblClient;
	private JTextField txtRG;
	private JTextField txtName;
	private JTextField txtBirthday;
	private JTextField txtAddress;
	private JTextField txtNeighborhood;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtPostalCode;
	private JTextField txtPhoneType;
	private JTextField txtPhoneNumber;
	private JLabel lblMessage;

	public FrmClient() {
		setTitle("Client Management");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(240, 248, 255));

		JPanel panelForm = new JPanel();
		panelForm.setBackground(new Color(255, 250, 250));

		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(new Color(255, 250, 250));

		JPanel panelTable = new JPanel();
		panelTable.setBackground(new Color(255, 250, 250));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);

		tblClient = new JTable();
		tblClient.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Name", "Birthday", "Address",
				"Neighborhood", "City", "State", "Postal Code", "Phone Number" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class, String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblClient.setRowHeight(30);
		tblClient.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(tblClient);

		GroupLayout gl_panelTable = new GroupLayout(panelTable);
		gl_panelTable.setHorizontalGroup(gl_panelTable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTable.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE).addContainerGap()));
		gl_panelTable.setVerticalGroup(
				gl_panelTable.createParallelGroup(Alignment.LEADING).addGroup(gl_panelTable.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE).addContainerGap()));
		panelTable.setLayout(gl_panelTable);

		JButton btnInsert = new JButton("Insert");
		btnInsert.setBackground(new Color(102, 205, 170));
		btnInsert.setFont(new Font("Arial", Font.BOLD, 12));
		btnInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Client client;
				Address address;
				Phone phone;
				ClientController cc = new ClientController();
				try {
					client = mapFieldsToClient();
					address = mapFieldsToAddress();
					phone = mapFieldsToPhone();
					lblMessage.setText("Message: " + cc.addClient(client, address, phone));
				} catch (Exception ex) {
					lblMessage.setText("Message: " + ex.getMessage());
				}
				searchClient();
			}
		});

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(102, 205, 170));
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 12));
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Client client;
				Address address;
				Phone phone;
				ClientController cc = new ClientController();
				try {
					client = mapFieldsToClient();
					address = mapFieldsToAddress();
					phone = mapFieldsToPhone();
					lblMessage.setText("Message: " + cc.updateClient(client, address, phone));
				} catch (Exception ex) {
					lblMessage.setText("Message: " + ex.getMessage());
				}
				searchClient();
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(255, 69, 58));
		btnDelete.setFont(new Font("Arial", Font.BOLD, 12));
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Client client = new Client();
				ClientController cc = new ClientController();
				client.setRG(txtRG.getText());
				int i = JOptionPane.showConfirmDialog(null,
						"Do you want to delete this client with ID: " + txtRG.getText() + "?", "Delete",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.YES_OPTION == i) {
					lblMessage.setText("Message: " + cc.deleteClient(client));
					searchClient();
				}
			}
		});

		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(173, 216, 230));
		btnSearch.setFont(new Font("Arial", Font.BOLD, 12));
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchClient();
			}
		});

		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(new Color(240, 230, 140));
		btnClear.setFont(new Font("Arial", Font.BOLD, 12));
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtRG.setText("");
				txtName.setText("");
				txtBirthday.setText("");
				txtAddress.setText("");
				txtNeighborhood.setText("");
				txtCity.setText("");
				txtState.setText("");
				txtPostalCode.setText("");
				txtPhoneType.setText("");
				txtPhoneNumber.setText("");
				DefaultTableModel tbm = (DefaultTableModel) tblClient.getModel();
				tbm.setRowCount(0);
			}
		});

		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(255, 160, 122));
		btnExit.setFont(new Font("Arial", Font.BOLD, 12));
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmClient.this.dispose();
			}
		});

		tblClient.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && tblClient.getSelectedRow() != -1) {
					fillFieldsFromSelectedRow();
				}
			}
		});

		GroupLayout gl_panelButtons = new GroupLayout(panelButtons);
		gl_panelButtons.setHorizontalGroup(gl_panelButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelButtons.createSequentialGroup().addContainerGap().addComponent(btnInsert)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnUpdate)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnDelete)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSearch)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnClear)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnExit)
						.addContainerGap(542, Short.MAX_VALUE)));
		gl_panelButtons.setVerticalGroup(gl_panelButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelButtons.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelButtons.createParallelGroup(Alignment.BASELINE).addComponent(btnInsert)
								.addComponent(btnUpdate).addComponent(btnDelete).addComponent(btnSearch)
								.addComponent(btnClear).addComponent(btnExit))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelButtons.setLayout(gl_panelButtons);

		JLabel lblID = new JLabel("RG");
		lblID.setFont(new Font("Arial", Font.BOLD, 14));

		txtRG = new JTextField();
		txtRG.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial", Font.BOLD, 14));

		txtName = new JTextField();
		txtName.setColumns(10);

		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setFont(new Font("Arial", Font.BOLD, 14));

		txtBirthday = new JTextField();
		txtBirthday.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Arial", Font.BOLD, 14));

		txtAddress = new JTextField();
		txtAddress.setColumns(10);

		JLabel lblNeighborhood = new JLabel("Neighborhood");
		lblNeighborhood.setFont(new Font("Arial", Font.BOLD, 14));

		txtNeighborhood = new JTextField();
		txtNeighborhood.setColumns(10);

		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Arial", Font.BOLD, 14));

		txtCity = new JTextField();
		txtCity.setColumns(10);

		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Arial", Font.BOLD, 14));

		txtState = new JTextField();
		txtState.setColumns(10);

		JLabel lblPostalCode = new JLabel("Postal Code");
		lblPostalCode.setFont(new Font("Arial", Font.BOLD, 14));

		txtPostalCode = new JTextField();
		txtPostalCode.setColumns(10);

		JLabel lblPhoneType = new JLabel("Phone Type");
		lblPhoneType.setFont(new Font("Arial", Font.BOLD, 14));

		txtPhoneType = new JTextField();
		txtPhoneType.setColumns(10);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Arial", Font.BOLD, 14));

		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setColumns(10);

		lblMessage = new JLabel("Message: ");
		lblMessage.setFont(new Font("Arial", Font.BOLD, 14));
		lblMessage.setForeground(Color.RED);

		GroupLayout gl_panelForm = new GroupLayout(panelForm);
		gl_panelForm.setHorizontalGroup(gl_panelForm.createParallelGroup(Alignment.LEADING).addGroup(gl_panelForm
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING).addGroup(gl_panelForm
						.createSequentialGroup()
						.addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING).addComponent(lblID)
								.addComponent(lblName).addComponent(lblBirthday).addComponent(lblAddress)
								.addComponent(lblNeighborhood).addComponent(lblCity).addComponent(lblState)
								.addComponent(lblPostalCode).addComponent(lblPhoneType).addComponent(lblPhoneNumber))
						.addGap(18)
						.addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING).addComponent(txtRG)
								.addComponent(txtName).addComponent(txtBirthday).addComponent(txtAddress)
								.addComponent(txtNeighborhood).addComponent(txtCity).addComponent(txtState)
								.addComponent(txtPostalCode).addComponent(txtPhoneType).addComponent(txtPhoneNumber)))
						.addComponent(lblMessage))
				.addContainerGap()));
		gl_panelForm.setVerticalGroup(gl_panelForm.createParallelGroup(Alignment.LEADING).addGroup(gl_panelForm
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE).addComponent(lblID).addComponent(txtRG,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE).addComponent(lblName).addComponent(
						txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE).addComponent(lblBirthday).addComponent(
						txtBirthday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE).addComponent(lblAddress).addComponent(
						txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE).addComponent(lblNeighborhood)
						.addComponent(txtNeighborhood, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE).addComponent(lblCity).addComponent(
						txtCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE).addComponent(lblState).addComponent(
						txtState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelForm
						.createParallelGroup(Alignment.BASELINE).addComponent(lblPostalCode).addComponent(txtPostalCode,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE).addComponent(lblPhoneType).addComponent(
						txtPhoneType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE).addComponent(lblPhoneNumber)
						.addComponent(txtPhoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblMessage).addContainerGap()));
		panelForm.setLayout(gl_panelForm);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panelForm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelButtons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(
						gl_contentPane
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(
										gl_contentPane.createSequentialGroup().addContainerGap()
												.addComponent(panelForm, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(panelButtons, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(panelTable, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}

	private void searchClient() {
		ClientController cc = new ClientController();
		List<Client> clients = cc.getAllClients();
		DefaultTableModel tbm = (DefaultTableModel) tblClient.getModel();
		tbm.setRowCount(0);

		for (Client client : clients) {
			Address address = cc.getAddressByClientId(client);
			Phone phone = cc.getPhoneByClientId(client);
			if (address != null && phone != null) {
				tbm.addRow(new Object[] { client.getRG(), client.getName(),
						Util.formatDateToDDMMYYYY(client.getBirthday()), address.getAddress(),
						address.getNeighborhood(), address.getCity(), address.getState(), address.getPostalCode(),
						phone.getPhoneNumber() });
			} else {
				tbm.addRow(new Object[] { client.getRG(), client.getName(), client.getBirthday().toString(), "", "", "",
						"", "", "" });
			}
		}
	}

	private void fillFieldsFromSelectedRow() {
		int selectedRow = tblClient.getSelectedRow();
		txtRG.setText(tblClient.getValueAt(selectedRow, 0).toString());
		txtName.setText(tblClient.getValueAt(selectedRow, 1).toString());
		try {
			LocalDate birthday = Util.mapGUIDateToLocalDate(tblClient.getValueAt(selectedRow, 2).toString());
			txtBirthday.setText(Util.formatDateToDDMMYYYY(birthday));
		} catch (Exception e) {
			txtBirthday.setText("");
		}
		txtAddress.setText(tblClient.getValueAt(selectedRow, 3).toString());
		if (tblClient.getValueAt(selectedRow, 4) != null)
			txtNeighborhood.setText(tblClient.getValueAt(selectedRow, 4).toString());
		txtCity.setText(tblClient.getValueAt(selectedRow, 5).toString());
		txtState.setText(tblClient.getValueAt(selectedRow, 6).toString());
		if (tblClient.getValueAt(selectedRow, 7) != null)
			txtPostalCode.setText(tblClient.getValueAt(selectedRow, 7).toString());
		txtPhoneNumber.setText(tblClient.getValueAt(selectedRow, 8).toString());
	}

	private Client mapFieldsToClient() throws Exception {
		Client client = new Client();
		client.setRG(Util.validateAndGetString(txtRG.getText().trim(), "RG"));
		client.setName(Util.validateAndGetString(txtName.getText().trim(), "Name"));
		client.setBirthday(Util.validateAndGetDate(txtBirthday.getText().trim(), "Birthday"));
		return client;
	}

	private Address mapFieldsToAddress() throws Exception {
		Address address = new Address();
		address.setAddress(Util.validateAndGetString(txtAddress.getText().trim(), "Address"));
		// address.setNeighborhood(Util.validateAndGetString(txtNeighborhood.getText().trim(),
		// "Neighborhood"));
		address.setCity(Util.validateAndGetString(txtCity.getText().trim(), "City"));
		address.setState(Util.validateAndGetString(txtState.getText().trim(), "State"));
		// address.setPostalCode(Util.validateAndGetString(txtPostalCode.getText().trim(),
		// "Postal Code"));
		return address;
	}

	private Phone mapFieldsToPhone() throws Exception {
		Phone phone = new Phone();
		// phone.setPhoneType(Util.validateAndGetString(txtPhoneType.getText().trim(),
		// "Phone Type"));
		phone.setPhoneNumber(Util.validateAndGetString(txtPhoneNumber.getText().trim(), "Phone Number"));
		return phone;
	}
}
