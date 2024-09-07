package br.com.chaletmanagement.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;

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
import javax.swing.table.DefaultTableModel;

import br.com.chaletmanagement.controller.ClientController;
import br.com.chaletmanagement.model.Client;
import br.com.chaletmanagement.util.Util;

public class FrmClient extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tblConsulta;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtBirthday;
    private JLabel lblMessage;

    /**
     * Create the frame.
     */
    public FrmClient() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1021, 537);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        JPanel panel = new JPanel();

        JPanel panel_1 = new JPanel();

        JPanel panel_2 = new JPanel();
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 1009, GroupLayout.PREFERRED_SIZE)
                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 1002, GroupLayout.PREFERRED_SIZE)
                                .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 1010, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        JScrollPane scrollPane = new JScrollPane();
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 995, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2.createSequentialGroup()
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        tblConsulta = new JTable();
        tblConsulta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Integer row = tblConsulta.getSelectedRow();
                String id = tblConsulta.getValueAt(row, 0).toString();
                String name = tblConsulta.getValueAt(row, 1).toString();
                String birthday = tblConsulta.getValueAt(row, 2).toString();
                txtId.setText(id);
                txtName.setText(name);
                txtBirthday.setText(birthday);
            }
        });
        tblConsulta.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Name", "Birthday" }) {
            Class[] columnTypes = new Class[] { String.class, String.class, String.class };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            boolean[] columnEditables = new boolean[] { false, false, false };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        tblConsulta.getColumnModel().getColumn(0).setResizable(false);
        tblConsulta.getColumnModel().getColumn(1).setResizable(false);
        tblConsulta.getColumnModel().getColumn(2).setResizable(false);
        scrollPane.setViewportView(tblConsulta);
        panel_2.setLayout(gl_panel_2);

        JButton btnInsert = new JButton("Insert");
        btnInsert.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	Client client = null;
            	ClientController cc = new ClientController();
            	try 
            	{
            		client = mapFieldsToClient();
            		lblMessage.setText("Message: " + cc.addClient(client));
            	}
            	catch (Exception ex)
            	{
            		lblMessage.setText("Message: " + ex.getMessage());
            	}
                searchClient();
            }
        });

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	Client client = null;
            	ClientController cc = new ClientController();
            	try 
            	{
            		client = mapFieldsToClient();
            		lblMessage.setText("Message: " + cc.updateClient(client));
            	}
            	catch (Exception ex)
            	{
            		lblMessage.setText("Message: " + ex.getMessage());
            	}
                searchClient();
            }
        });

        JButton btnDelete = new JButton("Delete");
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Client client = new Client();
                ClientController cc = new ClientController();
                client.setId(txtId.getText());
                Object[] options = { "Yes", "No" };
                int i = JOptionPane.showOptionDialog(null, "Do you want to delete this client: " + txtName.getText() + "?",
                        "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (JOptionPane.YES_OPTION == i) {
                    lblMessage.setText("Message: " + cc.deleteClient(client));
                    searchClient();
                }
            }
        });

        JButton btnSearch = new JButton("Search");
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchClient();
            }
        });

        JButton btnClear = new JButton("Clear");
        btnClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtId.setText("");
                txtName.setText("");
                txtBirthday.setText("");
                DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
                for (int i = tbm.getRowCount() - 1; i >= 0; i--) {
                    tbm.removeRow(i);
                }
            }
        });

        JButton btnExit = new JButton("Exit");
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrmClient.this.dispose();
            }
        });
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(btnInsert)
                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(btnUpdate)
                        .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnDelete)
                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSearch)
                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(btnClear)
                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(btnExit)
                        .addContainerGap(408, Short.MAX_VALUE)));
        gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
                        .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnInsert)
                                .addComponent(btnUpdate).addComponent(btnDelete).addComponent(btnSearch)
                                .addComponent(btnClear).addComponent(btnExit))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        panel_1.setLayout(gl_panel_1);

        JLabel lblId = new JLabel("ID");

        txtId = new JTextField();
        txtId.setColumns(10);

        JLabel lblName = new JLabel("Name");

        txtName = new JTextField();
        txtName.setColumns(10);

        JLabel lblBirthday = new JLabel("Birthday");

        txtBirthday = new JTextField();
        txtBirthday.setColumns(10);

        lblMessage = new JLabel("Message:");
        lblMessage.setForeground(new Color(28, 113, 216));
        lblMessage.setFont(new Font("Dialog", Font.BOLD, 16));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(lblName)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(lblBirthday)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtBirthday, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
        				.addComponent(lblMessage, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblId)
        				.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblName)
        				.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtBirthday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblBirthday))
        			.addGap(26)
        			.addComponent(lblMessage, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

        searchClient();
    }

    private void searchClient()
    {
        ClientController cc = new ClientController();
        List<Client> clients = cc.getAllClients();
        DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
        tbm.setRowCount(0);
        for (Client client : clients) {
            tbm.addRow(new Object[] { client.getId(), client.getName(), client.getBirthday().toString() });
        }
    }

    private Client mapFieldsToClient() throws Exception {
        Client client = new Client();
        
        String name = txtName.getText().trim();
        String id = txtId.getText().trim();
        String birthdayStr = txtBirthday.getText().trim();
        
        // Validate name
        if (name.isEmpty())
        {
            throw new Exception("Name Cannot Be Empty.");
        }
        client.setName(name);
        
        // Validate ID
        if (id.isEmpty())
        {
            throw new Exception("ID Cannot Be Empty.");
        }
        client.setId(id);
        
        // validate date
        int[] formattedDate;
        try 
        {
            formattedDate = Util.mapGUIDateToLocalDate(birthdayStr);
            if (formattedDate.length != 3)
            {
                throw new Exception("Invalid birthday format. Please use DD-MM-YYYY.");
            }
        } 
        catch (NumberFormatException | ArrayIndexOutOfBoundsException e)
        {
            throw new Exception("Invalid date format. Please use DD-MM-YYYY.");
        }
        
        if (formattedDate[1] < 1 || formattedDate[1] > 12)
        {
            throw new Exception("Invalid month. Must be between 1 and 12.");
        }
        if (formattedDate[0] < 1 || formattedDate[0] > 31)
        {
            throw new Exception("Invalid day. Must be between 1 and 31.");
        }
        
        client.setBirthday(LocalDate.of(formattedDate[2], formattedDate[1], formattedDate[0]));
        
        return client;
    }

}
