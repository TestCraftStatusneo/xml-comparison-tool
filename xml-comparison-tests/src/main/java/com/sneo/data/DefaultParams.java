package com.sneo.data;


import com.sneo.datautils.CSVReader;
import com.sneo.datautils.PropertyUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import static com.sneo.core.Configuration.ENVIRONMENT;
import static java.lang.Integer.parseInt;

/**
 * Class contains default Parameters
 *
 * @author ikumar
 */
public class DefaultParams {

    public static final String US = "US";
    public static final String AVIS = "Avis";
    public static final String BUDGET = "Budget";
    public static final String PAYLESS = "Payless";

    public static final String QA = "QAuser";
    public static final String QA_PASS = "QApassword";

    public static final String GRANT_TYPE = "client_credentials";

    // * Batch 0 partners
    public static final String PRICELINE = "Priceline";
    public static final String PRICELINE_TEST = "Priceline_Test";
    public static final String PRICELINE_PASS = "Priceline_Password";
    public static final String PRICELINE_PATH = "/availability/request/xml/priceline/";
    public static final String PRICELINE_HEADER_XML_PATH = "/availability/request/xml/priceline/priceline_header.xml";
    public static final String PRICELINE_HEADER_DATES_XML_PATH = "/availability/request/xml/priceline/priceline_header_dates.xml";
    public static final String GBT = "GlobalBookingTool";
    public static final String GBT_PASS = "Kje2BMIHb3yp";
    public static final String GBT_PATH = "/availability/request/xml/globalbooking/";

    public static final String LAC = "LACMarketing";
    public static final String LAC_PASS = "jjBQ?#hQ]vuf";
    public static final String LAC_PATH = "/availability/request/xml/lacmarketing/";

    // * Batch 1 partners
    public static final String EasyRentCars = "EasyRentCars";
    public static final String GenericDual = "GenericDual";
    public static final String GenericDualAU = "GenericDualAU";
    public static final String UnitedRetail = "UnitedRetail";
    public static final String CostcoDirect = "CostcoDirect";
    public static final String CostcoTour = "CostcoTour";

    // * Batch 2 partners
    public static final String AECarTrawler = "AECarTrawler";
    public static final String CarTrawlerNet = "CarTrawlerNet";
    public static final String ArgusCarTrawler = "ArgusCarTrawler";
    public static final String CarTrawler = "CarTrawler";
    public static final String CarTrawlerIreland = "CarTrawlerIreland";
    public static final String CarTrawlerMG = "CarTrawlerMG";
    public static final String Expedia = "Expedia";
    public static final String ExpediaNA = "ExpediaNA";
    public static final String TravelJigsaw = "TravelJigsaw";
    public static final String TravelJigsawBE = "TravelJigsawBE";
    public static final String TravelJigsawRetail = "TravelJigsawRetail";
    public static final String TraveljigsawLtd = "TraveljigsawLtd";


    public static final String PICKUP_DATE = todayPlusDays("90", "10:00:00");
    public static final String DROPOFF_DATE = todayPlusDays("99", "10:00:00");

    public static final String CORP_DISCOUNT_NMBR = PropertyUtils.getValue(ENVIRONMENT + "_CDN");
    public static final String MEMBERSHIP_ID = PropertyUtils.getValue(ENVIRONMENT + "_MID");
    public static final String PROMO_CODE = PropertyUtils.getValue(ENVIRONMENT + "_PROMO");

    public static List<String[]> csvList = CSVReader.readAllRows("locations_data");

    public static final String DEFAULT_PICK = "LAX";
    public static final String DEFAULT_DROP = "LAX";

    //public static int RANDOM_NUMBER;
    public static String PICK_LOCATION;
    public static String RETURN_LOCATION;

    public static int getRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(csvList.size());
        return number;
    }


    public static void setPickAndDrop() {
        int number = getRandomNumber();
        PICK_LOCATION = csvList.get(number)[1];
        RETURN_LOCATION = csvList.get(number)[2];
    }


//    public static String todayPlusDays(String days) {
//        return LocalDateTime.now().plus(parseInt(days), ChronoUnit.DAYS).truncatedTo(ChronoUnit.SECONDS).toString();
//    }

    public static String todayPlusDays(String days, String time) {
        String tempDate;
        try {
            tempDate = LocalDateTime.now().plus(parseInt(days), ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS).toString().replace("00:00", time);
        } catch (Exception e) {
            tempDate = "";
        }
        return tempDate;
    }


}
