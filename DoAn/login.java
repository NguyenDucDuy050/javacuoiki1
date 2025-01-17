package DoAn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame implements ActionListener {
    private JLabel nameLayble,passwordLaybel, title;
    private JPasswordField password;
    private JTextField nameField;
    private JButton btn1;
    private JLabel logoLabel, companyLabel;

    public login() {
        super("Login");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        logoLabel = new JLabel(new ImageIcon(login.class.getResource("logo2.png")));
        logoLabel.setBounds(65, 120, 250, 100);
        this.add(logoLabel);
        companyLabel = new JLabel("TẬP ĐOÀN ĐIỆN LỰC VIỆT NAM");
        companyLabel.setBounds(80, 250, 300, 40);
        companyLabel.setFont(new Font("Arial", Font.BOLD, 17));
        this.add(companyLabel);

        title = new JLabel("PHAN MEM QUAN LY TIEN DIEN");
        title.setBounds(350,80,450,40);
        title.setFont(new Font("Arial" ,Font.BOLD, 25));
        this.add(title);

        nameLayble = new JLabel("Username");
        nameLayble.setBounds(350,150,100,30);
        passwordLaybel = new JLabel("Pass Word");
        passwordLaybel.setBounds(350,200,100,30);
        this.add(nameLayble);this.add(passwordLaybel);

        nameField = new JTextField();
        password = new JPasswordField();
        nameField.setBounds(440,150,300,30);
        password.setBounds(440,200,300,30);
        this.add(nameField);this.add(password);

        btn1 = new JButton("Dang Nhap");
        btn1.setBounds(495,250,100,30);
        this.add(btn1);
        btn1.addActionListener(this);
        getContentPane().setBackground(new Color(0,191,255));


    }

    public static void main(String[] args) {
        login x = new login();
        x.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(nameField.getText().equals("admin")
                && String.valueOf(password.getPassword()).equals("123")) {
            QuanLyTienDien x = new QuanLyTienDien();
            x.setVisible(true);
            this.setVisible(false);
            JOptionPane.showMessageDialog(rootPane, "Chao Mung Admin");
        }else {
            JOptionPane.showMessageDialog(rootPane, "Loi");
        }
    }
}
