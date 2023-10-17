package com.sneo.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    public List<String> readExcelData(String fileName) throws IOException {
        fileName = "./testdata/" + fileName + ".xlsx";
        FileInputStream inputStream = new FileInputStream(fileName);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        List<String> excelData = new ArrayList<>();
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            excelData.add(cell.getStringCellValue());
        }
        workbook.close();
        return excelData;
    }
}
