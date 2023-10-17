package com.sneo.dataprovider;


import com.sneo.datautils.DataConverter;
import com.sneo.datautils.ExcelUtils;
import com.sneo.datautils.JacksonUtils;
import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.sneo.datautils.DataConverter.combinationOfData;
import static com.sneo.datautils.DataConverter.convertListToTwoDHashMap;


/**
 * Class contains all generic excel data providers
 *
 * @author ikumar
 */
public class ExcelDataProvider {

    public static final String EXCEL_DATA_AS_POJO = "dataAsPojo";
    public static final String EXCEL_DATA_AS_MAP = "dataAsMap";
    public static final String EXCEL_DATA_AS_POJO_2 = "dataAsPojo_2";
    public static final String EXCEL_DATA_AS_MAP_2 = "dataAsMap_2";
    protected static final String[][] excelData;
    protected static final String[][] excelData_2;

    static {
        excelData = ExcelUtils.getInstance().getAllSheetsData("testmethod");
        excelData_2 = ExcelUtils.getInstance().getAllSheetsData("testmethod_2");
    }

    protected static Map<String, String>[][] excelDataAsMap(String fileName) {
        String[][] excelData = ExcelUtils.getInstance().getAllSheetsData(fileName);
        List<String[]> avisStationsList = DataConverter.convertTwoDArrToList(excelData);
        return DataConverter.convertListToTwoDHashMap(avisStationsList);
    }

    @SneakyThrows
    @DataProvider(name = EXCEL_DATA_AS_MAP)
    public Object[][] dataAsMap(Method method) {
        List<String[]> testMethodList = DataConverter.getTestMethodDataAsList(excelData, method);
        Map<String, String>[][] testMethodMap = DataConverter.convertListToTwoDHashMap(testMethodList);
        return testMethodMap;
    }

    @SneakyThrows
    @DataProvider(name = EXCEL_DATA_AS_POJO)
    public Object[][] dataAsPojo(Method method) {
        return getTestmethodData(method);
    }

    @SneakyThrows
    @DataProvider(name = EXCEL_DATA_AS_MAP_2)
    public Object[][] dataAsMap_2(Method method) {
        List<String[]> testMethodList = DataConverter.getTestMethodDataAsList(excelData_2, method);
        Map<String, String>[][] testMethodMap = DataConverter.convertListToTwoDHashMap(testMethodList);
        return testMethodMap;
    }

    @SneakyThrows
    @DataProvider(name = EXCEL_DATA_AS_POJO_2)
    public Object[][] dataAsPojo_2(Method method) {
        return getTestmethodData_2(method);
    }

    protected TestmethodData[][] getTestmethodData_2(Method method) throws IOException {
        List<String[]> testMethodList = DataConverter.getTestMethodDataAsList(excelData_2, method);
        Map<String, String>[][] testMethodMap = DataConverter.convertListToTwoDHashMap(testMethodList);
        TestmethodData[][] testcaseData = JacksonUtils.convertTwoDMapToPOJO(testMethodMap, TestmethodData[][].class);
        return testcaseData;
    }

    protected TestmethodData[][] getTestmethodData(Method method) throws IOException {
        List<String[]> testMethodList = DataConverter.getTestMethodDataAsList(excelData, method);
        Map<String, String>[][] testMethodMap = DataConverter.convertListToTwoDHashMap(testMethodList);
        TestmethodData[][] testcaseData = JacksonUtils.convertTwoDMapToPOJO(testMethodMap, TestmethodData[][].class);
        return testcaseData;
    }

    protected Object[][] getIdentifierMatchedREQFromCSVList(Method method, List<String[]> reqCSVList) throws IOException {
        TestmethodData[][] testcaseData = getTestmethodData(method);

        List<String[]> testRelatedReqList = new ArrayList<>();
        for (int i = 0; i < reqCSVList.size(); i++) {
            if (method.getName().trim().split("_")[0].equalsIgnoreCase(reqCSVList.get(i)[0].trim().split("_")[0]) || i == 0) {
                testRelatedReqList.add(reqCSVList.get(i));
            }
        }
        Map<String, String>[][] map = convertListToTwoDHashMap(testRelatedReqList);

        return combinationOfData(testcaseData, map);
    }


    protected Object[][] getMethodInitialMatchedREQFromCSVList(Method method, List<String[]> reqCSVList) throws IOException {
        List<String[]> testRelatedReqList = new ArrayList<>();
        for (int i = 0; i < reqCSVList.size(); i++) {
            if (method.getName().trim().split("_")[0].equalsIgnoreCase(reqCSVList.get(i)[0].trim().split("_")[0]) || i == 0) {
                testRelatedReqList.add(reqCSVList.get(i));
            }
        }
        Map<String, String>[][] map = convertListToTwoDHashMap(testRelatedReqList);

        return map;
    }


}
