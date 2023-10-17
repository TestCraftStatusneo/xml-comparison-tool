package com.sneo.data;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDataProvider {

    public static final String PRICELINE_TRANSACTIONS_DATA_PROVIDER = "Priceline_TransactionsDataProvider";
    public static final String LAC_TRANSACTIONS_DATA_PROVIDER = "LAC_Transactions_Data_Provider";
    public static final String CARTRAWLER_TRANSACTIONS_DATA_PROVIDER = "CarTrawler_Transactions_Data_Provider";
    public static final String EXPEDIA_TRANSACTIONS_DATA_PROVIDER = "Expedia_Transactions_Data_Provider";
    public static final String COSTCODIRECT_TRANSACTIONS_DATA_PROVIDER = "CostcoDirect_Transactions_Data_Provider";
    public static final String COSTCOTOUR_TRANSACTIONS_DATA_PROVIDER = "CostcoTour_Transactions_Data_Provider";
    private static final String PRICELINE_TRANSACTIONS_FILE_NAME = "./testdata/rr/Priceline_production_logs.txt";
    private static final String LAC_TRANSACTIONS_FILE_NAME = "./testdata/rr/LAC_production_logs.txt";
    private static final String CARTRAWLER_TRANSACTIONS_FILE_NAME = "./testdata/rr/CarTrawler_production_logs.txt";
    private static final String EXPEDIA_TRANSACTIONS_FILE_NAME = "./testdata/rr/Expedia_production_logs.txt";
    private static final String COSTCODIRECT_TRANSACTIONS_FILE_NAME = "./testdata/rr/CostcoDirect_production_logs.txt";
    private static final String COSTCOTOUR_TRANSACTIONS_FILE_NAME = "./testdata/rr/CostcoTour_production_logs.txt";

    @DataProvider(name = PRICELINE_TRANSACTIONS_DATA_PROVIDER)
    public Object[] providePricelineTransactionsData() throws IOException {
        return extractTxtData(PRICELINE_TRANSACTIONS_FILE_NAME);
    }

    @DataProvider(name = LAC_TRANSACTIONS_DATA_PROVIDER)
    public Object[] provideLACTransactionsData() throws IOException {
        return extractTxtData(LAC_TRANSACTIONS_FILE_NAME);
    }

    @DataProvider(name = CARTRAWLER_TRANSACTIONS_DATA_PROVIDER)
    public Object[] provideCarTrawlerTransactionsData() throws IOException {
        return extractTxtData(CARTRAWLER_TRANSACTIONS_FILE_NAME);
    }

    @DataProvider(name = EXPEDIA_TRANSACTIONS_DATA_PROVIDER)
    public Object[] provideExpediaTransactionsData() throws IOException {
        return extractTxtData(EXPEDIA_TRANSACTIONS_FILE_NAME);
    }

    @DataProvider(name = COSTCODIRECT_TRANSACTIONS_DATA_PROVIDER)
    public Object[] provideCostcoDirectTransactionsData() throws IOException {
        return extractTxtData(COSTCODIRECT_TRANSACTIONS_FILE_NAME);
    }

    @DataProvider(name = COSTCOTOUR_TRANSACTIONS_DATA_PROVIDER)
    public Object[] provideCostcoTourTransactionsData() throws IOException {
        return extractTxtData(COSTCOTOUR_TRANSACTIONS_FILE_NAME);
    }

    private Object[] extractTxtData(String fileName){
        List<String> data = new ArrayList<>();
        BufferedReader txtReader = null;
        try {
            txtReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = txtReader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (txtReader != null) {
                    txtReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data.toArray();
    }
}



