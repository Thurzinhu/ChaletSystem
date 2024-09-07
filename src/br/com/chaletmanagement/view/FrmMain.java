package br.com.chaletmanagement.view;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMain extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmMain frame = new FrmMain();
                    frame.setLocationRelativeTo(null);
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
    public FrmMain() {
        setTitle("Chalet Management System");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnHelp = new JMenu("Help");
        mnHelp.setFont(new Font("Arial", Font.BOLD, 14));
        menuBar.add(mnHelp);
        
        JMenuItem mntmAbout = new JMenuItem("About");
        mntmAbout.setIcon(new ImageIcon(FrmMain.class.getResource("../resources/icons/about.png")));  
        mntmAbout.setFont(new Font("Arial", Font.PLAIN, 12));
        mnHelp.add(mntmAbout);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(new Color(245, 245, 245)); 
        setContentPane(contentPane);
        
        JLabel lblChaletManagement = new JLabel("Chalet Heaven");
        lblChaletManagement.setFont(new Font("Harrington", Font.BOLD, 45));
        lblChaletManagement.setHorizontalAlignment(SwingConstants.CENTER);
        lblChaletManagement.setForeground(new Color(28, 113, 216));
        
        JButton btnManageClients = new JButton("Manage Clients");
        btnManageClients.setIcon(new ImageIcon(FrmMain.class.getResource("../resources/icons/manage-client.png"))); 
        btnManageClients.setFont(new Font("Arial", Font.PLAIN, 14));
        btnManageClients.setBackground(new Color(173, 216, 230));
        btnManageClients.setForeground(Color.BLACK);
        btnManageClients.setFocusPainted(false);
        btnManageClients.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmClient addClientFrame = new FrmClient();
                addClientFrame.setLocationRelativeTo(null);
                addClientFrame.setVisible(true);
            }
        });
        
        JButton btnManageChalets = new JButton("Manage Chalets");
        btnManageChalets.setIcon(new ImageIcon(FrmMain.class.getResource("../resources/icons/manage-chalet.png")));
        btnManageChalets.setFont(new Font("Arial", Font.PLAIN, 14));
        btnManageChalets.setBackground(new Color(144, 238, 144));
        btnManageChalets.setForeground(Color.BLACK);
        btnManageChalets.setFocusPainted(false);
        btnManageChalets.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmChalet manageChaletFrame = new FrmChalet();
                manageChaletFrame.setLocationRelativeTo(null);
                manageChaletFrame.setVisible(true);
            }
        });
        
        JButton btnManageBookings = new JButton("Manage Bookings");
        btnManageBookings.setIcon(new ImageIcon(FrmMain.class.getResource("../resources/icons/manage-booking.png")));
        btnManageBookings.setFont(new Font("Arial", Font.PLAIN, 14));
        btnManageBookings.setBackground(new Color(255, 182, 193));
        btnManageBookings.setForeground(Color.BLACK);
        btnManageBookings.setFocusPainted(false);
        btnManageBookings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmBooking manageBookingFrame = new FrmBooking();
                manageBookingFrame.setLocationRelativeTo(null);
                manageBookingFrame.setVisible(true);
            }
        });
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.CENTER)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(48)
        					.addComponent(btnManageClients, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
        					.addGap(56)
        					.addComponent(btnManageChalets, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
        					.addGap(56)
        					.addComponent(btnManageBookings, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblChaletManagement, GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(115)
        			.addComponent(lblChaletManagement)
        			.addGap(63)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnManageClients, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnManageChalets, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnManageBookings, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(249, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
