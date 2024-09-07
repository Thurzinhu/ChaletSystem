package br.com.chaletmanagement.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import br.com.chaletmanagement.controller.ChaletController;
import br.com.chaletmanagement.model.Chalet;
import br.com.chaletmanagement.util.Util;

public class FrmChalet extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tblConsulta;
    private JTextField txtChaletCode;
    private JTextField txtLocation;
    private JTextField txtCapacity;
    private JTextField txtPeakSeasonPrice;
    private JTextField txtNormalPrice;
    private JLabel lblMessage;

    /**
     * Create the frame.
     */
    public FrmChalet() {
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
                String chaletCode = tblConsulta.getValueAt(row, 0).toString();
                String location = tblConsulta.getValueAt(row, 1).toString();
                String capacity = tblConsulta.getValueAt(row, 2).toString();
                String peakSeasonPrice = tblConsulta.getValueAt(row, 3).toString();
                String normalPrice = tblConsulta.getValueAt(row, 4).toString();
                txtChaletCode.setText(chaletCode);
                txtLocation.setText(location);
                txtCapacity.setText(capacity);
                txtPeakSeasonPrice.setText(peakSeasonPrice);
                txtNormalPrice.setText(normalPrice);
            }
        });
        tblConsulta.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Chalet Code", "Location", "Capacity", "Peak Season Price", "Normal Price" }) {
            Class[] columnTypes = new Class[] { String.class, String.class, Integer.class, Double.class, Double.class };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            boolean[] columnEditables = new boolean[] { false, false, false, false, false };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        tblConsulta.getColumnModel().getColumn(0).setResizable(false);
        tblConsulta.getColumnModel().getColumn(1).setResizable(false);
        tblConsulta.getColumnModel().getColumn(2).setResizable(false);
        tblConsulta.getColumnModel().getColumn(3).setResizable(false);
        tblConsulta.getColumnModel().getColumn(4).setResizable(false);
        scrollPane.setViewportView(tblConsulta);
        panel_2.setLayout(gl_panel_2);

        JButton btnInsert = new JButton("Insert");
        btnInsert.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Chalet chalet = null;
                ChaletController hc = new ChaletController();
                try {
                    chalet = mapFieldsToChalet();
                    lblMessage.setText("Message: " + hc.addChalet(chalet));
                } catch (Exception ex) {
                    lblMessage.setText("Message: " + ex.getMessage());
                }
                searchChalet();
            }
        });

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Chalet chalet = null;
                ChaletController hc = new ChaletController();
                try {
                    chalet = mapFieldsToChalet();
                    lblMessage.setText("Message: " + hc.updateChalet(chalet));
                } catch (Exception ex) {
                    lblMessage.setText("Message: " + ex.getMessage());
                }
                searchChalet();
            }
        });

        JButton btnDelete = new JButton("Delete");
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Chalet chalet = new Chalet();
                ChaletController hc = new ChaletController();
                chalet.setChaletCode(txtChaletCode.getText());
                Object[] options = { "Yes", "No" };
                int i = JOptionPane.showOptionDialog(null, "Do you want to delete this chalet: " + txtChaletCode.getText() + "?",
                        "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (JOptionPane.YES_OPTION == i) {
                    lblMessage.setText("Message: " + hc.deleteChalet(chalet));
                    searchChalet();
                }
            }
        });

        JButton btnSearch = new JButton("Search");
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchChalet();
            }
        });

        JButton btnClear = new JButton("Clear");
        btnClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtChaletCode.setText("");
                txtLocation.setText("");
                txtCapacity.setText("");
                txtPeakSeasonPrice.setText("");
                txtNormalPrice.setText("");
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
                FrmChalet.this.dispose();
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

        JLabel lblChaletCode = new JLabel("Chalet Code");

        txtChaletCode = new JTextField();
        txtChaletCode.setColumns(10);

        JLabel lblLocation = new JLabel("Location");

        txtLocation = new JTextField();
        txtLocation.setColumns(10);

        JLabel lblCapacity = new JLabel("Capacity");

        txtCapacity = new JTextField();
        txtCapacity.setColumns(10);

        JLabel lblPeakSeasonPrice = new JLabel("Peak Season Price");

        txtPeakSeasonPrice = new JTextField();
        txtPeakSeasonPrice.setColumns(10);

        JLabel lblNormalPrice = new JLabel("Normal Price");

        txtNormalPrice = new JTextField();
        txtNormalPrice.setColumns(10);

        lblMessage = new JLabel("Message:");
        lblMessage.setForeground(new Color(28, 113, 216));
        lblMessage.setFont(new Font("Dialog", Font.BOLD, 16));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
        			.addGap(501)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblChaletCode, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblCapacity))
        			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
        				.addComponent(txtCapacity, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtChaletCode, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblPeakSeasonPrice)
        				.addComponent(lblNormalPrice))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(txtPeakSeasonPrice, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(lblLocation)
        					.addGap(18)
        					.addComponent(txtLocation, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
        				.addComponent(txtNormalPrice, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(465, Short.MAX_VALUE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap(449, Short.MAX_VALUE)
        			.addComponent(lblMessage, GroupLayout.PREFERRED_SIZE, 900, GroupLayout.PREFERRED_SIZE)
        			.addGap(338))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblLocation)
        				.addComponent(txtLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtChaletCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblChaletCode)
        				.addComponent(txtPeakSeasonPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblPeakSeasonPrice))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtNormalPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNormalPrice, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblCapacity)
        				.addComponent(txtCapacity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(11)
        			.addComponent(lblMessage, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        panel.setLayout(gl_panel);

        searchChalet();
    }

    private void searchChalet()
    {
        ChaletController controller = new ChaletController();
        List<Chalet> chalets = controller.getAllChalets();
        DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
        tbm.setRowCount(0);
        for (Chalet chalet : chalets) 
        {
            tbm.addRow(new Object[] { chalet.getChaletCode(), chalet.getLocation(), chalet.getCapacity(), chalet.getPeakSeasonPrice(), chalet.getNormalPrice() });
        }
    }

    private Chalet mapFieldsToChalet() throws Exception 
    {
        Chalet chalet = new Chalet();
        
        String chaletCode = txtChaletCode.getText().trim();
        String location = txtLocation.getText().trim();
        String capacityStr = txtCapacity.getText().trim();
        String peakSeasonPriceStr = txtPeakSeasonPrice.getText().trim();
        String normalPriceStr = txtNormalPrice.getText().trim();
        
        // Validate chalet code
        if (chaletCode.isEmpty())
        {
            throw new Exception("Chalet Code Cannot Be Empty.");
        }
        chalet.setChaletCode(chaletCode);
        
        // Validate location
        if (location.isEmpty())
        {
            throw new Exception("Location Cannot Be Empty.");
        }
        chalet.setLocation(location);
        
        // Validate capacity
        int capacity;
        try
        {
            capacity = Integer.parseInt(capacityStr);
        } 
        catch (NumberFormatException e)
        {
            throw new Exception("Invalid Capacity. Must be an integer.");
        }
        chalet.setCapacity(capacity);
        
        // Validate peak season price
        double peakSeasonPrice;
        try
        {
            peakSeasonPrice = Double.parseDouble(peakSeasonPriceStr);
        }
        catch (NumberFormatException e)
        {
            throw new Exception("Invalid Peak Season Price. Must be a number.");
        }
        chalet.setPeakSeasonPrice(peakSeasonPrice);
        
        // Validate normal price
        double normalPrice;
        try
        {
            normalPrice = Double.parseDouble(normalPriceStr);
        } 
        catch (NumberFormatException e)
        {
            throw new Exception("Invalid Normal Price. Must be a number.");
        }
        chalet.setNormalPrice(normalPrice);
        
        return chalet;
    }

}
