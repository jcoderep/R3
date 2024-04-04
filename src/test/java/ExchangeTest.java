package test.java;

import test.resources.utilities.BaseTest;
import test.resources.endpoints.Endpoints;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.AssertionError;
import java.io.InputStream;
import java.time.Instant;
import java.util.HashMap;


public class ExchangeTest extends BaseTest {

    @Test()
    public void testValidateAEDExchangeRate()
    {
        logger.info("**************** Initiating Validate AED Exchange Price Test ******************");
        Response response = Endpoints.FetchLatestUSD();
        HashMap<String, Float> currExchange = new HashMap<>(response.path("rates"));
        double rate_aed = currExchange.get("AED");

        if (3.6 < rate_aed && rate_aed < 3.7)
        {
            logger.info("USD to AED exchange rate is within range of 3.6 - 3.7");
        }
        else
        {
            throw new AssertionError("USD to AED exchange rate is not within range of 3.6 - 3.7");
        }
        logger.info("**************** Validate AED Exchange Price Test Completed Successfully ******************");
    }

    @Test()
    public void testValidateResponseTime()
    {
        logger.info("**************** Initiating Validate Response Time Test ******************");
        Response response = Endpoints.FetchLatestUSD();
        long currentUnixTime = Instant.now().getEpochSecond();
        Integer response_unix_time = response.path("time_last_update_unix");
        Assert.assertTrue ((currentUnixTime - response_unix_time) >= 3);
        logger.info("**************** Validate Response Time Test Completed Successfully ******************");
    }

    @Test()
    public void testValidateSchema()
    {
        logger.info("**************** Initiating Validate Schema Test ******************");
        Response response = Endpoints.FetchLatestUSD();
        InputStream currConversionSchema = getClass().getClassLoader().getResourceAsStream("currConversionSchema.json");
        if (currConversionSchema != null) {
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(currConversionSchema));
        }
        else {
            logger.error("Provided schema is null, please provide correct schema for validation");
        }
        logger.info("**************** Validate Schema Test Completed Successfully ******************");
    }

    @Test()
    public void testValidateCurrCount()
    {
        logger.info("**************** Initiating Validate Currency Count Test ******************");
        Response response = Endpoints.FetchLatestUSD();
        HashMap<String, Float> currExchange = new HashMap<>(response.path("rates"));
        Assert.assertEquals(currExchange.size(), 162);
        logger.info("**************** Validate Currency Count Test Completed Successfully ******************");
    }
}
