package DoAn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DanhSachThanhToan extends JPanel {
    private static final String URL = "jdbc:mysql://localhost:3306/duy";
    private static final String USER = "root";
    private static final String PASS = "";
    JTable tabledanop, tablechuanop;
    public DanhSachThanhToan(QuanLyTienDien mainFrame) {
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Danh Sach Ho Tieu Thu Da Nop/Chua Nop", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(1000,100));
        title.setOpaque(true);
        title.setBackground(Color.blue);
        title.setFont(new Font("Arial",Font.BOLD, 20));
        title.setForeground(Color.white);

        JPanel panel1 = new JPanel(null);
        panel1.setPreferredSize(new Dimension(100,25));
        JButton refreshbtn = new JButton("Refresh");
        refreshbtn.setBounds(400,0,80,20);
        JPanel tablePanel = new JPanel(new GridLayout(2,1,10,10));
        String[] col ={"Ma KH","Ho Ten","Gioi Tinh","Ngay Sinh","CMND","SDT","Ngay Dang Ki","Dia Chi","Loai Dien"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        tabledanop = new JTable(model);
        JScrollPane scrollPanedanop = new JScrollPane(tabledanop);
        scrollPanedanop.setBorder(BorderFactory.createTitledBorder("Danh Sach Da Nop"));

        DefaultTableModel model1 = new DefaultTableModel(col, 0);
        tablechuanop = new JTable(model1);
        JScrollPane scrollPanechuanop = new JScrollPane(tablechuanop);
        scrollPanechuanop.setBorder(BorderFactory.createTitledBorder("Danh Sach Chua Nop"));

        tablePanel.add(scrollPanechuanop);tablePanel.add(scrollPanedanop);
        panel1.add(refreshbtn);
        this.add(panel1, BorderLayout.SOUTH);
        this.add(title, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);
        loadKhachHang();


    }
    private void loadKhachHang() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String queryDaNop = "SELECT khachhang.MaKH, khachhang.TenKH, khachhang.GioiTinh, khachhang.NgaySinh, khachhang.CMND, khachhang.SDT, khachhang.NgayDangKi, khachhang.DiaChi, khachhang.LoaiDien " +
                    "FROM khachhang WHERE TrangThai = 'Đã thanh toán'";

            PreparedStatement pstmtDaNop = conn.prepareStatement(queryDaNop);
            ResultSet rsDaNop = pstmtDaNop.executeQuery();
            DefaultTableModel modelDaNop = (DefaultTableModel) tabledanop.getModel();
            modelDaNop.setRowCount(0);
            while (rsDaNop.next()) {
                Object[] row = {
                        rsDaNop.getString("MaKH"),
                        rsDaNop.getString("TenKH"),
                        rsDaNop.getString("GioiTinh"),
                        rsDaNop.getString("NgaySinh"),
                        rsDaNop.getString("CMND"),
                        rsDaNop.getString("SDT"),
                        rsDaNop.getString("NgayDangKi"),
                        rsDaNop.getString("DiaChi"),
                        rsDaNop.getString("LoaiDien")
                };
                modelDaNop.addRow(row);
            }

            String queryChuaNop = "SELECT khachhang.MaKH, khachhang.TenKH, khachhang.GioiTinh, khachhang.NgaySinh, khachhang.CMND, khachhang.SDT, khachhang.NgayDangKi, khachhang.DiaChi, khachhang.LoaiDien " +
                    "FROM khachhang " +
                    "WHERE khachhang.MaKH NOT IN (SELECT MaKH FROM khachhang WHERE TrangThai = 'Đã thanh toán')";
            PreparedStatement pstmtChuaNop = conn.prepareStatement(queryChuaNop);
            ResultSet rsChuaNop = pstmtChuaNop.executeQuery();
            DefaultTableModel modelChuaNop = (DefaultTableModel) tablechuanop.getModel();
            modelChuaNop.setRowCount(0);
            while (rsChuaNop.next()) {
                Object[] row = {
                        rsChuaNop.getString("MaKH"),
                        rsChuaNop.getString("TenKH"),
                        rsChuaNop.getString("GioiTinh"),
                        rsChuaNop.getString("NgaySinh"),
                        rsChuaNop.getString("CMND"),
                        rsChuaNop.getString("SDT"),
                        rsChuaNop.getString("NgayDangKi"),
                        rsChuaNop.getString("DiaChi"),
                        rsChuaNop.getString("LoaiDien")
                };
                modelChuaNop.addRow(row);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
