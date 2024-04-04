import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Feature("Test Meal Plan")
public class RecipeTest extends BaseTest2 {
    String apiKey = "3b8c9f0f039343b5bbb32addaff69f1a";
    String hash = "dba30d4b75c79b8b99845d6b5261de8999d07225";
    String username = "test123160";

    @Test(description = "Search Recipes")
    public void SearchRecipesTest(){

    }

}