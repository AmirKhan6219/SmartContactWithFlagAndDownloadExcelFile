package com.smarts.excel;

import com.smarts.entity.Contact;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class ContactExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Contact> listcontacts;

    public ContactExcelExporter(List<Contact> listcontacts) {
        this.listcontacts = listcontacts;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Contact");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        createCell(row, 0, "contactId", style);
        createCell(row, 1, "firstName", style);
        createCell(row, 2, "lastName", style);
        createCell(row, 3, "emailAddress", style);
        createCell(row, 4, "createdBy", style);
        createCell(row, 5, "createdOn", style);
        createCell(row, 6, "updatedBy", style);
        createCell(row, 7, "updatedOn", style);
        createCell(row, 8, "isActive", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Contact contact : listcontacts) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, contact.getContactId(), style);
            createCell(row, columnCount++, contact.getFirstName(), style);
            createCell(row, columnCount++, contact.getLastName(), style);
            createCell(row, columnCount++, contact.getEmailAddress(), style);
            createCell(row, columnCount++, contact.getCreatedBy(), style);
            createCell(row, columnCount++, contact.getCreatedOn(), style);
            createCell(row, columnCount++, contact.getUpdatedBy(), style);
            createCell(row, columnCount++, contact.getUpdatedOn(), style);
            createCell(row, columnCount++, contact.getIsActive(), style);
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
