package com.sneo.data;

import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CartesianDataProvider {

    private static final String FILE_NAME = "./testdata/rr/cartesian_raterule_parameters.xlsx";
    private static final String LOCATION_SHEET_NAME = "Location";
    private static final String SIPP_SHEET_NAME = "Sipp";
    private static final String MEMBERSHIP_SHEET_NAME = "Membership";
    private static final String CORP_DISCOUNT_SHEET_NAME = "CorpDiscount";
    private static final String AGE_SHEET_NAME = "Age";
    public static final String PICKUP_LOCATION = "pickupLocation";
    public static final String DROPOFF_LOCATION = "dropoffLocation";
    public static final String RATE_QUALIFIER = "rateQualifier";
    public static final String DESCRIPTION = "description";
    public static final String SIPP = "sipp";
    public static final String PROGRAM = "program";
    public static final String MEMBERSHIP = "membership";
    public static final String CORP_DISCOUNT = "corpDiscount";
    public static final String AGE = "age";

    public static final String CARTESIAN_DATA_PROVIDER_RATERULE = "cartesianDataProviderRaterule";

    @DataProvider(name = CARTESIAN_DATA_PROVIDER_RATERULE)
    public Object[][] provideCartesianData() throws IOException {

        FileInputStream file = new FileInputStream(FILE_NAME);
        Workbook workbook = WorkbookFactory.create(file);

        // * extract data from location sheet
        Sheet locationSheet = workbook.getSheet(LOCATION_SHEET_NAME);
        List<String> locationInfoList = extractColumnsFromLocationSheetAsList(locationSheet);

        // * extract data from sipp sheet
        Sheet sippSheet = workbook.getSheet(SIPP_SHEET_NAME);
        List<String> sippList = extractFirstColumnStrFromSheetAsList(sippSheet);

        // * extract data from membership sheet
        Sheet membershipSheet = workbook.getSheet(MEMBERSHIP_SHEET_NAME);
        List<String> membershipList = extractColumnsFromMembershipSheetAsList(membershipSheet);

        // * extract data from corpDiscount sheet
        Sheet corpDiscountSheet = workbook.getSheet(CORP_DISCOUNT_SHEET_NAME);
        List<String> corpDiscountList = extractFirstColumnStrFromSheetAsList(corpDiscountSheet);

        // * extract data from age sheet
        Sheet ageSheet = workbook.getSheet(AGE_SHEET_NAME);
        List<String> ageList = extractFirstColumnNmbrFromSheetAsList(ageSheet);

        // ! Cartesian Product
        List<List<Object>> cartesianList = Lists.cartesianProduct(locationInfoList, sippList, membershipList, corpDiscountList, ageList);

//        List<Object[]> allCombinations = parseCartesianList(cartesianList);
        List<Map<String, String>> testData = new ArrayList<>();

        for(List<Object> combination : cartesianList){
            Map<String, String> row = new LinkedHashMap<>();
            String locationInfo = (String) combination.get(0);
            String[] locationArray = locationInfo.split("_");
            String membershipInfo = (String) combination.get(2);
            String[] membershipArray = membershipInfo.split("_");
            row.put(PICKUP_LOCATION, locationArray[0]);
            row.put(DROPOFF_LOCATION, locationArray[1]);
            row.put(DESCRIPTION, locationArray[2]);
            row.put(SIPP, (String) combination.get(1));
            row.put(PROGRAM, membershipArray[0]);
            row.put(MEMBERSHIP, membershipArray[1]);
            row.put(CORP_DISCOUNT, (String) combination.get(3));
            row.put(AGE, (String) combination.get(4));
            testData.add(row);
        }
        file.close();

        Object[][] testDataArray = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            testDataArray[i][0] = testData.get(i);
        }

        return testDataArray;
    }

    public static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return ""; // Return empty string if the cell is null
        }

        String cellValue;
        if (cell.getCellType().equals(CellType.STRING)){
            cellValue = cell.getStringCellValue();
        }
        else if (cell.getCellType().equals(CellType.NUMERIC)){
            // Use DecimalFormat to format the numeric value as a string without scientific notation
            DecimalFormat decimalFormat = new DecimalFormat("#");
            cellValue = decimalFormat.format(cell.getNumericCellValue());
        } else if (cell.getCellType().equals(CellType.BOOLEAN)) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        }
        else {
            cellValue = ""; // Return empty string for unsupported cell types
        }

        return cellValue;
    }


    private static List<String> extractColumnsFromLocationSheetAsList(Sheet locationSheet) {
        List<String> locationInfoList = new ArrayList<>();
        for (int i = 1; i <= locationSheet.getLastRowNum(); i++) {
            Row row = locationSheet.getRow(i);
            Cell pickupLocationCell = row.getCell(0);
            Cell dropoffLocationCell = row.getCell(1);
            Cell descriptionCell = row.getCell(2);
            String locationInfo =
                    pickupLocationCell.getStringCellValue() + "_" +
                    dropoffLocationCell.getStringCellValue() + "_" +
                    descriptionCell.getStringCellValue();
            locationInfoList.add(locationInfo);
        }
        return locationInfoList;
    }

    private static List<String> extractColumnsFromMembershipSheetAsList(Sheet membershipSheet) {
        List<String> MembershipInfoList = new ArrayList<>();
        for (int i = 1; i <= membershipSheet.getLastRowNum(); i++) {
            Row row = membershipSheet.getRow(i);
            Cell programIdCell = row.getCell(0);
            Cell membershipIdCell = row.getCell(1);
            String membershipInfo =
                    getCellValueAsString(programIdCell) + "_" + getCellValueAsString(membershipIdCell);
            MembershipInfoList.add(membershipInfo);
        }
        return MembershipInfoList;
    }

    private static List<String> extractFirstColumnStrFromSheetAsList(Sheet sheet) {
        List<String> sheetAsList = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            String cellValue = cell.getStringCellValue();
            sheetAsList.add(cellValue);
        }
        return sheetAsList;
    }

    private static List<String> extractFirstColumnNmbrFromSheetAsList(Sheet sheet) {
        List<String> sheetAsList = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            int cellValue = (int) cell.getNumericCellValue();
            sheetAsList.add(String.valueOf(cellValue));
        }
        return sheetAsList;
    }
}



