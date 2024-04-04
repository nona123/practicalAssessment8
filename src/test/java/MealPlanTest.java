import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Feature("Test Meal Plan")
public class MealPlanTest extends BaseTest{
    String apiKey = "3b8c9f0f039343b5bbb32addaff69f1a";
    String hash = "dba30d4b75c79b8b99845d6b5261de8999d07225";
    String username = "test123160";

    @Test(description="Generate MealPlan")
    public void MealPlanGenerateTest(){
        given()
                .queryParams("apiKey",apiKey)
                .log().ifValidationFails()
                .when()
                .get("generate")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                //Assertion isi 1 hari mealplan
                .body("week.monday.meals.size()",equalTo(3));
    }
    @Test(description = "Add item to mealplan")
    public void AddItemtoMealPlanTest(){
        String requestBody = "[\n" +
                "    {\n" +
                "        \"date\": 1589500800,\n" +
                "        \"slot\": 1,\n" +
                "        \"position\": 0,\n" +
                "        \"type\": \"INGREDIENTS\",\n" +
                "        \"value\": {\n" +
                "            \"ingredients\": [\n" +
                "                {\n" +
                "                    \"name\": \"1 banana\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"coffee\",\n" +
                "                    \"unit\": \"cup\",\n" +
                "                    \"amount\": \"1\",\n" +
                "                    \"image\": \"https://img.spoonacular.com/ingredients_100x100/brewed-coffee.jpg\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"date\": 1589500800,\n" +
                "        \"slot\": 2,\n" +
                "        \"position\": 0,\n" +
                "        \"type\": \"CUSTOM_FOOD\",\n" +
                "        \"value\": {\n" +
                "            \"id\": 348,\n" +
                "            \"servings\": 1,\n" +
                "            \"title\": \"Aldi Spicy Cashews - 30g\",\n" +
                "            \"image\": \"https://img.spoonacular.com/ingredients_100x100/cashews.jpg\"\n" +
                "        }\n" +
                "    }\n" +
                "]";
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParams("apiKey",apiKey)
                .queryParams("hash",hash)
                .body(requestBody)
                .log().ifValidationFails()
                .when()
                .post("/{username}/items",username)
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                //Assertion isi 1 hari mealplan
                .body("status",equalTo("success"));
    }
    @Test(description="Delete item from MealPlan")
    public void DeleteItemtoMealPlanTest(){
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParams("apiKey",apiKey)
                .queryParams("hash",hash)
                .when()
                .delete("/{username}/items/25640984",username)
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                //Assertion isi 1 hari mealplan
                .body("status",equalTo("success"));
    }

}