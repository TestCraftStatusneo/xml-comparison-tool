package com.sneo.data;


import com.sneo.dataprovider.ExcelDataProvider;
import com.sneo.dataprovider.TestmethodData;
import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static com.sneo.datautils.CSVReader.readAllCSV;
import static com.sneo.datautils.CSVReader.readAllRows;
import static com.sneo.datautils.DataConverter.combinationOfData;


/**
 * Class contains all custom data providers
 *
 * @author ikumar
 */
public class AvisDataProvider extends ExcelDataProvider {


    public static final String AVIS_STATIONS = "avisStations";
    //public static final String GLOBAL_BOOKING_TOOL = "globalBookingTool";
    public static final String TEST_DATA_AND_PARTNER_CONFIG = "testDataAndPartnerConfig";
    public static final String TEST_DATA_AND_PARTNER_CONFIG_2 = "testDataAndPartnerConfig_2";
    public static final String PROD_REQUESTS_DATA = "prodRequestsData";
    public static final String REQUESTS_DATA = "requestsData";

    public static final String SOAPUI_REQUESTS_DATA = "soapuiRequestsData";
    public static final String LOCATION_SIPP_COMBINATIONS = "locationSippCombinations";
    public static final String ERROR_REQUESTS_DATA = "errorRequestsData";


    private static final Map<String, String>[][] avisStationMap;
    private static final Map<String, String>[][] globalBookingToolMap;
    //static final Map<String, String>[][] partnerConfigMap;
    //private static final Map<String, String>[][] sippMap;
    private static final List<String[]> prodRequestsCSVAsList;
    private static final List<String[]> requestsCSVAsList;

    //private static final List<String[]> soapuiRequestsCSVAsList;

    //static final List<String[]> errorRequestsCSVAsList;
    //private static final Map<String, String>[][] testDataAndPartnerConfigMap;
    private static final Map<String, String>[][] testDataAndPartnerConfigMap_2;

    static {
        avisStationMap = excelDataAsMap("avis_stations");
        globalBookingToolMap = excelDataAsMap("global_booking_tool");
        //partnerConfigMap = CSVReader.csvDataAsMap("partner_configs");
        testDataAndPartnerConfigMap_2 = excelDataAsMap("partner_configuration_2");
        prodRequestsCSVAsList = readAllRows("prod_request_tests");
        requestsCSVAsList = readAllCSV(new String[]{"avail_batch0", "avail_batch1", "avail_soapui"});

        //soapuiRequestsCSVAsList = readAllRows("testcases");
        // sippMap = excelDataAsMap("sipp_codes");
        //errorRequestsCSVAsList = readAllRows("error_tests");

    }


    @SneakyThrows
    @DataProvider(name = AVIS_STATIONS)
    public Object[][] avisStations(Method method) {
        TestmethodData[][] testcaseData = getTestmethodData(method);
        return combinationOfData(testcaseData, avisStationMap);
    }

//    @SneakyThrows
//    @DataProvider(name = GLOBAL_BOOKING_TOOL)
//    public Object[][] globalBookingTool(Method method) {
//        TestmethodData[][] testcaseData = getTestmethodData(method);
//        return combinationOfData(testcaseData, globalBookingToolMap);
//
//    }

//    @SneakyThrows
//    @DataProvider(name = TEST_DATA_AND_PARTNER_CONFIG)
//    public Object[][] testDataAndPartnerConfig(Method method) {
//        TestmethodData[][] testcaseData = getTestmethodData(method);
//        return combinationOfData(testcaseData, partnerConfigMap);
//
//    }

    @SneakyThrows
    @DataProvider(name = TEST_DATA_AND_PARTNER_CONFIG_2)
    public Object[][] rateruleTestDataAndPartnerConfig(Method method) {
        TestmethodData[][] testcaseData = getTestmethodData_2(method);
        return combinationOfData(testcaseData, testDataAndPartnerConfigMap_2);

    }

    @SneakyThrows
    @DataProvider(name = PROD_REQUESTS_DATA)
    public Object[][] prodRequestsData(Method method) {
        return getIdentifierMatchedREQFromCSVList(method, prodRequestsCSVAsList);
    }

    @SneakyThrows
    @DataProvider(name = REQUESTS_DATA)
    public Object[][] requestsData(Method method) {
        return getMethodInitialMatchedREQFromCSVList(method, requestsCSVAsList);
    }

//    @SneakyThrows
//    @DataProvider(name = SOAPUI_REQUESTS_DATA)
//    public Object[][] soapuiRequestsData(Method method) {
//        return getIdentifierMatchedREQFromCSVList(method, soapuiRequestsCSVAsList);
//    }

//    @SneakyThrows
//    @DataProvider(name = ERROR_REQUESTS_DATA)
//    public Object[][] errorRequestsData(Method method) {
//        return getIdentifierMatchedREQFromCSVList(method, errorRequestsCSVAsList);
//    }

//    @SneakyThrows
//    @DataProvider(name = LOCATION_SIPP_COMBINATIONS)
//    public Object[][] locationSippCombinations(Method method){
//        TestmethodData[][] testcaseData = getTestmethodData_2(method);
//        return combinationOfData(testcaseData, sippMap);
//    }
}
