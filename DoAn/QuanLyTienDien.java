package DoAn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class QuanLyTienDien extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/duy";
    private static final String USER = "root";
    private static final String PASS = "";
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JTable table;
    public QuanLyTienDien() {
        super("Quan Ly Tien Dien");
        initUI();
        loadStudent();
    }
    private void initUI() {
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        createNavPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(createQuanLyThongTinPanel(), "QuanLyThongTin");
        cardPanel.add( new CapNhatChiSo(this), "CapNhatChiSo");
        cardPanel.add(new QuanLyHoaDon(this), "QuanLyHoaDon");
        cardPanel.add(new DanhSachThanhToan(this), "DanhSachThanhToan");
        add(cardPanel, BorderLayout.CENTER);

    }
    public void switchCard(String cardname) {
        cardLayout.show(cardPanel,cardname);
    }
    private void createNavPanel() {
        JPanel mainPanel = new JPanel(null);
        mainPanel.setPreferredSize(new Dimension(200, 700));
        mainPanel.setBackground(Color.lightGray);

        JLabel lbl = new JLabel("Quan Ly Tien Dien",  SwingConstants.CENTER);
        lbl.setOpaque(true);
        lbl.setBackground(Color.CYAN);
        lbl.setFont(new Font("Arial",Font.BOLD, 16));
        JButton btn1 = new JButton("Quan Ly Thong Tin");
        JButton btn2 = new JButton("Cap Nhat Chi So Dien");
        JButton btn3 = new JButton("Quan Ly Hoa Don");
        JButton btn4 = new JButton("Danh Sach Thanh Toan");
        lbl.setBounds(0,0,200,70);
        btn1.setBounds(0,75,200,50);
        btn2.setBounds(0,130,200,50);
        btn3.setBounds(0,185,200,50);
        btn4.setBounds(0,240,200,50);

        mainPanel.add(lbl);mainPanel.add(btn1);mainPanel.add(btn2);mainPanel.add(btn3);mainPanel.add(btn4);
        add(mainPanel);
        add(mainPanel, BorderLayout.WEST);

        btn1.addActionListener(e -> cardLayout.show(cardPanel, "QuanLyThongTin"));
        btn2.addActionListener(e -> cardLayout.show(cardPanel, "CapNhatChiSo"));
        btn3.addActionListener(e -> cardLayout.show(cardPanel, "QuanLyHoaDon"));
        btn4.addActionListener(e -> cardLayout.show(cardPanel, "DanhSachThanhToan"));


    }
    private JPanel createQuanLyThongTinPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel paneltop = new JPanel(null);
        paneltop.setPreferredSize(new Dimension(500, 270));
        panel.setBackground(Color.white);
        JLabel title = new JLabel("Thong Tin Ho Tieu Thu Dien",  SwingConstants.CENTER);
        title.setBounds(0,0,1000,100);
        title.setOpaque(true);
        title.setBackground(Color.blue);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.white);
        panel.add(title);
        JLabel lblMaKH = new JLabel("Ma Khach Hang");
        lblMaKH.setBounds(90, 110, 130, 20);

        JTextField txtMaKH = new JTextField();
        txtMaKH.setBounds(190, 110, 200, 20);

        JLabel lblTenKH = new JLabel("Ten Khach Hang");
        lblTenKH.setBounds(90,140,130,20);
        JTextField txtTenKH = new JTextField();
        txtTenKH.setBounds(190,140,200,20);

        JLabel lblCMND = new JLabel("CMND");
        lblCMND.setBounds(90,170,130,20);
        JTextField txtCMND = new JTextField();
        txtCMND.setBounds(190,170,200,20);

        JLabel lblDiaChi = new JLabel("Dia Chi");
        lblDiaChi.setBounds(90,200,130,20);
        JTextField txtDiaChi = new JTextField();
        txtDiaChi.setBounds(190,200,200,20);

        JLabel lblGioiTinh = new JLabel("Gioi Tinh");
        lblGioiTinh.setBounds(450,110,130,20);
        JRadioButton male = new JRadioButton("Nam");
        JRadioButton female = new JRadioButton("Nu");
        male.setBounds(530,110,100,20);
        female.setBounds(680,110,130,20);
        ButtonGroup group = new ButtonGroup();
        group.add(male);group.add(female);
        JLabel lblNgaySinh = new JLabel("Ngay Sinh");
        lblNgaySinh.setBounds(450,140,90,20);
        JTextField txtNgaySinh = new JTextField();
        txtNgaySinh.setBounds(540,140,110,20);
        JLabel lblDangKi = new JLabel("Ngay Dang Ki");
        lblDangKi.setBounds(660,140,100,20);
        JTextField txtDangKi = new JTextField();
        txtDangKi.setBounds(740,140,100,20);
        JLabel lblSDT = new JLabel("So Dien Thoai");
        lblSDT.setBounds(450,170,110,20);
        JTextField txtSDT= new JTextField();
        txtSDT.setBounds(540,170,300,20);
        JLabel lblLoaiDien = new JLabel("Loai Dien");
        lblLoaiDien.setBounds(450,200,110,20);
        String[] type = {"Sinh Hoat","Hanh Chinh"};
        JComboBox<String> typeBox = new JComboBox<>(type);
        typeBox.setBounds(540,200,300,20);
        typeBox.setBackground(Color.lightGray);
        JButton addbtn = new JButton("Them");
        addbtn.setBounds(110,230,100,25);
        addbtn.setBackground(Color.CYAN);
        JButton updatebtn = new JButton("Sua");
        updatebtn.setBounds(280,230,100,25);
        updatebtn.setBackground(Color.ORANGE);
        JButton deletebtn = new JButton("Xoa");
        deletebtn.setBounds(465,230,100,25);
        deletebtn.setBackground(Color.lightGray);
        JButton searchbtn = new JButton("Tim Kiem");
        searchbtn.setBounds(660,230,100,25);
        searchbtn.setBackground(Color.white);
        JButton refreshbtn = new JButton("Refresh");
        refreshbtn.setBounds(820, 230, 100, 25);
        refreshbtn.setBackground(Color.lightGray);
        paneltop.add(refreshbtn);

        String[] col = {"MÃ KH", "TÊN KH", "Gioi Tinh","Ngay Sinh","CMND", "SDT", "Ngay Dang Ki","Dia Chi","Loai Dien"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        addbtn.addActionListener(e -> {
            String maKH = txtMaKH.getText();
            String tenKH = txtTenKH.getText();
            String GioiTinh = male.isSelected() ? "Male" : "Female";
            String NgaySinh = txtNgaySinh.getText();
            String CMND = txtCMND.getText();
            String SDT = txtSDT.getText();
            String NgayDangKi = txtDangKi.getText();
            String DiaChi = txtDiaChi.getText();
            String LoaiDien = (String) typeBox.getSelectedItem();

            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                String query = "INSERT INTO khachhang (maKH, tenKH, GioiTinh, NgaySinh, CMND, SDT, NgayDangKi, DiaChi, LoaiDien) VALUES (?, ?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1,maKH);
                pstmt.setString(2,tenKH);
                pstmt.setString(3,GioiTinh);
                pstmt.setString(4,NgaySinh);
                pstmt.setString(5,CMND);
                pstmt.setString(6,SDT);
                pstmt.setString(7,NgayDangKi);
                pstmt.setString(8,DiaChi);
                pstmt.setString(9,LoaiDien);


                int rowsAffected=pstmt.executeUpdate();
                if(rowsAffected>0){
                    JOptionPane.showMessageDialog(this,"Them Khach Hang Thanh Cong");
                    loadStudent();

                    txtMaKH.setText("");
                    txtTenKH.setText("");
                    txtCMND.setText("");
                    txtDiaChi.setText("");
                    txtNgaySinh.setText("");
                    txtDangKi.setText("");
                    txtSDT.setText("");
                    typeBox.setSelectedIndex(0);
                    male.setSelected(false);
                    female.setSelected(false);


                }else{
                    JOptionPane.showMessageDialog(this,"Them Khach That Bai");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,"Loi "+ex.getMessage());
            }

        });


        deletebtn.addActionListener(e -> {
            String maKH = txtMaKH.getText();
            if (maKH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng để xóa.");
                return;
            }
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                String delequery = "DELETE FROM khachhang WHERE maKH = ?";
                PreparedStatement pstmt = conn.prepareStatement(delequery);
                pstmt.setString(1,maKH);
                int kq = pstmt.executeUpdate();
                if(kq > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Da Xoa Khach Hang Ma " + maKH);
                    loadStudent();
                    txtMaKH.setText("");
                }else {
                    JOptionPane.showMessageDialog(rootPane, "Khong Tim Thay KH Ma " + maKH);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi " + ex.getMessage());
            }
        });

        updatebtn.addActionListener(e -> {
            String maKH = txtMaKH.getText();
            String tenKH = txtTenKH.getText();
            String GioiTinh = male.isSelected() ? "Male" : "Female";
            String NgaySinh = txtNgaySinh.getText();
            String CMND = txtCMND.getText();
            String SDT = txtSDT.getText();
            String NgayDangKi = txtDangKi.getText();
            String DiaChi = txtDiaChi.getText();
            String LoaiDien = (String) typeBox.getSelectedItem();
            try(Connection conn = DriverManager.getConnection(URL, USER,PASS)) {
               String query = "UPDATE khachhang set tenKH = ?, GioiTinh = ? , Ngaysinh = ?, CMND = ? , SDT = ? , NgayDangKi = ? , DiaChi = ? , LoaiDien = ? WHERE maKH = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, tenKH);
                pstmt.setString(2,GioiTinh);
                pstmt.setString(3,NgaySinh);
                pstmt.setString(4,CMND);
                pstmt.setString(5, SDT);
                pstmt.setString(6,NgayDangKi);
                pstmt.setString(7,DiaChi);
                pstmt.setString(8,LoaiDien);
                pstmt.setString(9,maKH);

                int kq = pstmt.executeUpdate();
                if(kq > 0) {
                    JOptionPane.showMessageDialog(rootPane,"Cap Nhat Khach Hang Thanh Cong");
                    loadStudent();
                    txtMaKH.setText("");
                    txtTenKH.setText("");
                    txtCMND.setText("");
                    txtDiaChi.setText("");
                    txtNgaySinh.setText("");
                    txtDangKi.setText("");
                    txtSDT.setText("");
                    typeBox.setSelectedIndex(0);
                    male.setSelected(false);
                    female.setSelected(false);
                }
                else {
                    JOptionPane.showMessageDialog(rootPane, "Khong Tim Thay Khach Hang Ma " + maKH);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi " + ex.getMessage());
            }
        });

        searchbtn.addActionListener(e -> {
            try(Connection conn = DriverManager.getConnection(URL, USER,PASS)) {
                String maKH = txtMaKH.getText();
                String query = "SELECT * FROM khachhang WHERE maKH = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);

                pstmt.setString(1, maKH);
                ResultSet rs = pstmt.executeQuery();
                DefaultTableModel model2 = (DefaultTableModel) table.getModel();
                model2.setRowCount(0);

                if (rs.next()) {
                    String tenKH = rs.getString("TenKH");
                    String gioiTinh = rs.getString("GioiTinh");
                    String ngaySinh = rs.getString("NgaySinh");
                    String cmnd = rs.getString("CMND");
                    String sdt = rs.getString("SDT");
                    String ngayDangKi = rs.getString("NgayDangKi");
                    String diaChi = rs.getString("DiaChi");
                    String loaiDien = rs.getString("LoaiDien");

                    Object[] row = {maKH, tenKH, gioiTinh, ngaySinh, cmnd, sdt, ngayDangKi, diaChi, loaiDien};
                    model.addRow(row);
                    txtMaKH.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng mã " + maKH);
                }


            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        refreshbtn.addActionListener(e -> {
            loadStudent();
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        paneltop.add(title);
        paneltop.add(lblMaKH);
        paneltop.add(txtMaKH);
        paneltop.add(lblTenKH);
        paneltop.add(txtTenKH);
        paneltop.add(lblCMND);
        paneltop.add(txtCMND);
        paneltop.add(lblDiaChi);
        paneltop.add(txtDiaChi);
        paneltop.add(lblGioiTinh);
        paneltop.add(male);
        paneltop.add(female);
        paneltop.add(lblNgaySinh);
        paneltop.add(txtNgaySinh);
        paneltop.add(lblDangKi);
        paneltop.add(txtDangKi);
        paneltop.add(lblSDT);
        paneltop.add(txtSDT);
        paneltop.add(lblLoaiDien);
        paneltop.add(typeBox);
        paneltop.add(addbtn);
        paneltop.add(updatebtn);
        paneltop.add(deletebtn);
        paneltop.add(searchbtn);
        panel.add(paneltop, BorderLayout.NORTH);


        return panel;
    }
    private void loadStudent() {
        try (Connection conn=DriverManager.getConnection(URL,USER,PASS)) {
            String query = "SELECT * FROM khachhang";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                String maKH = rs.getString("MaKH");
                String tenKH = rs.getString("TenKH");
                String gioitinh = rs.getString("GioiTinh");
                String ngaysinh = rs.getString("NgaySinh");
                String cmnd = rs.getString("CMND");
                String sdt = rs.getString("SDT");
                String ngaydangki = rs.getString("NgayDangKi");
                String diachi = rs.getString("DiaChi");
                String loaidien = rs.getString("LoaiDien");

                Object[] row = {maKH, tenKH, gioitinh, ngaysinh, cmnd, sdt, ngaydangki , diachi, loaidien};
                model.addRow(row);
            }

        }catch (SQLException ex) {
            ex.printStackTrace();;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuanLyTienDien x = new QuanLyTienDien();
            x.setVisible(true);
        });

    }
}