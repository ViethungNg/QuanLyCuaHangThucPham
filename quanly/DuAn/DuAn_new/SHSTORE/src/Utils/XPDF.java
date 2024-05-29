/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import DAO.GGSanPhamDAO;
import DAO.NhanVienDAO;
import DAO.SanPhamDAO;
import Models.CTHDBanLe;
import Models.GGSanPham;
import Models.HDBanLe;
import Models.NhanVien;
import Models.SanPham;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author huuho
 */
public class XPDF {

    private float width = 180;
    private float height = 340;
    private String path = null;
    private PdfWriter pdfWriter;
    private PdfDocument pdfDocument;
    private Document document;
    private float khuyenMai = 0;
    private float khachTra = 0;
    NhanVienDAO nvDAO = new NhanVienDAO();
    GGSanPhamDAO ggspdao = new GGSanPhamDAO();
    SanPhamDAO spDAO = new SanPhamDAO();

    public float getKhachTra() {
        return khachTra;
    }

    public void setKhachTra(int khachTra) {
        this.khachTra = khachTra;
    }

    public float getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(float khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void createFilePDF(HDBanLe hDBanLe, List<CTHDBanLe> sp) throws FileNotFoundException, IOException {
        int tongTien = 0;
        int tam = sp.size();
        while (tam > 1) {
            tam -= 1;
            this.height += 14.2;
        }
        for (CTHDBanLe x : sp) {

            int lengthsp = x.getMaSP().length();

            while (lengthsp > 6) {
                lengthsp -= 6;
                this.height += 14.2;
            }

        }

        String titleHeader[] = {"Ngày:", "Khách Hàng:", "SDT:", "Thu Ngân:"};
        this.pdfWriter = new PdfWriter(this.path);
        this.pdfDocument = new PdfDocument(this.pdfWriter);
        this.pdfDocument.addNewPage(new PageSize(width, height));
        this.document = new Document(pdfDocument);
        this.document.setFont(fontPDF("dataPDF\\Lora-VariableFont_wght.ttf"));
        Table tableHeader = new Table(new float[]{65, 65});
        Cell cellTitleCH = new Cell(0, 2);
        cellTitleCH.setBorder(Border.NO_BORDER);
        cellTitleCH.add(new Paragraph("Cửa Hàng SH").setFontSize(10).setTextAlignment(TextAlignment.CENTER));
        cellTitleCH.setPadding(0);
        cellTitleCH.setMargin(0);
        Cell cellDiachi = new Cell(0, 2);
        cellDiachi.setBorder(Border.NO_BORDER);
        cellDiachi.add(new Paragraph("355 Trần Hưng Đạo, Quận 1, TP Hồ Chí Minh").setFontSize(7).setTextAlignment(TextAlignment.CENTER));
        cellDiachi.setPadding(0);
        cellDiachi.setMargin(0);
        Cell cellSDT = new Cell(0, 2);
        cellSDT.setBorder(Border.NO_BORDER);
        cellSDT.add(new Paragraph("SDT Cửa Hàng: 0931418748").setFontSize(7).setTextAlignment(TextAlignment.CENTER));
        cellSDT.setPadding(0);
        cellSDT.setMargin(0);
        Cell cellTitleHD = new Cell(0, 2);
        cellTitleHD.setBorder(Border.NO_BORDER);
        cellTitleHD.add(new Paragraph("Hóa Đơn Thanh Toán").setFontSize(10).setTextAlignment(TextAlignment.CENTER));
        cellTitleHD.setPadding(0);
        cellTitleHD.setMargin(0);
        Cell cellThongtinHD = new Cell(0, 2);
        cellThongtinHD.setBorder(Border.NO_BORDER);
        cellThongtinHD.add(new Paragraph("Số Phiếu: " + sp.get(0).getMaHD()).setFontSize(7).setTextAlignment(TextAlignment.CENTER));
        cellThongtinHD.setPadding(0);
        cellThongtinHD.setMargin(0);
        tableHeader.addHeaderCell(cellTitleCH.setBold());
        tableHeader.addCell(cellDiachi);
        tableHeader.addCell(cellSDT.add(new LineSeparator(new SolidLine(1))));
        tableHeader.addCell(cellTitleHD.setBold());
        tableHeader.addCell(cellThongtinHD);
        for (int i = 0; i < titleHeader.length; i++) {
            Cell cellTT = new Cell();
            cellTT.setBorder(Border.NO_BORDER);
            cellTT.add(new Paragraph(titleHeader[i]).setFontSize(7));
            cellTT.setPadding(0);
            cellTT.setMargin(0);
            tableHeader.addCell(cellTT);
            if (i == 0) {
                Cell cell = new Cell();
                cell.setBorder(Border.NO_BORDER);
                cell.add(new Paragraph(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()).toString()).setFontSize(7));
                cell.setPadding(0);
                cell.setMargin(0);
                tableHeader.addCell(cell.setMinWidth(75));
            } else if (i == 1) {

                Cell cell = new Cell();
                cell.setBorder(Border.NO_BORDER);
                if (hDBanLe.getSdtKH() == null) {
                    cell.add(new Paragraph("Khách vãng lai").setFontSize(7));
                } else {
                    cell.add(new Paragraph(hDBanLe.getSdtKH()).setFontSize(7));
                }

                cell.setPadding(0);
                cell.setMargin(0);
                tableHeader.addCell(cell);
            } else if (i == 2) {
                Cell cell = new Cell();
                cell.setBorder(Border.NO_BORDER);
                if (hDBanLe.getSdtKH() == null) {
                    cell.add(new Paragraph("Khách vãng lai").setFontSize(7));
                } else {
                    cell.add(new Paragraph(hDBanLe.getSdtKH()).setFontSize(7));
                }
                cell.setPadding(0);
                cell.setMargin(0);
                tableHeader.addCell(cell);
            } else if (i == 3) {
                Cell cell = new Cell();
                cell.setBorder(Border.NO_BORDER);
                NhanVien nv = nvDAO.selectbyID(hDBanLe.getMaNV());
                cell.add(new Paragraph(nv.getHoTen()).setFontSize(7));
                cell.setPadding(0);
                cell.setMargin(0);
                tableHeader.addCell(cell);
            }
        }
        String titleSP[] = {"Tên SP", "SL", "Giá", "Thành Tiền"};
        Table tableSP = new Table(new float[]{50, 35, 35, 35});
        for (int i = 0; i < titleSP.length; i++) {
            Cell cell = new Cell();
            cell.setBorderLeft(Border.NO_BORDER);
            cell.setBorderRight(Border.NO_BORDER);
            cell.add(new Paragraph(titleSP[i]).setFontSize(7));
            cell.setPadding(0);
            cell.setMargin(0);
            cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
            cell.setTextAlignment(TextAlignment.CENTER);
            tableSP.addCell(cell.setBold());
        }

        for (int i = 0; i < sp.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    Cell cell = new Cell();
                    cell.setMargin(0);
                    cell.setPadding(0);
                    cell.setBorder(Border.NO_BORDER);
                     SanPham sanPham = spDAO.selectbyID(sp.get(i).getMaSP());
                    cell.add(new Paragraph(sanPham.getTenSP()));
                    
                    if (i == sp.size() - 1) {
                        cell.setBorderBottom(new SolidBorder(0.5f));
                    }
                    tableSP.addCell(cell.setFontSize(7).setBold());
                } else if (j == 1) {
                    Cell cell = new Cell();
                    cell.setMargin(0);
                    cell.setPadding(0);
                    cell.setBorder(Border.NO_BORDER);
                    cell.setTextAlignment(TextAlignment.CENTER);

                    cell.add(new Paragraph(String.valueOf(sp.get(i).getSoLuong())));
                    if (i == sp.size() - 1) {
                        cell.setBorderBottom(new SolidBorder(0.5f));
                    }
                    tableSP.addCell(cell.setFontSize(7));
                } else if (j == 2) {
                    Cell cell = new Cell();
                    cell.setMargin(0);
                    cell.setPadding(0);
                    cell.setBorder(Border.NO_BORDER);
                    cell.setTextAlignment(TextAlignment.CENTER);
                    GGSanPham ggSP = ggspdao.selectbyID(sp.get(i).getMaSP());

                    if (ggSP != null) {
                        SanPham sanPham = spDAO.selectbyID(ggSP.getMaSP());

                        cell.add(new Paragraph(new Text(new DecimalFormat("#,###").format(sanPham.getGiaBan())).setLineThrough()).add(" " + (new DecimalFormat("#,###").format(sp.get(i).getGiaBan()))));

                    } else {
                        cell.add(new Paragraph(new DecimalFormat("#,###").format(sp.get(i).getGiaBan())));
                    }

                    if (i == sp.size() - 1) {
                        cell.setBorderBottom(new SolidBorder(0.5f));
                    }
                    tableSP.addCell(cell.setFontSize(7));
                } else if (j == 3) {
                    tongTien += sp.get(i).getGiaBan() * sp.get(i).getSoLuong();
                    Cell cell = new Cell();
                    cell.setMargin(0);
                    cell.setPadding(0);
                    cell.setBorder(Border.NO_BORDER);
                    cell.setTextAlignment(TextAlignment.CENTER);
                    cell.add(new Paragraph(new DecimalFormat("#,###").format(sp.get(i).getGiaBan() * sp.get(i).getSoLuong())));
                    if (i == sp.size() - 1) {
                        cell.setBorderBottom(new SolidBorder(0.5f));
                    }
                    tableSP.addCell(cell.setFontSize(7));
                }
            }

        }
        String titleFooter[] = {"Tổng cộng:", "Khuyễn Mãi:", "Thành Tiền:", "Tiền Khách Đưa:", "Tiền Thừa:"};
        for (int i = 0; i < titleFooter.length; i++) {
            Cell cellTT = new Cell(0, 3);
            cellTT.setBorder(Border.NO_BORDER);
            cellTT.add(new Paragraph(titleFooter[i]).setFontSize(8));
            cellTT.setPadding(0);
            cellTT.setMargin(0);
            tableSP.addCell(cellTT.setTextAlignment(TextAlignment.CENTER));
            if (i == 0) {
                Cell cell = new Cell();
                cell.setBorder(Border.NO_BORDER);
                cell.add(new Paragraph(new DecimalFormat("#,###").format(tongTien)).setFontSize(8));
                cell.setPadding(0);
                cell.setMargin(0);
                tableSP.addCell(cell);
            } else if (i == 1) {
                Cell cell = new Cell();
                cell.setBorder(Border.NO_BORDER);
                cell.add(new Paragraph(new DecimalFormat("#,###").format(this.getKhuyenMai())).setFontSize(8));
                cell.setPadding(0);
                cell.setMargin(0);
                tableSP.addCell(cell);
            } else if (i == 2) {
                Cell cell = new Cell();
                cell.setBorder(Border.NO_BORDER);
                cell.add(new Paragraph(new DecimalFormat("#,###").format(tongTien - this.getKhuyenMai())).setFontSize(8));
                cell.setPadding(0);
                cell.setMargin(0);
                tableSP.addCell(cell.setBold());
            } else if (i == 3) {
                Cell cell = new Cell();
                cell.setBorder(Border.NO_BORDER);
                cell.add(new Paragraph(new DecimalFormat("#,###").format(this.getKhachTra())).setFontSize(8));
                cell.setPadding(0);
                cell.setMargin(0);
                tableSP.addCell(cell);
            } else if (i == 4) {
                Cell cell = new Cell();
                cell.setBorder(Border.NO_BORDER);
                cell.add(new Paragraph(new DecimalFormat("#,###").format(this.getKhachTra() - (tongTien - this.getKhuyenMai()))).setFontSize(8));
                cell.setPadding(0);
                cell.setMargin(0);
                tableSP.addCell(cell);
            }
        }
        Cell cellCamon = new Cell(0, 4);
        cellCamon.setBorder(Border.NO_BORDER);
        cellCamon.setTextAlignment(TextAlignment.CENTER);
        cellCamon.add(new Paragraph("Xin Cảm Ơn Quý Khách Và Hẹn Gặp Lại!").setBold().setFontSize(10));
        cellCamon.setPadding(0);
        cellCamon.setMargin(0);
        tableSP.addCell(cellCamon);

        this.document.setMargins(0, 30, 0, 30);
        this.document.add(tableHeader);
        this.document.add(tableSP);
        this.document.close();
    }

    public PdfFont fontPDF(String str) throws IOException {
        return PdfFontFactory.createFont(new File(str).getAbsolutePath(), PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
    }

}
