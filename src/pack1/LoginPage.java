package pack1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsername;
    private JTextField txtPassword;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPage frame = new LoginPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 236, 260);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Username");
        lblNewLabel.setBounds(10, 47, 76, 14);
        contentPane.add(lblNewLabel);

        txtUsername = new JTextField();
        txtUsername.setBounds(87, 44, 86, 20);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        txtPassword = new JTextField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(87, 77, 86, 20);
        contentPane.add(txtPassword);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(10, 80, 76, 14);
        contentPane.add(lblPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = txtUsername.getText();
                    String password = txtPassword.getText();
                    UserServices userSer = new UserServices();
                    ArrayList<User> userList = userSer.getUsers();

                    if (userList
                            .stream()
                            .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                            .findFirst()
                            .isPresent()) {
                        MainFrame mf = new MainFrame();
                        mf.setVisible(true);
                        mf.setUser(userList
                                .stream()
                                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                                .findFirst()
                                .get());
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "User is not valid!");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnLogin.setBounds(84, 168, 89, 23);
        contentPane.add(btnLogin);
    }
}
