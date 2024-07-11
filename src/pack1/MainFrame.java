package pack1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    User activeUser;

    public void setUser(User u) {
        activeUser = u;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 475, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblWelcome = new JLabel("     Welcome to Restaurant Reservation Management System");
        lblWelcome.setBounds(39, 58, 412, 23);
        contentPane.add(lblWelcome);

        JLabel lblReservation = new JLabel("You can create a reservation here:");
        lblReservation.setBounds(142, 91, 219, 16);
        contentPane.add(lblReservation);

        JButton btnReservation = new JButton("Create Reservation");
        btnReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (activeUser.getUserRole().equals("admin")) {
                    ReservationPage lp = null;
					try {
						lp = new ReservationPage();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    lp.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "No admin rights!");
                }
            }
        });
        btnReservation.setBounds(142, 121, 156, 35);
        contentPane.add(btnReservation);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnExit.setBounds(156, 224, 141, 29);
        contentPane.add(btnExit);
    }
}
