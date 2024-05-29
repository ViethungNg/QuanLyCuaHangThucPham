/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Children;

import DAO.GGHoaDonDAO;
import DAO.GGSanPhamDAO;
import DAO.KeHangDAO;
import DAO.LoaiHangDAO;
import DAO.SanPhamDAO;
import Models.GGHoaDon;
import Models.GGSanPham;
import Models.LoaiHang;
import Models.SanPham;
import GUI.MainJF;
import Utils.Auth;
import Utils.DDTienTe;
import Utils.MsgBox;
import Utils.Validation;
import Utils.XDate;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TIEN SY
 */
public class KhuyenMaiPanel extends javax.swing.JPanel {

    /**
     * Creates new form KhuyenMaiPanel
     */
    public KhuyenMaiPanel() {
        initComponents();
       init();
    }
    GGHoaDonDAO ggHoaDonDAO = new GGHoaDonDAO();
    SanPhamDAO spDAO = new SanPhamDAO();
    LoaiHangDAO lhDAO = new LoaiHangDAO();
    GGSanPhamDAO ggSPDAO = new GGSanPhamDAO();
    KeHangDAO khDAO = new KeHangDAO();

    public void init() {
        fillTableGGHoaDon();
        fillComboboxLoaiSP();
        fillTableDSGGSP("");
        ckButton(0);
        checkButtonKMSP(0);
    }

    public boolean ckKMUpdate() {
        if (Validation.isEmpty(txtMaGGHD, "Không được bỏ trống mã giảm giá")) {
            return false;
        }

        if (Validation.isEmpty(txtSoLuongHD, "Không được bỏ trống số lượng")) {
            return false;
        } else {
            if (Validation.isNumber(txtSoLuongHD, "Số lượng vui lòng nhập số")) {
                return false;
            } else if (Integer.parseInt(txtSoLuongHD.getText()) < 1) {
                MsgBox.alert(this, "Số lượng phải lớn hơn 1");
                return false;
            }
        }

        if (txtNgayBDHD.getDate() == null) {
            MsgBox.alert(this, "Vui lòng nhập ngày bắt đầu");
            return false;
        }
        if (txtNgayKTHD.getDate() == null) {
            MsgBox.alert(this, "Vui lòng nhập ngày kết thúc");
            return false;
        }
        if (txtNgayKTHD.getDate().before(txtNgayBDHD.getDate())) {
            MsgBox.alert(this, "Ngày kết thúc phải sau ngày  bắt đầu");
            return false;
        }

        if (Validation.isEmpty(txtApDungHD, "Không được bỏ trống HĐ áp dụng")) {
            return false;
        } else {
            if (Validation.isNumber(txtApDungHD, "Áp dụng HĐ vui lòng nhập số")) {
                return false;
            } else if (Integer.parseInt(txtApDungHD.getText()) < 1) {
                MsgBox.alert(this, "Áp dụng HĐ phải lớn hơn 1");
                return false;
            }
        }

        if (Validation.isEmpty(txtGiaTriHD, "Không được bỏ trống giá trị hóa đơn ")) {
            return false;
        } else {
            if (Validation.isNumber(txtGiaTriHD, "Giá trị  vui lòng nhập số")) {
                return false;
            } else if (Integer.parseInt(txtGiaTriHD.getText()) < 1) {
                MsgBox.alert(this, "Giá trị phải lớn hơn 1");
                return false;
            }
        }

        if (rdoTheoPT.isSelected() == false && rdoTheoTien.isSelected() == false) {
            MsgBox.alert(this, "Vui lòng chọn hình thức giảm giá");
            return false;

        }

        return true;
    }

    public boolean ckKhuyemMaiHD() {
        if (Validation.isEmpty(txtMaGGHD, "Không được bỏ trống mã giảm giá")) {
            return false;
        }

        if (Validation.isEmpty(txtSoLuongHD, "Không được bỏ trống số lượng")) {
            return false;
        } else {
            if (Validation.isNumber(txtSoLuongHD, "Số lượng vui lòng nhập số")) {
                return false;
            } else if (Integer.parseInt(txtSoLuongHD.getText()) < 1) {
                MsgBox.alert(this, "Số lượng phải lớn hơn 1");
                return false;
            }
        }

        if (txtNgayBDHD.getDate() == null) {
            MsgBox.alert(this, "Vui lòng nhập ngày bắt đầu");
            return false;
        }
        if (txtNgayBDHD.getDate().before(XDate.addDays(XDate.getDate(), -1))) {
            MsgBox.alert(this, "Ngày bắt đầu phải là hôm nay hoặc sau hôm nay");
            return false;
        }
        if (txtNgayKTHD.getDate() == null) {
            MsgBox.alert(this, "Vui lòng nhập ngày kết thúc");
            return false;
        }
        if (txtNgayKTHD.getDate().before(txtNgayBDHD.getDate())) {
            MsgBox.alert(this, "Ngày kết thúc phải sau ngày  bắt đầu");
            return false;
        }

        if (Validation.isEmpty(txtApDungHD, "Không được bỏ trống HĐ áp dụng")) {
            return false;
        } else {
            if (Validation.isNumber(txtApDungHD, "Áp dụng HĐ vui lòng nhập số")) {
                return false;
            } else if (Integer.parseInt(txtApDungHD.getText()) < 1) {
                MsgBox.alert(this, "Áp dụng HĐ phải lớn hơn 1");
                return false;
            }
        }

        if (Validation.isEmpty(txtGiaTriHD, "Không được bỏ trống giá trị hóa đơn ")) {
            return false;
        } else {
            if (Validation.isNumber(txtGiaTriHD, "Giá trị  vui lòng nhập số")) {
                return false;
            } else if (Integer.parseInt(txtGiaTriHD.getText()) < 1) {
                MsgBox.alert(this, "Giá trị phải lớn hơn 1");
                return false;
            }
        }

        if (rdoTheoPT.isSelected() == false && rdoTheoTien.isSelected() == false) {
            MsgBox.alert(this, "Vui lòng chọn hình thức giảm giá");
            return false;

        }

        return true;
    }

    public void ckButton(int i) {
        if (i == 0) {
            btnSuaHD.setEnabled(false);
            btnMoiHD.setEnabled(false);
            btnThemMoi.setEnabled(true);
            btnXoaSP.setEnabled(false);
        } else {
            btnSuaHD.setEnabled(true);
            btnMoiHD.setEnabled(true);
            btnThemMoi.setEnabled(false);
            btnXoaSP.setEnabled(true);
        }
    }

    public void fillTableGGHoaDon() {
        DefaultTableModel model = (DefaultTableModel) tblListGGHoaDon.getModel();
        model.setRowCount(0);
        List<GGHoaDon> ggHoaDon = ggHoaDonDAO.getList();
        for (GGHoaDon gg : ggHoaDon) {
            if (gg.isHinhThuc() == true) {
                Object row[] = {gg.getMaGG(), gg.getSoLuong(), DDTienTe.FormatVND(gg.getMucGG()), gg.getNgayBD(), gg.getNgayKT(), DDTienTe.FormatVND(gg.getHoaDonAD()), gg.isHinhThuc() ? "Tiền" : "%", gg.getMaNV()};
                model.addRow(row);
            } else {
                Object row[] = {gg.getMaGG(), gg.getSoLuong(), DDTienTe.FormatToMacDinh(gg.getMucGG()), gg.getNgayBD(), gg.getNgayKT(), DDTienTe.FormatVND(gg.getHoaDonAD()), gg.isHinhThuc() ? "Tiền" : "%", gg.getMaNV()};
                model.addRow(row);
            }

        }
    }

    public GGHoaDon getformHD() {
        GGHoaDon gg = new GGHoaDon();
        gg.setMaGG(txtMaGGHD.getText());
        gg.setSoLuong(Integer.parseInt(txtSoLuongHD.getText()));
        gg.setNgayBD(XDate.toString(txtNgayBDHD.getDate(), "yyyy-MM-dd"));
        gg.setNgayKT(XDate.toString(txtNgayKTHD.getDate(), "yyyy-MM-dd"));
        gg.setHoaDonAD(Double.parseDouble(txtApDungHD.getText()));
        gg.setMucGG(Double.parseDouble(txtGiaTriHD.getText()));
        gg.setHinhThuc(rdoTheoTien.isSelected() ? true : false);
        gg.setMaNV(Auth.user.getMaNV());
        return gg;
    }

    public void setFormHD(GGHoaDon ggHD) {
        txtMaGGHD.setText(ggHD.getMaGG());
        if (ggHD.getNgayBD() == null) {
            txtNgayBDHD.setDate(null);
        } else {
            txtNgayBDHD.setDate(XDate.toDate(ggHD.getNgayBD(), "dd-MM-yyyy"));
        }
        if (ggHD.getNgayKT() == null) {
            txtNgayKTHD.setDate(null);
        } else {
            txtNgayKTHD.setDate(XDate.toDate(ggHD.getNgayKT(), "dd-MM-yyyy"));
        }
        if (ggHD.getSoLuong() == null) {
            txtSoLuongHD.setText("");
        } else {
            txtSoLuongHD.setText(String.valueOf(ggHD.getSoLuong()));
        }

        if (ggHD.getHoaDonAD() == null) {
            txtApDungHD.setText("");
        } else {
            txtApDungHD.setText(DDTienTe.catCham(String.valueOf(ggHD.getHoaDonAD())));
        }

        if (ggHD.getMucGG() == null) {
            txtGiaTriHD.setText("");
        } else {
            txtGiaTriHD.setText(DDTienTe.catCham(String.valueOf(ggHD.getMucGG())));
        }

        if (ggHD.isHinhThuc()) {
            rdoTheoTien.setSelected(true);
        } else {
            rdoTheoPT.setSelected(true);
        }
    }

    public void insert() {
        if (ggHoaDonDAO.selectbyID(txtMaGGHD.getText()) != null) {
            MsgBox.alert(this, "Mã Giảm giá đã tồn tại !");
        } else {
            try {
                ggHoaDonDAO.insert(getformHD());
                MsgBox.alert(this, "Thêm Thành Công");
                ggHoaDonDAO.setList(ggHoaDonDAO.selectAll());
                fillTableGGHoaDon();
                clearFormHD();
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm Thất Bại !");
                System.out.println(e);
            }
        }

    }

    public void update() {

        GGHoaDon ggHD = getformHD();
        try {
            if (MsgBox.confrim(this, "Bạn có thật sự muốn cập nhật ?")) {
                ggHoaDonDAO.update(ggHD);
                MsgBox.alert(this, "Cập Nhật Thành Công");
                ggHoaDonDAO.setList(ggHoaDonDAO.selectAll());
                fillTableGGHoaDon();
                clearFormHD();
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Cập Nhật Thất Bại !");
            System.out.println(e);
        }

    }

    public void clearFormHD() {
        GGHoaDon gg = new GGHoaDon();
        setFormHD(gg);
        ckButton(0);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Khuyến mãi sản phẩm
    public void checkButtonKMSP(int i) {
        if (i == 0) {
            btnMoiSP.setEnabled(false);
            btnThemSP.setEnabled(true);
            btnSuaSP.setEnabled(false);
            btnXoaSP.setEnabled(false);
        } else {
            btnMoiSP.setEnabled(true);
            btnThemSP.setEnabled(false);
            btnSuaSP.setEnabled(true);
            btnXoaSP.setEnabled(true);
        }
    }

    public void fillComboboxLoaiSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiSP.getModel();
        model.removeAllElements();
        List<LoaiHang> listLH = lhDAO.getListLH();
        for (LoaiHang lh : listLH) {
            model.addElement(lh);
        }
        fillComboboxSP();
    }

    public void fillComboboxSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSanPham.getModel();
        model.removeAllElements();
        LoaiHang lh = (LoaiHang) cboLoaiSP.getSelectedItem();
        if (lh != null) {
            List<SanPham> listSP = spDAO.selectSPbyLoaiSP(lh.getMaLoai());
            for (SanPham sp : listSP) {
                model.addElement(sp);
            }
        }
    }

    public GGSanPham getFormGGSP() {
        GGSanPham ggSP = new GGSanPham();
        SanPham sp = (SanPham) cboSanPham.getSelectedItem();
        ggSP.setMaSP(sp.getMaSP());
        ggSP.setNgayBD(XDate.toString(txtNgayBD.getDate(), "yyyy-MM-dd"));
        ggSP.setNgayKT(XDate.toString(txtNgayKetThuc.getDate(), "yyyy-MM-dd"));
        ggSP.setMaNV(Auth.user.getMaNV());
        ggSP.setGiaGiam(Double.parseDouble(txtGiaTri.getText()));
        return ggSP;
    }

    public void setFormGGSP(GGSanPham ggSP) {
        DefaultComboBoxModel modelLoaiSP = (DefaultComboBoxModel) cboLoaiSP.getModel();
        DefaultComboBoxModel modelSanPham = (DefaultComboBoxModel) cboSanPham.getModel();
        SanPham sp = spDAO.selectbyID(ggSP.getMaSP());
        LoaiHang lh = lhDAO.selectbyID(sp.getMaLoai());
        modelLoaiSP.setSelectedItem(lh);
        modelSanPham.setSelectedItem(sp);
        txtGiaTri.setText(DDTienTe.catCham(String.valueOf(ggSP.getGiaGiam())));
        txtNgayBD.setDate(XDate.toDate(ggSP.getNgayBD(), "dd-MM-yyyy"));
        txtNgayKetThuc.setDate(XDate.toDate(ggSP.getNgayKT(), "dd-MM-yyyy"));

    }

    public void fillTableDSGGSP(String maSP) {
        DefaultTableModel model = (DefaultTableModel) tblDSKMSanPham.getModel();
        model.setRowCount(0);
        List<GGSanPham> listGGSP = ggSPDAO.selectByMaSP(maSP);
        for (GGSanPham ggSP : listGGSP) {
            SanPham sp = spDAO.selectbyID(ggSP.getMaSP());
            Object row[] = {ggSP.getMaSP(), sp.getTenSP(), ggSP.getNgayBD(), ggSP.getNgayKT(), DDTienTe.FormatVND(ggSP.getGiaGiam()), ggSP.getMaNV()};
            model.addRow(row);
        }
    }

    public void insertGGSanPham() {
        GGSanPham ggSP = getFormGGSP();
        try {
            if (ggSPDAO.selectbyID(ggSP.getMaSP()) != null) {
                MsgBox.alert(this, "Sản phẩm đã có khuyến mãi !");
            } else {
                ggSPDAO.insert(ggSP);
                MsgBox.alert(this, "Thêm thành công");
                fillTableDSGGSP("");
                clearFormSP();
                KeHangPanel.fillTable();
                BanHangPanel.fillTableSPDangBan();

            }
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm thất bại");
        }
    }

    public void deleteGGSanPham() {
        try {
            if (MsgBox.confrim(this, "Bạn thật sự muốn xóa giảm giá này ?")) {
                GGSanPham ggSP = getFormGGSP();
                ggSPDAO.delete(ggSP.getMaSP());
                MsgBox.alert(this, "Xóa Thành Công");
                fillTableDSGGSP("");
                clearFormSP();
                KeHangPanel.fillTable();
                BanHangPanel.fillTableSPDangBan();

            }
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa Thất bại");
        }
    }

    public void updateGGSanPham() {
        try {
            GGSanPham ggSP = getFormGGSP();
            if (ggSPDAO.selectbyID(ggSP.getMaSP()) == null) {
                MsgBox.alert(this, "Giảm giá không tồn tại !");
            } else {
                if (MsgBox.confrim(this, "Bạn thật sự muốn cập nhật ?")) {
                    ggSPDAO.update(getFormGGSP());
                    MsgBox.alert(this, "Cập nhật thành công");
                    fillTableDSGGSP("");
                    clearFormSP();
                    KeHangPanel.fillTable();
                    BanHangPanel.fillTableSPDangBan();
                }
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại");
        }
    }

    public void showDetailGGSanPham() {
        int indexGGSP = tblDSKMSanPham.getSelectedRow();
        String maSP = (String) tblDSKMSanPham.getValueAt(indexGGSP, 0);
        GGSanPham sp = ggSPDAO.selectbyID(maSP);
        this.setFormGGSP(sp);
        checkButtonKMSP(1);
    }

    public void clearFormSP() {
        txtGiaTri.setText("");
        txtNgayBD.setDate(null);
        txtNgayKetThuc.setDate(null);
        checkButtonKMSP(0);
    }

    public boolean ckGGSP() {
        if (Validation.isEmpty(txtGiaTri, "Không bỏ trông giá trị")) {
            return false;
        }
        if (Validation.isNumber(txtGiaTri, "Giá trị không được nhập chữ")) {
            return false;
        }

        if (Double.parseDouble(txtGiaTri.getText()) < 1) {
            MsgBox.alert(this, "Giá trị phải lớn hơn 1");
            return false;
        }
        if (txtNgayBD.getDate() == null) {
            MsgBox.alert(this, "Vui lòng nhập ngày bắt đầu");
            return false;
        }
        if (txtNgayBD.getDate().before(XDate.addDays(XDate.getDate(), -1))) {
            MsgBox.alert(this, "Ngày bắt đầu phải là hôm nay hoặc sau hôm nay");
            return false;
        }
        if (txtNgayKetThuc.getDate() == null) {
            MsgBox.alert(this, "Vui lòng nhập ngày kết thúc");
            return false;
        }
        if (txtNgayKetThuc.getDate().before(txtNgayBD.getDate())) {
            MsgBox.alert(this, "Ngày kết thúc phải sau ngày  bắt đầu");
            return false;
        }
        return true;
    }

    public boolean ckUpdateSP() {
        if (Validation.isEmpty(txtGiaTri, "Không bỏ trông giá trị")) {
            return false;
        }
        if (Validation.isNumber(txtGiaTri, "Giá trị không được nhập chữ")) {
            return false;
        }

        if (Double.parseDouble(txtGiaTri.getText()) < 1) {
            MsgBox.alert(this, "Giá trị phải lớn hơn 1");
            return false;
        }
        if (txtNgayBD.getDate() == null) {
            MsgBox.alert(this, "Vui lòng nhập ngày bắt đầu");
            return false;
        }

        if (txtNgayKetThuc.getDate() == null) {
            MsgBox.alert(this, "Vui lòng nhập ngày kết thúc");
            return false;
        }
        if (txtNgayKetThuc.getDate().before(txtNgayBD.getDate())) {
            MsgBox.alert(this, "Ngày kết thúc phải sau ngày  bắt đầu");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        bgrHinhThucHD = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtMaGGHD = new javax.swing.JTextField();
        txtSoLuongHD = new javax.swing.JTextField();
        txtNgayKTHD = new com.toedter.calendar.JDateChooser();
        txtApDungHD = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnThemMoi = new javax.swing.JButton();
        btnSuaHD = new javax.swing.JButton();
        btnMoiHD = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        txtGiaTriHD = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        rdoTheoTien = new javax.swing.JRadioButton();
        rdoTheoPT = new javax.swing.JRadioButton();
        txtNgayBDHD = new com.toedter.calendar.JDateChooser();
        jPanel8 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListGGHoaDon = new HELP.Table();
        jTextField5 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSKMSanPham = new HELP.Table();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboSanPham = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtNgayBD = new com.toedter.calendar.JDateChooser();
        txtGiaTri = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        btnThemSP = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnMoiSP = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cboLoaiSP = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel7.setText("jLabel7");

        jLabel8.setText("jLabel8");

        jMenuItem1.setText("jMenuItem1");

        jLabel24.setText("jLabel24");

        jLabel25.setText("jLabel25");

        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 102));
        jLabel13.setText("Khuyến Mãi Hóa Đơn");

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 102));
        jLabel14.setText("Mã GG");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setText("Số Lượng");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 102));
        jLabel16.setText("Ngày Bắt Đầu");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 102));
        jLabel17.setText("Ngày Kết Thúc");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 102));
        jLabel18.setText("Áp dụng hóa đơn từ");

        txtMaGGHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaGGHDActionPerformed(evt);
            }
        });

        txtNgayKTHD.setDateFormatString("dd-MM-yyyy");

        txtApDungHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApDungHDActionPerformed(evt);
            }
        });

        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 5, 5));

        btnThemMoi.setBackground(new java.awt.Color(0, 102, 102));
        btnThemMoi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMoi.setText("Thêm Mới");
        btnThemMoi.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });
        jPanel7.add(btnThemMoi);

        btnSuaHD.setBackground(new java.awt.Color(0, 102, 102));
        btnSuaHD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaHD.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaHD.setText("Cập Nhật");
        btnSuaHD.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnSuaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaHDActionPerformed(evt);
            }
        });
        jPanel7.add(btnSuaHD);

        btnMoiHD.setBackground(new java.awt.Color(0, 102, 102));
        btnMoiHD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMoiHD.setForeground(new java.awt.Color(255, 255, 255));
        btnMoiHD.setText("Làm Mới");
        btnMoiHD.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnMoiHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiHDActionPerformed(evt);
            }
        });
        jPanel7.add(btnMoiHD);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 102, 102));
        jLabel28.setText("Giá trị");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 102, 102));
        jLabel31.setText("Hình Thức");

        bgrHinhThucHD.add(rdoTheoTien);
        rdoTheoTien.setText("Theo Tiền");

        bgrHinhThucHD.add(rdoTheoPT);
        rdoTheoPT.setText("Theo %");

        txtNgayBDHD.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(rdoTheoTien)
                        .addGap(18, 18, 18)
                        .addComponent(rdoTheoPT))
                    .addComponent(txtMaGGHD, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addComponent(txtSoLuongHD)
                    .addComponent(txtNgayBDHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgayKTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtApDungHD)
                    .addComponent(txtGiaTriHD))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel14)
                    .addComponent(txtMaGGHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel15)
                    .addComponent(txtSoLuongHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtNgayBDHD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17)
                    .addComponent(txtNgayKTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtApDungHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel28)
                    .addComponent(txtGiaTriHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoTheoTien)
                        .addComponent(rdoTheoPT)))
                .addGap(31, 31, 31)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel6);

        jLabel27.setBackground(new java.awt.Color(0, 102, 102));
        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Danh Sách Mã KM:");
        jLabel27.setOpaque(true);

        tblListGGHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã GG", "Số Lượng", "Giá Trị", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Áp dụng HĐ từ", "Hình Thức", "Mã NV tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListGGHoaDon.setRowHeight(30);
        tblListGGHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListGGHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblListGGHoaDon);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel8);

        jTextField5.setText("Mã KM");

        jLabel26.setBackground(new java.awt.Color(0, 153, 255));
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Tìm Kiếm");
        jLabel26.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1293, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Khuyến Mãi Hóa Đơn", jPanel1);

        tblDSKMSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giá Trị", "Nhân Viên Tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSKMSanPham.setRowHeight(30);
        tblDSKMSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSKMSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDSKMSanPham);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Sản Phẩm");

        cboSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSanPhamActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Giá Trị");

        txtNgayBD.setDateFormatString("dd-MM-yyyy");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Ngày bắt đầu");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Ngày Kết Thúc");

        txtNgayKetThuc.setDateFormatString("dd-MM-yyyy");

        jPanel5.setLayout(new java.awt.GridLayout(1, 0, 5, 5));

        btnThemSP.setBackground(new java.awt.Color(0, 102, 102));
        btnThemSP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemSP.setForeground(new java.awt.Color(255, 255, 255));
        btnThemSP.setText("Thêm Mới");
        btnThemSP.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });
        jPanel5.add(btnThemSP);

        btnXoaSP.setBackground(new java.awt.Color(0, 102, 102));
        btnXoaSP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaSP.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaSP.setText("Xóa");
        btnXoaSP.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoaSP);

        btnSuaSP.setBackground(new java.awt.Color(0, 102, 102));
        btnSuaSP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaSP.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaSP.setText("Cập Nhật");
        btnSuaSP.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });
        jPanel5.add(btnSuaSP);

        btnMoiSP.setBackground(new java.awt.Color(0, 102, 102));
        btnMoiSP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMoiSP.setForeground(new java.awt.Color(255, 255, 255));
        btnMoiSP.setText("Làm Mới");
        btnMoiSP.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiSPActionPerformed(evt);
            }
        });
        jPanel5.add(btnMoiSP);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("Loại sản phẩm");

        cboLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiSPItemStateChanged(evt);
            }
        });
        cboLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGiaTri)
                    .addComponent(txtNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(cboSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setBackground(new java.awt.Color(0, 102, 102));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Khuyến Mãi Sản Phẩm");

        jLabel12.setBackground(new java.awt.Color(0, 102, 102));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Danh Sách KM");
        jLabel12.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Khuyến Mãi Sản Phẩm", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1284, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaGGHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaGGHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaGGHDActionPerformed

    private void tblListGGHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListGGHoaDonMouseClicked
        int index = tblListGGHoaDon.getSelectedRow();
        for (GGHoaDon ggHD : ggHoaDonDAO.getList()) {
            if (ggHD.getMaGG().equals(tblListGGHoaDon.getValueAt(index, 0))) {
                setFormHD(ggHD);
                ckButton(1);

            }
        }

    }//GEN-LAST:event_tblListGGHoaDonMouseClicked

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSanPhamActionPerformed

    private void cboLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiSPActionPerformed

    private void cboLoaiSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiSPItemStateChanged
        fillComboboxSP();
    }//GEN-LAST:event_cboLoaiSPItemStateChanged

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        if (ckGGSP()) {
            insertGGSanPham();
        }

    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        deleteGGSanPham();
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        if (ckUpdateSP()) {
            updateGGSanPham();
        }

    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void tblDSKMSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSKMSanPhamMouseClicked
        showDetailGGSanPham();
    }//GEN-LAST:event_tblDSKMSanPhamMouseClicked

    private void btnMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiSPActionPerformed
        clearFormSP();
    }//GEN-LAST:event_btnMoiSPActionPerformed

    private void txtApDungHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApDungHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApDungHDActionPerformed

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        if (ckKhuyemMaiHD()) {

            insert();
        }
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void btnSuaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaHDActionPerformed
        if (ckKMUpdate()) {
            update();
        }
    }//GEN-LAST:event_btnSuaHDActionPerformed

    private void btnMoiHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiHDActionPerformed
        clearFormHD();
    }//GEN-LAST:event_btnMoiHDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrHinhThucHD;
    private javax.swing.JButton btnMoiHD;
    private javax.swing.JButton btnMoiSP;
    private javax.swing.JButton btnSuaHD;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JComboBox<String> cboSanPham;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JRadioButton rdoTheoPT;
    private javax.swing.JRadioButton rdoTheoTien;
    private HELP.Table tblDSKMSanPham;
    private HELP.Table tblListGGHoaDon;
    private javax.swing.JTextField txtApDungHD;
    private javax.swing.JTextField txtGiaTri;
    private javax.swing.JTextField txtGiaTriHD;
    private javax.swing.JTextField txtMaGGHD;
    private com.toedter.calendar.JDateChooser txtNgayBD;
    private com.toedter.calendar.JDateChooser txtNgayBDHD;
    private com.toedter.calendar.JDateChooser txtNgayKTHD;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private javax.swing.JTextField txtSoLuongHD;
    // End of variables declaration//GEN-END:variables
}
