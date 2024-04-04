package test.resources.endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class Endpoints {

    public static Response FetchLatestUSD()
    {
        Response response = given()
                    .accept(ContentType.JSON)
                .when()
                    .get(Routes.latest_usd);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.path("result"), "success", "API Returned a non success response");
        return response;
    }
}
