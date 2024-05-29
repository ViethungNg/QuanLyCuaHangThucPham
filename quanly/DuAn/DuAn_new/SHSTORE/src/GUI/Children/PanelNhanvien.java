/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Children;

import DAO.ChucVuDAO;
import DAO.NhanVienDAO;
//import Models.ChuVu;
import Models.ChucVu;
import Models.NhanVien;
import UI.Children.DaiLog.gifLoadDiaLog;
import Utils.JDBCHelper;
import Utils.MsgBox;
import Utils.Validation;
import Utils.XDate;
import Utils.XImage;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huuho
 */
public class PanelNhanvien extends javax.swing.JPanel {

    /**
     * Creates new form PanelNhanvien
     */
    public PanelNhanvien() {
        initComponents();
        init();
    }
    
    NhanVienDAO nhanVienDAO = new NhanVienDAO();
    ChucVuDAO chucVuDAO = new ChucVuDAO();
    byte[] fileAnhTam;
    
    public void init() {
        fillTable();
        fillcomboboxCV();
        rdoConViec.setSelected(true);
    }
    
    public void chonAnh() throws IOException {
        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(file);
            fileAnhTam = fis.readAllBytes();
            ImageIcon icon = XImage.readChon(file, 222, 237);

        }
        
    }
    
    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblLIstNhanvien.getModel();
        model.setRowCount(0);
        List<NhanVien> listNV = nhanVienDAO.selectAll();
        for (NhanVien nv : listNV) {
            Object rows[] = {nv.getMaNV(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getNgayVaoLam(), nv.getTenCV(), nv.getTTLamViec() ? "Đang làm" : "Nghĩ Làm"};
            model.addRow(rows);
        }
    }
    
    public void fillcomboboxCV() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboChucvu.getModel();
        model.removeAllElements();
        List<ChucVu> listCV = chucVuDAO.selectAll();
        for (ChucVu cv : listCV) {
            model.addElement(cv);
        }
    }
    
    public NhanVien getForm() {
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtMaNV.getText());
        nv.setHoTen(txtHoten.getText());
        nv.setGioiTinh(rdoNam.isSelected() ? true : false);
        nv.setNgaySinh(XDate.toString(dateNgaysinh.getDate(), "yyyy-MM-dd"));
        nv.setSdt(txtSDT.getText());
        nv.setEmail(txtEmail.getText());
        nv.setHinh(fileAnhTam);
        ChucVu cv = (ChucVu) cboChucvu.getSelectedItem();
        nv.setMaCV(cv.getMaCV());
        nv.setDiaChi(txtDiachi.getText());
        Date date = new Date();
        nv.setNgayVaoLam(XDate.toString(date, "yyyy-MM-dd"));
        nv.setMatKhau(txtMatkhau.getText());
        nv.setTTLamViec(rdoConViec.isSelected() ? true : false);
        fileAnhTam = null;
        return nv;
    }
    
    public void setForm(NhanVien nv) {
        txtMaNV.setText(nv.getMaNV());
        txtHoten.setText(nv.getHoTen());
        txtMatkhau.setText(nv.getMatKhau());
        if (nv.getNgaySinh() == null) {
            dateNgaysinh.setDate(null);
        } else {
            dateNgaysinh.setDate(XDate.toDate(nv.getNgaySinh(), "dd-MM-yyyy"));
        }
        
        txtSDT.setText(nv.getSdt());
        if (nv.isGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtDiachi.setText(nv.getDiaChi());
        txtEmail.setText(nv.getEmail());
        
        
        if (nv.getTTLamViec() == true) {
            rdoConViec.setSelected(true);
        } else {
            rdoNghiViec.setSelected(true);
        }
        
        ChucVu cv = chucVuDAO.selectbyID(nv.getMaCV());
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboChucvu.getModel();
        model.setSelectedItem(cv);
    }
    
    public void insert() {
        NhanVien nv = getForm();
        if (nhanVienDAO.selectbyID(nv.getMaNV()) != null) {
            MsgBox.alert(this, "Nhân viên đã tồn tại !");
            return;
        }
        try {
            nhanVienDAO.insert(nv);
            MsgBox.alert(this, "Thêm thành công");
            fillTable();
//            clearForm();
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm thất bại !");
        }
    }
    
    public void delete() {
        try {
            boolean ck = MsgBox.confrim(this, "Bạn thật sự muốn xóa ?");
            if (ck == true) {
                nhanVienDAO.delete(txtMaNV.getText());
                MsgBox.alert(this, "Xóa thành công");
            }
            fillTable();
            clearForm();
            
        } catch (Exception e) {
        }
        
    }
    
    public void update() {
        NhanVien nv = getForm();
        if (nhanVienDAO.selectbyID(nv.getMaNV()) == null) {
            MsgBox.alert(this, "Nhân viên không tồn tại !");
        } else {
            try {
                nhanVienDAO.update(nv);
                MsgBox.alert(this, "Cập nhật thành công");
                clearForm();
                fillTable();
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại");
                System.out.println(e);
            }
            
        }
    }
    
    public void clearForm() {
        NhanVien nv = new NhanVien();
        this.setForm(nv);
    }
    
    public boolean ckValidate() {
        if (Validation.isEmpty(txtMaNV, "Không được bỏ trống mã nhân viên")) {
            return false;
        }
        
        if (Validation.isEmpty(txtHoten, "Không được bỏ trống họ tên")) {
            return false;
        }
        
        if (Validation.isEmpty(txtMatkhau, "Không được bỏ trống mật khẩu")) {
            return false;
        }
        
        if (dateNgaysinh.getDate() == null) {
            MsgBox.alert(this, "Không được bỏ trông ngày sinh");
            return false;
        }
        
        if (dateNgaysinh.getDate().after(XDate.addDays(XDate.getDate(), -17))) {
            MsgBox.alert(this, "Không đủ tuôi đi làm");
            return false;
        }
        
        if (Validation.isEmpty(txtSDT, "Vui lòng nhập số điện thoại")) {
            return false;
        }
        
        if (Validation.isNumber(txtSDT, "Sai định dạng số điện thoại")) {
            return false;
        }
        if (txtSDT.getText().length() != 10) {
            MsgBox.alert(this, "Số điện thoại sai định dạng");
            return false;
        }
        if (Validation.isEmpty(txtDiachi, "Không để trống địa chỉ")) {
            return false;
        }
        
        if (Validation.isEmail(txtEmail, "Email sai định dạng")) {
            return false;
        }
        
        return true;
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btng = new javax.swing.ButtonGroup();
        bgrTinhTrang = new javax.swing.ButtonGroup();
        lblTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLIstNhanvien = new HELP.Table();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblNgaysinh1 = new javax.swing.JLabel();
        dateNgaysinh = new com.toedter.calendar.JDateChooser();
        lblSDT1 = new javax.swing.JLabel();
        txtSDT = new SWING_OTHER.MyTextField();
        lblGioitinh1 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        lblDiachi1 = new javax.swing.JLabel();
        txtDiachi = new SWING_OTHER.MyTextField();
        lblEmail1 = new javax.swing.JLabel();
        txtEmail = new SWING_OTHER.MyTextField();
        lblChucvu1 = new javax.swing.JLabel();
        cboChucvu = new javax.swing.JComboBox<>();
        btnThem = new SWING_OTHER.Button();
        btnCapNhat = new SWING_OTHER.Button();
        btnNew = new SWING_OTHER.Button();
        txtMatkhau = new javax.swing.JTextField();
        lblMa1 = new javax.swing.JLabel();
        txtMaNV = new SWING_OTHER.MyTextField();
        lblHoten1 = new javax.swing.JLabel();
        txtHoten = new SWING_OTHER.MyTextField();
        lblMatkhau1 = new javax.swing.JLabel();
        rdoConViec = new javax.swing.JRadioButton();
        rdoNghiViec = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 102, 102));
        lblTitle.setText("THÔNG TIN NHÂN VIÊN");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tblLIstNhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên Nhân Viên", "Giới Tính", "Ngày Vào Làm", "Chức Vụ", "Tình Trạng LV"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLIstNhanvien.setRowHeight(30);
        tblLIstNhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLIstNhanvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLIstNhanvien);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Danh Sách Nhân Viên");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        lblNgaysinh1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblNgaysinh1.setForeground(new java.awt.Color(0, 102, 102));
        lblNgaysinh1.setText("Ngày Sinh");

        dateNgaysinh.setDateFormatString("dd-MM-yyyy");
        dateNgaysinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/calendar.gif")));

        lblSDT1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblSDT1.setForeground(new java.awt.Color(0, 102, 102));
        lblSDT1.setText("Số Điện Thoại");

        lblGioitinh1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblGioitinh1.setForeground(new java.awt.Color(0, 102, 102));
        lblGioitinh1.setText("Giới Tính");

        btng.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        btng.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rdoNu.setText("Nữ");

        lblDiachi1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblDiachi1.setForeground(new java.awt.Color(0, 102, 102));
        lblDiachi1.setText("Địa Chỉ");

        lblEmail1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblEmail1.setForeground(new java.awt.Color(0, 102, 102));
        lblEmail1.setText("Email");

        lblChucvu1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblChucvu1.setForeground(new java.awt.Color(0, 102, 102));
        lblChucvu1.setText("Chức Vụ");

        btnThem.setBackground(new java.awt.Color(0, 102, 102));
        btnThem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(0, 102, 102));
        btnCapNhat.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(0, 102, 102));
        btnNew.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("Làm Mới");
        btnNew.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        lblMa1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblMa1.setForeground(new java.awt.Color(0, 102, 102));
        lblMa1.setText("Mã Nhân Viên");

        lblHoten1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblHoten1.setForeground(new java.awt.Color(0, 102, 102));
        lblHoten1.setText("Họ Tên");

        lblMatkhau1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblMatkhau1.setForeground(new java.awt.Color(0, 102, 102));
        lblMatkhau1.setText("Mật Khẩu");

        bgrTinhTrang.add(rdoConViec);
        rdoConViec.setText("Còn làm việc");

        bgrTinhTrang.add(rdoNghiViec);
        rdoNghiViec.setText("Nghĩ việc");

        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Tình trạng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblGioitinh1)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdoNam)
                        .addGap(20, 20, 20)
                        .addComponent(rdoNu))
                    .addComponent(txtMatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoConViec)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoNghiViec)))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblChucvu1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblMa1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblHoten1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblMatkhau1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblSDT1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblDiachi1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblEmail1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblNgaysinh1, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDiachi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboChucvu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dateNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(253, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(txtMatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblGioitinh1)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(178, 178, 178))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoConViec)
                    .addComponent(rdoNghiViec)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(lblMa1)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblHoten1))
                    .addGap(34, 34, 34)
                    .addComponent(lblMatkhau1)
                    .addGap(28, 28, 28)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(lblNgaysinh1)
                        .addComponent(dateNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSDT1))
                    .addGap(91, 91, 91)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(lblDiachi1)
                        .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(31, 31, 31)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(lblEmail1)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(lblChucvu1)
                        .addComponent(cboChucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(lblTitle)
                        .addGap(344, 344, 344))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        
        gifLoadDiaLog gif = new gifLoadDiaLog(null, false);
        gif.setVisible(true);
        new Thread() {
            @Override
            public void run() {
                if (ckValidate()) {
                    insert();
                    fillTable();
                    MsgBox.alert(null,"Thêm thành công");
                }
                
                gif.dispose();
            }
        }.start();
        

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        
        gifLoadDiaLog gif = new gifLoadDiaLog(null, false);
        gif.setVisible(true);
        new Thread() {
            @Override
            public void run() {
                if (ckValidate()) {
                    update();
                }
                
                gif.dispose();
            }
        }.start();

    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        clearForm();
    }//GEN-LAST:event_btnNewActionPerformed

    private void tblLIstNhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLIstNhanvienMouseClicked
        
        NhanVien nv = nhanVienDAO.selectbyID((String) tblLIstNhanvien.getValueAt(tblLIstNhanvien.getSelectedRow(), 0));
        setForm(nv);
    }//GEN-LAST:event_tblLIstNhanvienMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrTinhTrang;
    private SWING_OTHER.Button btnCapNhat;
    private SWING_OTHER.Button btnNew;
    private SWING_OTHER.Button btnThem;
    private javax.swing.ButtonGroup btng;
    private javax.swing.JComboBox<String> cboChucvu;
    private com.toedter.calendar.JDateChooser dateNgaysinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChucvu1;
    private javax.swing.JLabel lblDiachi1;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblGioitinh1;
    private javax.swing.JLabel lblHoten1;
    private javax.swing.JLabel lblMa1;
    private javax.swing.JLabel lblMatkhau1;
    private javax.swing.JLabel lblNgaysinh1;
    private javax.swing.JLabel lblSDT1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JRadioButton rdoConViec;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghiViec;
    private javax.swing.JRadioButton rdoNu;
    private HELP.Table tblLIstNhanvien;
    private SWING_OTHER.MyTextField txtDiachi;
    private SWING_OTHER.MyTextField txtEmail;
    private SWING_OTHER.MyTextField txtHoten;
    private SWING_OTHER.MyTextField txtMaNV;
    private javax.swing.JTextField txtMatkhau;
    private SWING_OTHER.MyTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
