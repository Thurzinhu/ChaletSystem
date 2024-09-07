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

import br.com.chaletmanagement.controller.ChaletController;
import br.com.chaletmanagement.model.Chalet;
import br.com.chaletmanagement.util.Util;

public class FrmChalet extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tblChalet;
    private JTextField txtChaletCode;
    private JTextField txtLocation;
    private JTextField txtCapacity;
    private JTextField txtPeakSeasonPrice;
    private JTextField txtNormalPrice;
    private JLabel lblMessage;

    public FrmChalet() {
        setTitle("Chalet Management");
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

        tblChalet = new JTable();
        tblChalet.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Chalet Code", "Location", "Capacity", "Peak Season Price", "Normal Price" }
        ) {
            Class[] columnTypes = new Class[] { String.class, String.class, Integer.class, Double.class, Double.class };

            @Override
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblChalet.setRowHeight(30);
        tblChalet.setFont(new Font("Arial", Font.PLAIN, 14));
        scrollPane.setViewportView(tblChalet);

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
            public void mouseClicked(MouseEvent e) {
                Chalet chalet;
                ChaletController cc = new ChaletController();
                try {
                    chalet = mapFieldsToChalet();
                    lblMessage.setText("Message: " + cc.addChalet(chalet));
                } catch (Exception ex) {
                    lblMessage.setText("Message: " + ex.getMessage());
                }
                searchChalet();
            }
        });

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBackground(new Color(102, 205, 170));
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 12));
        btnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Chalet chalet;
                ChaletController cc = new ChaletController();
                try {
                    chalet = mapFieldsToChalet();
                    lblMessage.setText("Message: " + cc.updateChalet(chalet));
                } catch (Exception ex) {
                    lblMessage.setText("Message: " + ex.getMessage());
                }
                searchChalet();
            }
        });

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBackground(new Color(255, 69, 58));
        btnDelete.setFont(new Font("Arial", Font.BOLD, 12));
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Chalet chalet = new Chalet();
                ChaletController cc = new ChaletController();
                chalet.setChaletCode(txtChaletCode.getText());
                int i = JOptionPane.showConfirmDialog(null, "Do you want to delete this chalet with Code: " + txtChaletCode.getText() + "?",
                        "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (JOptionPane.YES_OPTION == i) {
                    lblMessage.setText("Message: " + cc.deleteChalet(chalet));
                    searchChalet();
                }
            }
        });

        JButton btnSearch = new JButton("Search");
        btnSearch.setBackground(new Color(173, 216, 230));
        btnSearch.setFont(new Font("Arial", Font.BOLD, 12));
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchChalet();
            }
        });

        JButton btnClear = new JButton("Clear");
        btnClear.setBackground(new Color(240, 230, 140));
        btnClear.setFont(new Font("Arial", Font.BOLD, 12));
        btnClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtChaletCode.setText("");
                txtLocation.setText("");
                txtCapacity.setText("");
                txtPeakSeasonPrice.setText("");
                txtNormalPrice.setText("");
                DefaultTableModel tbm = (DefaultTableModel) tblChalet.getModel();
                tbm.setRowCount(0);
            }
        });

        JButton btnExit = new JButton("Exit");
        btnExit.setBackground(new Color(255, 160, 122));
        btnExit.setFont(new Font("Arial", Font.BOLD, 12));
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrmChalet.this.dispose();
            }
        });

        tblChalet.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && tblChalet.getSelectedRow() != -1) {
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

        JLabel lblChaletCode = new JLabel("Chalet Code");
        lblChaletCode.setFont(new Font("Arial", Font.BOLD, 14));

        txtChaletCode = new JTextField();
        txtChaletCode.setColumns(10);

        JLabel lblLocation = new JLabel("Location");
        lblLocation.setFont(new Font("Arial", Font.BOLD, 14));

        txtLocation = new JTextField();
        txtLocation.setColumns(10);

        JLabel lblCapacity = new JLabel("Capacity");
        lblCapacity.setFont(new Font("Arial", Font.BOLD, 14));

        txtCapacity = new JTextField();
        txtCapacity.setColumns(10);

        JLabel lblPeakSeasonPrice = new JLabel("Peak Season Price");
        lblPeakSeasonPrice.setFont(new Font("Arial", Font.BOLD, 14));

        txtPeakSeasonPrice = new JTextField();
        txtPeakSeasonPrice.setColumns(10);

        JLabel lblNormalPrice = new JLabel("Normal Price");
        lblNormalPrice.setFont(new Font("Arial", Font.BOLD, 14));

        txtNormalPrice = new JTextField();
        txtNormalPrice.setColumns(10);

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
                                .addComponent(lblChaletCode)
                                .addComponent(lblLocation)
                                .addComponent(lblCapacity)
                                .addComponent(lblPeakSeasonPrice)
                                .addComponent(lblNormalPrice))
                            .addGap(18)
                            .addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING)
                                .addComponent(txtChaletCode)
                                .addComponent(txtLocation)
                                .addComponent(txtCapacity)
                                .addComponent(txtPeakSeasonPrice)
                                .addComponent(txtNormalPrice)))
                        .addComponent(lblMessage))
                    .addContainerGap())
        );
        gl_panelForm.setVerticalGroup(
            gl_panelForm.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelForm.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblChaletCode)
                        .addComponent(txtChaletCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblLocation)
                        .addComponent(txtLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblCapacity)
                        .addComponent(txtCapacity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPeakSeasonPrice)
                        .addComponent(txtPeakSeasonPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNormalPrice)
                        .addComponent(txtNormalPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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

    private void fillFieldsFromSelectedRow()
    {
        int selectedRow = tblChalet.getSelectedRow();
        txtChaletCode.setText(tblChalet.getValueAt(selectedRow, 0).toString());
        txtLocation.setText(tblChalet.getValueAt(selectedRow, 1).toString());
        txtCapacity.setText(tblChalet.getValueAt(selectedRow, 2).toString());
        txtPeakSeasonPrice.setText(tblChalet.getValueAt(selectedRow, 3).toString());
        txtNormalPrice.setText(tblChalet.getValueAt(selectedRow, 4).toString());
    }

    private Chalet mapFieldsToChalet() throws Exception 
    {
        Chalet chalet = new Chalet();
        
        chalet.setChaletCode(Util.validateAndGetString(txtChaletCode.getText().trim(), "Chalet Code"));
        chalet.setLocation(Util.validateAndGetString(txtLocation.getText().trim(), "Location"));
        chalet.setCapacity(Util.validateAndGetInteger(txtCapacity.getText().trim(), "Capacity"));
        chalet.setPeakSeasonPrice(Util.validateAndGetDouble(txtPeakSeasonPrice.getText().trim(), "Peak Season Price"));
        chalet.setNormalPrice(Util.validateAndGetDouble(txtNormalPrice.getText().trim(), "Normal Price"));
        
        return chalet;
    }

    private void searchChalet()
    {
        ChaletController cc = new ChaletController();
        List<Chalet> chalets = cc.getAllChalets();
        DefaultTableModel model = (DefaultTableModel) tblChalet.getModel();
        model.setRowCount(0);
        for (Chalet chalet : chalets)
        {
            model.addRow(new Object[] { chalet.getChaletCode(), chalet.getLocation(), chalet.getCapacity(),
                chalet.getPeakSeasonPrice(), chalet.getNormalPrice() });
        }
    }
}
