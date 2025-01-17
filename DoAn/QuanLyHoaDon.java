package DoAn;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class QuanLyHoaDon extends JPanel {
    private static final String URL = "jdbc:mysql://localhost:3306/duy";
    private static final String USER = "root";
    private static final String PASS = "";
    JTable table;
    public QuanLyHoaDon(QuanLyTienDien mainFrame) {
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelTop = new JPanel(null);
        panelTop.setPreferredSize(new Dimension(500,100));
        panel.setBackground(Color.white);
        JLabel title = new JLabel("Quan Ly Hoa Don", SwingConstants.CENTER);
        title.setBounds(0,0,1000,100);
        title.setOpaque(true);
        title.setBackground(Color.blue);
        title.setFont(new Font("Arial",Font.BOLD, 20));
        title.setForeground(Color.white);
        JPanel panel1 = new JPanel(null);
        panel1.setBorder(BorderFactory.createTitledBorder("Thanh Toan"));
        panel1.setPreferredSize(new Dimension(250,180));

        JLabel maKHlbl = new JLabel("Ma Khach Hang");
        maKHlbl.setBounds(10,20,120,20);
        JLabel tenKHlbl = new JLabel("Ten Khach Hang");
        tenKHlbl.setBounds(10,50,120,20);
        JLabel LDTTlbl = new JLabel("LDTT");
        LDTTlbl.setBounds(10,80,80,20);
        JLabel tienlbl = new JLabel("Tong Tien");
        tienlbl.setBounds(10,110,80,20);
        JTextField txtmaKH = new JTextField();
        txtmaKH.setBounds(110,20,130,20);
        JTextField txttenKH = new JTextField();
        txttenKH.setBounds(110,50,130,20);
        JTextField txtLDTT = new JTextField();
        txtLDTT.setBounds(110,80,130,20);
        JTextField txttien = new JTextField();
        txttien.setBounds(110,110,130,20);
        JButton btn1 = new JButton("Thanh Toan");
        btn1.setBounds(90,155,120,20);
        JButton refresh = new JButton("Refresh");
        refresh.setBounds(90,200,120,20);

        String[] col = {"Ma Khach Hang","Ho Ten","Loai Dien","LDTT","Tien"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if(row != -1) {
                    String maKH = table.getValueAt(row, 0).toString();
                    String tenKH = table.getValueAt(row, 1).toString();
                    String LDTT = table.getValueAt(row, 3).toString();
                    String tien = table.getValueAt(row, 4).toString();

                    txtmaKH.setText(maKH);
                    txttenKH.setText(tenKH);
                    txtLDTT.setText(LDTT);
                    txttien.setText(tien);
                }
            }
        });
        btn1.addActionListener(e -> {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                String query = "UPDATE khachhang SET TrangThai = 'Đã thanh toán' WHERE MaKH = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, String.valueOf(txtmaKH.getText()));
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Thanh Toan Thanh Cong");
                    loadHoaDon();
                } else {
                    JOptionPane.showMessageDialog(this,"Khong Tim Thay Khach Hang");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,"this" + ex.getMessage());
            }
        });
        refresh.addActionListener(e -> {
            loadHoaDon();
        });

        panelTop.add(title);
        panel1.add(refresh);
        panel1.add(maKHlbl);
        panel1.add(btn1);
        panel1.add(tenKHlbl);
        panel1.add(LDTTlbl);
        panel1.add(tienlbl);
        panel1.add(txtLDTT);
        panel1.add(txtmaKH);
        panel1.add(txttenKH);
        panel1.add(txttien);
        panel.add(panel1, BorderLayout.EAST);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelTop, BorderLayout.NORTH);
        this.add(panel);
        loadHoaDon();
    }
    private void loadHoaDon() {
        try(Connection conn = DriverManager.getConnection(URL,USER,PASS)) {
            String query = "SELECT khachhang.MaKH, khachhang.TenKH, khachhang.LoaiDien, " +
                    "chisodien.ChiSoMoi - chisodien.ChiSoCu AS LDTT, " +
                    "((chisodien.ChiSoMoi - chisodien.ChiSoCu) * " +
                    " CASE " +
                    "   WHEN khachhang.loaiDien = 'Sinh hoạt' THEN 2500 " +
                    "   WHEN khachhang.loaiDien = 'Kinh doanh' THEN 3000 " +
                    " END) AS Tien " +
                    "FROM khachhang " +
                    "INNER JOIN chisodien ON khachhang.maKH = chisodien.maKH";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                String maKH = rs.getString("maKH");
                String tenKH = rs.getString("tenKH");
                String loaiDien = rs.getString("loaiDien");
                int LDTT = rs.getInt("LDTT");
                double Tien = rs.getDouble("Tien");

                Object[] row = {maKH, tenKH, loaiDien, LDTT, Tien};
                model.addRow(row);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        QuanLyTienDien x = new QuanLyTienDien();
        x.setVisible(true);
    }
}
