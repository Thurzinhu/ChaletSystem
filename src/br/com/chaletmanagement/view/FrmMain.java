package br.com.chaletmanagement.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
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
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1227, 557);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnClientManagement = new JMenu("Client Management");
        menuBar.add(mnClientManagement);
        
        JMenuItem mntmAddClient = new JMenuItem("Add Client");
        mntmAddClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmClient addClientFrame = new FrmClient();
                addClientFrame.setLocationRelativeTo(null);
                addClientFrame.setVisible(true);
            }
        });
        mnClientManagement.add(mntmAddClient);
        
        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnClientManagement.add(mntmExit);

        JMenu mnchaletManagement = new JMenu("Chalet Management");
        menuBar.add(mnchaletManagement);
        
        JMenuItem mntmManagechalet = new JMenuItem("Manage chalet");
        mntmManagechalet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmChalet managechaletFrame = new FrmChalet();
                managechaletFrame.setLocationRelativeTo(null);
                managechaletFrame.setVisible(true);
            }
        });
        mnchaletManagement.add(mntmManagechalet);
        
        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);
        
        JMenuItem mntmAbout = new JMenuItem("About");
        mnHelp.add(mntmAbout);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JLabel lblchaletManagement = new JLabel("chalet Management System");
        lblchaletManagement.setFont(new Font("Dialog", Font.BOLD, 26));
        lblchaletManagement.setHorizontalAlignment(SwingConstants.CENTER);
        lblchaletManagement.setForeground(new Color(28, 113, 216));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(447)
                    .addComponent(lblchaletManagement)
                    .addContainerGap(564, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(181)
                    .addComponent(lblchaletManagement)
                    .addContainerGap(223, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
