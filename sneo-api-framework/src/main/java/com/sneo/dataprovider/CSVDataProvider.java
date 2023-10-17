package com.sneo.dataprovider;

import com.sneo.datautils.CSVReader;
import com.sneo.datautils.DataConverter;
import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Class contains all generic csv data providers
 *
 * @author ikumar
 */
public class CSVDataProvider {

    public static final String CSV_DATA_AS_STRING = "csvDataAsString";
    public static final String CSV_DATA_AS_MAP = "csvDataAsMAP";

    public static final String SCENARIO = "scenario";
    public static final String DESC = "desc";
    public static final String CATEGORY = "category";
    public static final String EXPECTED_VALUE = "expectedValue";
    public static final String PARAM1 = "param1";
    public static final String PARAM2 = "param2";
    public static final String PARAM3 = "param3";
    public static final String PARAM4 = "param4";
    public static final String INPUT = "input";


    private static final List<String[]> methodData;


    static {
        methodData = CSVReader.readAllRows("testcases");
    }

    @DataProvider(name = CSV_DATA_AS_STRING)
    public Object[][] csvDataAsString(Method method) {
        List testMethodDataList = DataConverter.getTestMethodDataAsList(methodData, method);
        String[][] testMethodDataArr = DataConverter.convertListToTwoDArray(testMethodDataList);
        return testMethodDataArr;
    }

    @SneakyThrows
    @DataProvider(name = CSV_DATA_AS_MAP)
    public Object[][] csvDataAsMap(Method method) {

        List<String[]> testRelatedReqList = new ArrayList<>();
        for (int i = 0; i < methodData.size(); i++) {
            if (method.getName().trim().split("_")[0].equalsIgnoreCase(methodData.get(i)[0].trim().split("_")[0]) || i == 0) {
                testRelatedReqList.add(methodData.get(i));
            }
        }

        Map<String, String>[][] testMethodMap = DataConverter.convertListToTwoDHashMap(testRelatedReqList);
        return testMethodMap;
    }


}
