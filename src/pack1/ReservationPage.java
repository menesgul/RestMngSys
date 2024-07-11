package pack1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ReservationPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtDate;
    private JTextField txtHowMany;

    ReservationDB resdb = new ReservationDB();
    DefaultListModel<String> model;

    public void fillTheList() throws SQLException {
        model.removeAllElements();
        resdb.getRes().forEach(res -> {
            model.addElement(res.getresID() + " " +
                    res.getName() + " " +
                    res.getSurname() + " " +
                    res.getDate() + " " +
                    res.getHowManyPeople());
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReservationPage frame = new ReservationPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ReservationPage() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 655, 375);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        model = new DefaultListModel<String>();
        fillTheList();
        JList<String> list = new JList<String>(model);
        list.setBounds(22, 19, 607, 170);
        contentPane.add(list);

        JLabel lblResId = new JLabel("Res ID");
        lblResId.setBounds(32, 223, 61, 16);
        contentPane.add(lblResId);

        txtID = new JTextField();
        txtID.setBounds(105, 218, 130, 26);
        contentPane.add(txtID);
        txtID.setColumns(10);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(32, 255, 61, 16);
        contentPane.add(lblName);

        txtName = new JTextField();
        txtName.setColumns(10);
        txtName.setBounds(105, 250, 130, 26);
        contentPane.add(txtName);

        JLabel lblSurname = new JLabel("Surname");
        lblSurname.setBounds(32, 286, 61, 16);
        contentPane.add(lblSurname);

        txtSurname = new JTextField();
        txtSurname.setColumns(10);
        txtSurname.setBounds(105, 281, 130, 26);
        contentPane.add(txtSurname);

        JLabel lblDate = new JLabel("Date");
        lblDate.setBounds(292, 223, 61, 16);
        contentPane.add(lblDate);

        txtDate = new JTextField();
        txtDate.setColumns(10);
        txtDate.setBounds(365, 218, 130, 26);
        contentPane.add(txtDate);

        JLabel lblHowMany = new JLabel("How Many People");
        lblHowMany.setBounds(292, 255, 128, 16);
        contentPane.add(lblHowMany);

        txtHowMany = new JTextField();
        txtHowMany.setColumns(10);
        txtHowMany.setBounds(418, 250, 130, 26);
        contentPane.add(txtHowMany);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Reservat r = new Reservat();
                    r.setresID(Integer.parseInt(txtID.getText()));
                    r.setName(txtName.getText());
                    r.setSurname(txtSurname.getText());
                    r.setDate(txtDate.getText());
                    r.setHowManyPeople(txtHowMany.getText());
                    resdb.saveRes(r);
                    fillTheList();
                    JOptionPane.showMessageDialog(contentPane, "Reservation saved!");
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(contentPane, "Error: " + e2.getMessage());
                }
            }
        });
        btnAdd.setBounds(52, 318, 117, 29);
        contentPane.add(btnAdd);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtID.getText());
                    resdb.deleteRes(id);
                    fillTheList();
                    JOptionPane.showMessageDialog(contentPane, "Reservation deleted!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnDelete.setBounds(181, 318, 117, 29);
        contentPane.add(btnDelete);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Reservat r = new Reservat();
                    r.setresID(Integer.parseInt(txtID.getText()));
                    r.setName(txtName.getText());
                    r.setSurname(txtSurname.getText());
                    r.setDate(txtDate.getText());
                    r.setHowManyPeople(txtHowMany.getText());
                    resdb.deleteRes(r.getresID());
                    resdb.saveRes(r);
                    fillTheList();
                    JOptionPane.showMessageDialog(contentPane, "Reservation updated!");
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(contentPane, "Error: " + e2.getMessage());
                }
            }
        });
        btnUpdate.setBounds(310, 318, 117, 29);
        contentPane.add(btnUpdate);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame mf = new MainFrame();
                mf.setVisible(true);
                dispose();
            }
        });
        btnExit.setBounds(439, 318, 117, 29);
        contentPane.add(btnExit);
    }
}
