package com.sneo.data;

import java.util.Arrays;
import java.util.List;

/**
 * Class contains common constants
 *
 * @author ikumar
 */
public class SneoConstants {

    public static final String SNEO_ABS_PATH = "src/main/java/com/sneo/api";

    public static final String REQ_1 = "/compare/request/xml/compare/request1.xml";
    public static final String REQ_2 = "/compare/request/xml/compare/request2.xml";
    public static final String REQ_3 = "/compare/request/xml/compare/request3.xml";


    public static final List<String> EMRBB_TAGS = Arrays.asList("Envelope",
            "Body",
            "Response",
            "OTA_VehAvailRateRS",
            "VehAvailRSCore",
            "VehRentalCore",
            "PickUpLocation",
            "ReturnLocation",
            "VehVendorAvails",
            "VehVendorAvail",
            "VehAvails",
            "VehAvail",
            "VehAvailCore",
            "Vehicle",
            "RentalRate",
            "TotalCharge",
            "Info",
            "LocationDetails",
            "Address",
            "Telephone");

    public static final List<String> RATERULE_TAGS = Arrays.asList("Envelope",
            "Body",
            "Response",
            "OTA_VehRateRuleRS",
            "VehRentalCore",
            "PickUpLocation",
            "ReturnLocation",
            "Vehicle",
            "RentalRate",
            "TotalCharge",
            "LocationDetails",
            "Address",
            "Telephone");
}
