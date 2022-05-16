package com.example.projectb2c.controller;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.example.projectb2c.entity.Product;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//chức năng xuất danh sách ra file excel
public class UserExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Product> productList;

    public UserExcelExporter(List<Product> productList) {
        this.productList = productList;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Product");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Detail", style);
        createCell(row, 2, "img", style);
        createCell(row, 3, "Price", style);
        createCell(row, 4, "Name product", style);
        createCell(row, 5, "Category", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        }else cell.setCellValue((Integer) value);
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Product product : productList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, product.getId(), style);
            createCell(row, columnCount++, product.getDetail(), style);
            createCell(row, columnCount++, product.getImg1(), style);
            createCell(row, columnCount++, product.getPrice(), style);
            createCell(row, columnCount++, product.getProductName(), style);
            createCell(row, columnCount++, product.getCategory().getNameCategory(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
