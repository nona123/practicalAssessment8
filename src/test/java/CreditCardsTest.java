import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class CreditCardsTest extends BaseTest3{
    @Test(description = "Get error code is 200")
    public void testGetData() {
        // Send GET request to the endpoint
        Response response = RestAssured.get("https://fakerapi.it/api/v1/credit_cards?_quantity=1");

        // Verify the response code is 200
        assertEquals(200, response.getStatusCode());

        // Verify the response body contains the expected data
        String expectedType = "Visa";
        String expectedNumber = "4532597219397001";
        String expectedExpiration = "01/26";
        String expectedOwner = "Hanna Dickinson";

        assertEquals(expectedType, response.jsonPath().getString("data[0].type"));
        assertEquals(expectedNumber, response.jsonPath().getString("data[0].number"));
        assertEquals(expectedExpiration, response.jsonPath().getString("data[0].expiration"));
        assertEquals(expectedOwner, response.jsonPath().getString("data[0].owner"));
    }

}
