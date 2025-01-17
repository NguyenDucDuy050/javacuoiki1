package DoAn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class CapNhatChiSo extends JPanel {
    private static final String URL = "jdbc:mysql://localhost:3306/duy";
    private static final String USER = "root";
    private static final String PASS = "";
    JTable table;
    public CapNhatChiSo(QuanLyTienDien mainFrame) {
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelTop = new JPanel(null);
        panelTop.setPreferredSize(new Dimension(500,270));
        panel.setBackground(Color.white);
        JLabel title = new JLabel("Cap Nhat Chi So Dien", SwingConstants.CENTER);
        title.setBounds(0,0,1000,100);
        title.setOpaque(true);
        title.setBackground(Color.blue);
        title.setFont(new Font("Arial",Font.BOLD, 20));
        title.setForeground(Color.white);

        JLabel lblMaKH = new JLabel("Ma Khach Hang");
        lblMaKH.setBounds(200,110,130,20);
        JTextField txtMaKH = new JTextField();
        txtMaKH.setBounds(350,110,200,20);
        JLabel lblMaThang = new JLabel("Ma Thang");
        lblMaThang.setBounds(200,145,130,20);
        JTextField txtMaThang = new JTextField();
        txtMaThang.setBounds(350,145,200,20);
        JLabel lblCSC = new JLabel("Chi So Cu");
        lblCSC.setBounds(200,185,130,20);
        JTextField txtCSC = new JTextField();
        txtCSC.setBounds(350,185,200,20);
        JLabel lblCSM = new JLabel("Chi So Moi");
        lblCSM.setBounds(200,225,130,20);
        JTextField txtCSM = new JTextField();
        txtCSM.setBounds(350,225,200,20);

        JButton addbtn = new JButton("Them");
        addbtn.setBounds(570,120,100,30);
        addbtn.setBackground(Color.CYAN);
        JButton updatebtn = new JButton("Sua");
        updatebtn.setBounds(570,165,100,30);
        updatebtn.setBackground(Color.ORANGE);
        JButton deletebtn = new JButton("Xoa");
        deletebtn.setBounds(570,205,100,30);
        deletebtn.setBackground(Color.LIGHT_GRAY);

        String[] col = {"Ma Khach Hang","Ma Thang","Chi So Cu","chi So Cu"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        addbtn.addActionListener(e -> {
            String maKH = txtMaKH.getText();
            String maThang = txtMaThang.getText();
            String ChiSoCu = txtCSC.getText();
            String ChiSoMoi = txtCSM.getText();

            try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                String query = "INSERT INTO chisodien (maKH, maThang, ChiSoCu, ChiSoMoi) VALUES(?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1,maKH);
                pstmt.setString(2,maThang);
                pstmt.setString(3,ChiSoCu);
                pstmt.setString(4,ChiSoMoi);

                int kq = pstmt.executeUpdate();
                if(kq > 0) {
                    JOptionPane.showMessageDialog(this, "Them Khach Hang Thanh Cong");
                    loadStudent();

                    txtMaKH.setText("");
                    txtMaThang.setText("");
                    txtCSC.setText("");
                    txtCSM.setText("");

                }else {
                    JOptionPane.showMessageDialog(this,"Them Khach Hang That Bai");
                }

            }catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi " + ex.getMessage());
            }
        });
        updatebtn.addActionListener(e -> {
            String maKH = txtMaKH.getText();
            String maThang = txtMaThang.getText();
            String ChiSoCu = txtCSC.getText();
            String ChiSoMoi = txtCSM.getText();
            try(Connection conn=DriverManager.getConnection(URL,USER,PASS)) {
                String query = "UPDATE chisodien set maThang = ?, ChiSoCu = ?, ChiSoMoi = ? WHERE maKH = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1,maThang);
                pstmt.setString(2,ChiSoCu);
                pstmt.setString(3,ChiSoMoi);
                pstmt.setString(4,maKH);
                int kq = pstmt.executeUpdate();
                if(kq > 0) {
                    JOptionPane.showMessageDialog(this, "Cap Nhat Thanh Cong");
                    loadStudent();
                    txtMaKH.setText("");
                    txtMaThang.setText("");
                    txtCSC.setText("");
                    txtCSM.setText("");
                }
            }catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi " + ex.getMessage());
            }

        });
        deletebtn.addActionListener(e -> {
            String maKH = txtMaKH.getText();
            if (maKH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng để xóa.");
                return;
            }
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                String delequery = "DELETE FROM chisodien WHERE maKH = ?";
                PreparedStatement pstmt = conn.prepareStatement(delequery);
                pstmt.setString(1,maKH);
                int kq = pstmt.executeUpdate();
                if(kq > 0) {
                    JOptionPane.showMessageDialog(this, "Da Xoa Khach Hang Ma " + maKH);
                    loadStudent();
                    txtMaKH.setText("");
                }else {
                    JOptionPane.showMessageDialog(this, "Khong Tim Thay KH Ma " + maKH);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi " + ex.getMessage());
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panelTop.add(title);
        panelTop.add(lblMaKH);
        panelTop.add(txtMaKH);
        panelTop.add(lblMaThang);
        panelTop.add(txtMaThang);
        panelTop.add(lblCSC);
        panelTop.add(txtCSC);
        panelTop.add(lblCSM);
        panelTop.add(txtCSM);
        panelTop.add(addbtn);
        panelTop.add(updatebtn);
        panelTop.add(deletebtn);
        panel.add(panelTop, BorderLayout.NORTH);
        add(panel);
        loadStudent();


    }
    private void loadStudent() {
        try (Connection conn=DriverManager.getConnection(URL,USER,PASS)) {
            String query = "SELECT * FROM chisodien";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model1 = (DefaultTableModel) table.getModel();
            model1.setRowCount(0);
            while (rs.next()) {
                String maKH = rs.getString("MaKH");
                String maThang = rs.getString("MaThang");
                String ChiSoCu = rs.getString("ChiSoCu");
                String ChiSoMoi = rs.getString("ChiSoMoi");

                Object[] row = {maKH,maThang,ChiSoCu,ChiSoMoi};
                model1.addRow(row);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        CapNhatChiSo x = new CapNhatChiSo();
//        x.setVisible(true);
//    }
}
