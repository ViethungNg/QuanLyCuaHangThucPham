/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Models.KhachHang;
import Models.SanPham;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author huuho
 */
public class XExcel<E> {

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet();
    ListType type = null;

    public ListType getType() {
        return type;
    }

    public void setType(ListType type) {
        this.type = type;
    }

    public static enum ListType {
        KHACHHANG, SANPHAM
    }

    public CellStyle styleCellColumn() {
        CellStyle cs = workbook.createCellStyle();
        cs.setBorderBottom(BorderStyle.MEDIUM);
        cs.setBorderTop(BorderStyle.MEDIUM);
        cs.setBorderLeft(BorderStyle.MEDIUM);
        cs.setBorderRight(BorderStyle.MEDIUM);
        cs.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cs.setAlignment(HorizontalAlignment.CENTER);
        cs.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        cs.setFont(font);
        return cs;
    }

    public CellStyle styleCellConten() {
        CellStyle cs = workbook.createCellStyle();
        cs.setBorderBottom(BorderStyle.MEDIUM);
        cs.setBorderTop(BorderStyle.MEDIUM);
        cs.setBorderLeft(BorderStyle.MEDIUM);
        cs.setBorderRight(BorderStyle.MEDIUM);
        cs.setAlignment(HorizontalAlignment.CENTER);
        cs.setVerticalAlignment(VerticalAlignment.CENTER);
        return cs;
    }

    public void createFileExecel(File file, String title[], Object obj) {
        if (getType() == XExcel.ListType.KHACHHANG) {
            List<KhachHang> list = (List<KhachHang>) obj;
            for (int i = 0; i < 1; i++) {
                Row row = sheet.createRow(i);
                for (int j = 0; j < title.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellStyle(styleCellColumn());
                    cell.setCellValue((title[j]));
                }
            }
            for (int i = 0; i < list.size(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < title.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellStyle(styleCellConten());
                    if (cell.getColumnIndex() == 0) {
                        cell.setCellValue(list.get(i).getSDT());
                    } else if (cell.getColumnIndex() == 1) {
                        cell.setCellValue(list.get(i).getTenKH());
                    } else if (cell.getColumnIndex() == 2) {
                        cell.setCellValue(list.get(i).getGmail());
                    } else if (cell.getColumnIndex() == 3) {
                        cell.setCellValue(list.get(i).getMatkhau());
                    } else if (cell.getColumnIndex() == 4) {
                        cell.setCellValue(list.get(i).getDiem());
                    }
                    sheet.autoSizeColumn(j);
                }
            }
        } else if (getType() == XExcel.ListType.SANPHAM) {
            List<SanPham> list = (List<SanPham>) obj;
            for (int i = 0; i < 1; i++) {
                Row row = sheet.createRow(i);
                for (int j = 0; j < title.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellStyle(styleCellColumn());
                    cell.setCellValue((title[j]));
                }
            }
            for (int i = 0; i < list.size(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < title.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellStyle(styleCellConten());
                    if (cell.getColumnIndex() == 0) {
                        cell.setCellValue(list.get(i).getMaSP());
                    } else if (cell.getColumnIndex() == 1) {
                        cell.setCellValue(list.get(i).getTenSP());
                    } else if (cell.getColumnIndex() == 2) {
                        cell.setCellValue(list.get(i).getSLTonKho());
                    } else if (cell.getColumnIndex() == 3) {
                        cell.setCellValue(list.get(i).getSlTrenKe());
                    } else if (cell.getColumnIndex() == 4) {
                        cell.setCellValue(list.get(i).getGiaBan());
                    } else if (cell.getColumnIndex() == 5) {
                        cell.setCellValue(list.get(i).getGiaNhap());
                    } else if (cell.getColumnIndex() == 6) {
                        cell.setCellValue(list.get(i).getMaLoai());
                    } else if (cell.getColumnIndex() == 7) {
                        cell.setCellValue(list.get(i).getDonVi());
                    } else if (cell.getColumnIndex() == 8) {
                        cell.setCellValue(list.get(i).getQrCode());
                    } else if (cell.getColumnIndex() == 9) {
                        cell.setCellValue((list.get(i).isTrangThai() ? "Đang kinh doanh" : "Ngưng kinh doanh"));
                    }
                    sheet.autoSizeColumn(j);
                }
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
