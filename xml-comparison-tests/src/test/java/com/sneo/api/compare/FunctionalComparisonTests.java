package com.sneo.api.compare;


import com.google.common.collect.Iterators;
import com.sneo.core.TestBase;
import com.sneo.data.AvisDataProvider;
import com.sneo.datautils.FileReader;
import com.sneo.utilities.XMLUtils2;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xmlunit.diff.Difference;

import java.util.Iterator;
import java.util.Map;

import static com.sneo.data.AvisDataProvider.REQUESTS_DATA;
import static com.sneo.data.SneoConstants.*;


/**
 * Class contains DCCompareVehAvailsTests tests
 *
 * @author ikumar
 */
@Log4j2
public class FunctionalComparisonTests extends TestBase {


    // ###########################_Costco Direct_MRBB_#########################



    @Test(dataProvider = REQUESTS_DATA, dataProviderClass = AvisDataProvider.class)
    public void Compare_Pass_Test(Map<String, String> map) {
        String req1 = FileReader.readFile(SNEO_ABS_PATH+REQ_1);
        logCodeBlock(req1);
        String req2 = FileReader.readFile(SNEO_ABS_PATH+REQ_2);
        logCodeBlock(req2);

        Iterator<Difference> iterator = XMLUtils2.XMLDifferences(req1, req2);
        Assert.assertEquals(Iterators.size(iterator), 0, "One or more differences have been found");
    }


    @Test(dataProvider = REQUESTS_DATA, dataProviderClass = AvisDataProvider.class)
    public void Compare_Fail_Test(Map<String, String> map) {
        String req1 = FileReader.readFile(SNEO_ABS_PATH+REQ_1);
        logCodeBlock(req1);
        String req2 = FileReader.readFile(SNEO_ABS_PATH+REQ_3);
        logCodeBlock(req2);

        Iterator<Difference> iterator = XMLUtils2.XMLDifferences(req1, req2);
        Assert.assertEquals(Iterators.size(iterator), 0, "One or more differences have been found");
    }





}




