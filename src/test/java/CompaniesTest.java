import org.testng.annotations.Test;

public class CompaniesTest extends BaseTest3{
    @Test(description = "Get error code is 200")
    public void testStatusCode() {
        given()
                .when()
                .get("https://fakerapi.it/api/v1/companies?_quantity=1")
                .then()
                .assertThat()
                .statusCode(200);
    }

}
