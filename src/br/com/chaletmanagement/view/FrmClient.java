package br.com.chaletmanagement.view;

import java.util.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class FrmClient extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tblClient;
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtBirthday;
    private JTextField txtAddress;
    private JTextField txtNeighborhood;
    private JTextField txtCity;
    private JTextField txtState;
    private JTextField txtPostalCode;
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
        tblClient.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Name", "Birthday", "Address", "Neighborhood", "City", "State", "Postal Code" }
        ) {
            Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class };

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
        gl_panelTable.setHorizontalGroup(
            gl_panelTable.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelTable.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panelTable.setVerticalGroup(
            gl_panelTable.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelTable.createSequentialGroup()
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addContainerGap())
        );
        panelTable.setLayout(gl_panelTable);

        JButton btnInsert = new JButton("Insert");
        btnInsert.setBackground(new Color(102, 205, 170));
        btnInsert.setFont(new Font("Arial", Font.BOLD, 12));
        btnInsert.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Client client;
                Address address;
                ClientController cc = new ClientController();
                try
                {
                    client = mapFieldsToClient();
                    address = mapFieldsToAddress();
                    lblMessage.setText("Message: " + cc.addClient(client, address));
                } 
                catch (Exception ex)
                {
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
                ClientController cc = new ClientController();
                try 
                {
                    client = mapFieldsToClient();
                    address = mapFieldsToAddress();
                    lblMessage.setText("Message: " + cc.updateClient(client, address));
                } 
                catch (Exception ex) 
                {
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
                client.setId(txtID.getText());
                int i = JOptionPane.showConfirmDialog(null, "Do you want to delete this client with ID: " + txtID.getText() + "?",
                        "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
                txtID.setText("");
                txtName.setText("");
                txtBirthday.setText("");
                txtAddress.setText("");
                txtNeighborhood.setText("");
                txtCity.setText("");
                txtState.setText("");
                txtPostalCode.setText("");
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
        gl_panelButtons.setHorizontalGroup(
            gl_panelButtons.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelButtons.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnInsert)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnUpdate)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnDelete)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnSearch)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnClear)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnExit)
                    .addContainerGap(542, Short.MAX_VALUE))
        );
        gl_panelButtons.setVerticalGroup(
            gl_panelButtons.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelButtons.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelButtons.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnInsert)
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete)
                        .addComponent(btnSearch)
                        .addComponent(btnClear)
                        .addComponent(btnExit))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelButtons.setLayout(gl_panelButtons);

        JLabel lblID = new JLabel("ID");
        lblID.setFont(new Font("Arial", Font.BOLD, 14));

        txtID = new JTextField();
        txtID.setColumns(10);

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

        lblMessage = new JLabel("Message: ");
        lblMessage.setFont(new Font("Arial", Font.BOLD, 14));
        lblMessage.setForeground(Color.RED);

        GroupLayout gl_panelForm = new GroupLayout(panelForm);
        gl_panelForm.setHorizontalGroup(
            gl_panelForm.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelForm.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panelForm.createSequentialGroup()
                            .addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblID)
                                .addComponent(lblName)
                                .addComponent(lblBirthday)
                                .addComponent(lblAddress)
                                .addComponent(lblNeighborhood)
                                .addComponent(lblCity)
                                .addComponent(lblState)
                                .addComponent(lblPostalCode))
                            .addGap(18)
                            .addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING)
                                .addComponent(txtID)
                                .addComponent(txtName)
                                .addComponent(txtBirthday)
                                .addComponent(txtAddress)
                                .addComponent(txtNeighborhood)
                                .addComponent(txtCity)
                                .addComponent(txtState)
                                .addComponent(txtPostalCode)))
                        .addComponent(lblMessage))
                    .addContainerGap())
        );
        gl_panelForm.setVerticalGroup(
            gl_panelForm.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelForm.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblID)
                        .addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblName)
                        .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblBirthday)
                        .addComponent(txtBirthday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblAddress)
                        .addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNeighborhood)
                        .addComponent(txtNeighborhood, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblCity)
                        .addComponent(txtCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblState)
                        .addComponent(txtState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPostalCode)
                        .addComponent(txtPostalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(lblMessage)
                    .addContainerGap())
        );
        panelForm.setLayout(gl_panelForm);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(panelForm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelButtons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelTable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelForm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panelTable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }

    private void searchClient() 
    {
        ClientController cc = new ClientController();
        List<Client> clients = cc.getAllClients();
        DefaultTableModel tbm = (DefaultTableModel) tblClient.getModel();
        tbm.setRowCount(0);

        for (Client client : clients) 
        {
            List<Address> addresses = cc.getAddressesByClientId(client);

            if (!addresses.isEmpty())
            {
                Address address = addresses.get(0);
                tbm.addRow(new Object[] {
                    client.getId(),
                    client.getName(),
                    client.getBirthday().toString(),
                    address.getAddress(),
                    address.getNeighborhood(),
                    address.getCity(),
                    address.getState(),
                    address.getPostalCode()
                });
            } 
            else 
            {
                tbm.addRow(new Object[] 
                {
                    client.getId(),
                    client.getName(),
                    client.getBirthday().toString(),
                    "", "", "", "", ""
                });
            }
        }
    }

    private void fillFieldsFromSelectedRow()
    {
        int selectedRow = tblClient.getSelectedRow();
        txtID.setText(tblClient.getValueAt(selectedRow, 0).toString());
        txtName.setText(tblClient.getValueAt(selectedRow, 1).toString());
        txtBirthday.setText(tblClient.getValueAt(selectedRow, 2).toString());
        txtAddress.setText(tblClient.getValueAt(selectedRow, 3).toString());
        txtNeighborhood.setText(tblClient.getValueAt(selectedRow, 4).toString());
        txtCity.setText(tblClient.getValueAt(selectedRow, 5).toString());
        txtState.setText(tblClient.getValueAt(selectedRow, 6).toString());
        txtPostalCode.setText(tblClient.getValueAt(selectedRow, 7).toString());
    }

    private Client mapFieldsToClient() throws Exception 
    {
        Client client = new Client();
        
        client.setName(Util.validateAndGetString(txtName.getText().trim(), "Name"));
        client.setId(Util.validateAndGetString(txtID.getText().trim(), "ID"));
        client.setBirthday(Util.validateAndGetDate(txtBirthday.getText().trim(), "Birthday"));
        
        return client;
    }

    private Address mapFieldsToAddress() throws Exception
    {
        Address address = new Address();
        address.setAddress(Util.validateAndGetString(txtAddress.getText().trim(), "Address"));
        address.setNeighborhood(Util.validateAndGetString(txtNeighborhood.getText().trim(), "Neighborhood"));
        address.setCity(Util.validateAndGetString(txtCity.getText().trim(), "City"));
        address.setState(Util.validateAndGetString(txtState.getText().trim(), "State"));
        address.setPostalCode(Util.validateAndGetString(txtPostalCode.getText().trim(), "Postal Code"));
        return address;
    }
}
