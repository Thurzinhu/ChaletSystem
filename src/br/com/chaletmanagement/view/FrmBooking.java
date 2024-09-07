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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.chaletmanagement.controller.BookingController;
import br.com.chaletmanagement.model.Booking;
import br.com.chaletmanagement.util.Util;

public class FrmBooking extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tblBooking;
    private JTextField txtChaletId;
    private JTextField txtClientId;
    private JTextField txtStatus;
    private JTextField txtCheckInDate;
    private JTextField txtCheckOutDate;
    private JTextField txtNumberGuests;
    private JTextField txtDiscount;
    private JTextField txtTotalPrice;
    private JLabel lblMessage;

    public FrmBooking() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1200, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(240, 248, 255));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 250, 250));

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 250, 250));

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 250, 250));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);

        tblBooking = new JTable();
        tblBooking.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Chalet ID", "Client ID", "Status", "Check-In Date", "Check-Out Date", "Number of Guests", "Discount", "Total Price" }
        ) {
            Class[] columnTypes = new Class[] { Integer.class, Integer.class, String.class, String.class, String.class, Integer.class, Double.class, Double.class };

            @Override
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblBooking.setRowHeight(30);
        tblBooking.setFont(new Font("Arial", Font.PLAIN, 14));
        scrollPane.setViewportView(tblBooking);

        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
            gl_panel_2.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_2.setVerticalGroup(
            gl_panel_2.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2.createSequentialGroup()
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addContainerGap())
        );
        panel_2.setLayout(gl_panel_2);

        JButton btnInsert = new JButton("Insert");
        btnInsert.setBackground(new Color(102, 205, 170));
        btnInsert.setFont(new Font("Arial", Font.BOLD, 12));
        btnInsert.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Booking booking;
                BookingController bc = new BookingController();
                try {
                    booking = mapFieldsToBooking();
                    lblMessage.setText("Message: " + bc.addBooking(booking));
                } catch (Exception ex) {
                    lblMessage.setText("Message: " + ex.getMessage());
                }
                searchBooking();
            }
        });

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBackground(new Color(102, 205, 170));
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 12));
        btnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Booking booking;
                BookingController bc = new BookingController();
                try {
                    booking = mapFieldsToBooking();
                    lblMessage.setText("Message: " + bc.updateBooking(booking));
                } catch (Exception ex) {
                    lblMessage.setText("Message: " + ex.getMessage());
                }
                searchBooking();
            }
        });

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBackground(new Color(255, 69, 58));
        btnDelete.setFont(new Font("Arial", Font.BOLD, 12));
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Booking booking = new Booking();
                BookingController bc = new BookingController();
                booking.setChaletId(Integer.parseInt(txtChaletId.getText()));
                int i = JOptionPane.showConfirmDialog(null, "Do you want to delete this booking for Chalet ID: " + txtChaletId.getText() + "?",
                        "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (JOptionPane.YES_OPTION == i) {
                    lblMessage.setText("Message: " + bc.deleteBooking(booking));
                    searchBooking();
                }
            }
        });

        JButton btnSearch = new JButton("Search");
        btnSearch.setBackground(new Color(173, 216, 230));
        btnSearch.setFont(new Font("Arial", Font.BOLD, 12));
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchBooking();
            }
        });

        JButton btnClear = new JButton("Clear");
        btnClear.setBackground(new Color(240, 230, 140));
        btnClear.setFont(new Font("Arial", Font.BOLD, 12));
        btnClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtChaletId.setText("");
                txtClientId.setText("");
                txtStatus.setText("");
                txtCheckInDate.setText("");
                txtCheckOutDate.setText("");
                txtNumberGuests.setText("");
                txtDiscount.setText("");
                txtTotalPrice.setText("");
                DefaultTableModel tbm = (DefaultTableModel) tblBooking.getModel();
                tbm.setRowCount(0);
            }
        });

        JButton btnExit = new JButton("Exit");
        btnExit.setBackground(new Color(255, 160, 122));
        btnExit.setFont(new Font("Arial", Font.BOLD, 12));
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrmBooking.this.dispose();
            }
        });

        tblBooking.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && tblBooking.getSelectedRow() != -1) {
                    fillFieldsFromSelectedRow();
                }
            }
        });

        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
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
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnInsert)
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete)
                        .addComponent(btnSearch)
                        .addComponent(btnClear)
                        .addComponent(btnExit))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);

        JLabel lblChaletId = new JLabel("Chalet ID");
        lblChaletId.setFont(new Font("Arial", Font.BOLD, 14));

        txtChaletId = new JTextField();
        txtChaletId.setColumns(10);

        JLabel lblClientId = new JLabel("Client ID");
        lblClientId.setFont(new Font("Arial", Font.BOLD, 14));

        txtClientId = new JTextField();
        txtClientId.setColumns(10);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 14));

        txtStatus = new JTextField();
        txtStatus.setColumns(10);

        JLabel lblCheckInDate = new JLabel("Check-In Date");
        lblCheckInDate.setFont(new Font("Arial", Font.BOLD, 14));

        txtCheckInDate = new JTextField();
        txtCheckInDate.setColumns(10);

        JLabel lblCheckOutDate = new JLabel("Check-Out Date");
        lblCheckOutDate.setFont(new Font("Arial", Font.BOLD, 14));

        txtCheckOutDate = new JTextField();
        txtCheckOutDate.setColumns(10);

        JLabel lblNumberGuests = new JLabel("Number of Guests");
        lblNumberGuests.setFont(new Font("Arial", Font.BOLD, 14));

        txtNumberGuests = new JTextField();
        txtNumberGuests.setColumns(10);

        JLabel lblDiscount = new JLabel("Discount");
        lblDiscount.setFont(new Font("Arial", Font.BOLD, 14));

        txtDiscount = new JTextField();
        txtDiscount.setColumns(10);

        JLabel lblTotalPrice = new JLabel("Total Price");
        lblTotalPrice.setFont(new Font("Arial", Font.BOLD, 14));

        txtTotalPrice = new JTextField();
        txtTotalPrice.setColumns(10);

        lblMessage = new JLabel("Message:");
        lblMessage.setFont(new Font("Arial", Font.BOLD, 14));
        lblMessage.setForeground(Color.RED);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblChaletId)
                                .addComponent(lblClientId)
                                .addComponent(lblStatus)
                                .addComponent(lblCheckInDate)
                                .addComponent(lblCheckOutDate)
                                .addComponent(lblNumberGuests)
                                .addComponent(lblDiscount)
                                .addComponent(lblTotalPrice))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(txtChaletId, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(txtClientId, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(txtStatus, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(txtCheckInDate, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(txtCheckOutDate, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(txtNumberGuests, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(txtDiscount, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(txtTotalPrice, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                        .addComponent(lblMessage))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblChaletId)
                        .addComponent(txtChaletId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblClientId)
                        .addComponent(txtClientId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblStatus)
                        .addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblCheckInDate)
                        .addComponent(txtCheckInDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblCheckOutDate)
                        .addComponent(txtCheckOutDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNumberGuests)
                        .addComponent(txtNumberGuests, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblDiscount)
                        .addComponent(txtDiscount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblTotalPrice)
                        .addComponent(txtTotalPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(lblMessage)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }

    
    private void fillFieldsFromSelectedRow()
    {
        int selectedRow = tblBooking.getSelectedRow();
        txtChaletId.setText(tblBooking.getValueAt(selectedRow, 0).toString());
        txtClientId.setText(tblBooking.getValueAt(selectedRow, 1).toString());
        txtStatus.setText(tblBooking.getValueAt(selectedRow, 2).toString());
        txtCheckInDate.setText(tblBooking.getValueAt(selectedRow, 3).toString());
        txtCheckOutDate.setText(tblBooking.getValueAt(selectedRow, 4).toString());
        txtNumberGuests.setText(tblBooking.getValueAt(selectedRow, 5).toString());
        txtDiscount.setText(tblBooking.getValueAt(selectedRow, 6).toString());
        txtTotalPrice.setText(tblBooking.getValueAt(selectedRow, 7).toString());
    }

    private Booking mapFieldsToBooking() throws Exception {
        Booking booking = new Booking();
        try {
            booking.setChaletId(Integer.parseInt(txtChaletId.getText()));
            booking.setClientId(Integer.parseInt(txtClientId.getText()));
            booking.setStatus(txtStatus.getText());
            booking.setCheckInDate(Util.validateAndGetDate(txtCheckInDate.getText(), "Check In Date"));
            booking.setCheckOutDate(Util.validateAndGetDate(txtCheckOutDate.getText(), "Check Out Date"));
            booking.setNumberGuests(Integer.parseInt(txtNumberGuests.getText()));
            booking.setDiscount(Double.parseDouble(txtDiscount.getText()));
            booking.setTotalPrice(Double.parseDouble(txtTotalPrice.getText()));
        } catch (NumberFormatException e) {
            throw new Exception("Please enter valid numeric values.");
        }
        return booking;
    }

    private void searchBooking() {
        DefaultTableModel tbm = (DefaultTableModel) tblBooking.getModel();
        BookingController bc = new BookingController();
        List<Booking> bookings = bc.getAllBookings();
        tbm.setRowCount(0);
        for (Booking b : bookings) {
            tbm.addRow(new Object[] { b.getChaletId(), b.getClientId(), b.getStatus(), b.getCheckInDate(), b.getCheckOutDate(), b.getNumberGuests(), b.getDiscount(), b.getTotalPrice() });
        }
    }
}
