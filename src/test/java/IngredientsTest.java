import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Feature("Test Ingredients")
public class IngredientsTest extends BaseTest{
    String apiKey = "3b8c9f0f039343b5bbb32addaff69f1a";
    String hash = "dba30d4b75c79b8b99845d6b5261de8999d07225";
    String username = "test123160";

    @Test(description = "Search Ingredients")
    public void IngredientsSearchTest() {
        given()
                .queryParams("apiKey", apiKey)
                .queryParams("query", "banana") // Add query parameter
                .queryParams("number", 2) // Add number parameter
                .queryParams("sort", "calories") // Add sort parameter
                .queryParams("sortDirection", "desc") // Add sortDirection parameter
                .log().ifValidationFails()
                .when()
                .get("/food/ingredients/search")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("results[0].id", equalTo(19400)) // Assert the first result's id
                .body("results[1].id", equalTo(93779)); // Assert the second result's id
    }


    @Test(description = "Get Ingredient Information")
    public void getIngredientInformation() {
        given()
                .queryParams("apiKey", apiKey)
                .queryParams("amount", 1)
                .when()
                .get("/food/ingredients/9266/information")
                .then()
                .statusCode(200)
                .body("id", equalTo(9266))
                .body("original", equalTo("pineapples"))
                .body("name", equalTo("pineapples"))
                .body("amount", equalTo(1.0f))
                .body("unit", equalTo(""))
                .body("unitShort", equalTo(""))
                .body("unitLong", equalTo(""))
                .body("possibleUnits", hasItems("piece", "slice", "fruit", "g", "oz", "cup", "serving"))
                .body("estimatedCost.value", equalTo(299.0f))
                .body("estimatedCost.unit", equalTo("US Cents"))
                .body("consistency", equalTo("solid"))
                .body("shoppingListUnits", hasItems("pieces"))
                .body("aisle", equalTo("Produce"))
                .body("image", equalTo("pineapple.jpg"))
                .body("categoryPath", hasItems("tropical fruit", "fruit"));
        // Add assertions for nutrition, properties, flavonoids, caloricBreakdown, and weightPerServing if needed
    }

    @Test(description = "Parse Ingredients")
    public void parseIngredients() {
        String requestBody = "[\n" +
                "    {\n" +
                "        \"id\": 10014355,\n" +
                "        \"original\": \"1 cup green tea\",\n" +
                "        \"originalName\": \"green tea\",\n" +
                "        \"name\": \"tea\",\n" +
                "        \"nameClean\": \"green tea\",\n" +
                "        \"amount\": 1.0,\n" +
                "        \"unit\": \"cup\",\n" +
                "        \"unitShort\": \"cup\",\n" +
                "        \"unitLong\": \"cup\",\n" +
                "        \"possibleUnits\": [\n" +
                "            \"g\",\n" +
                "            \"oz\",\n" +
                "            \"fluid ounce\",\n" +
                "            \"cup\"\n" +
                "        ],\n" +
                "        \"estimatedCost\": {\n" +
                "            \"value\": 1786.86,\n" +
                "            \"unit\": \"US Cents\"\n" +
                "        },\n" +
                "        \"consistency\": \"solid\",\n" +
                "        \"aisle\": \"Tea and Coffee\",\n" +
                "        \"image\": \"green-tea-leaves.jpg\",\n" +
                "        \"meta\": [\n" +
                "            \"green\"\n" +
                "        ],\n" +
                "        \"nutrition\": {\n" +
                "            \"nutrients\": [\n" +
                "                {\n" +
                "                    \"name\": \"Calories\",\n" +
                "                    \"amount\": 2.36,\n" +
                "                    \"unit\": \"kcal\",\n" +
                "                    \"percentOfDailyNeeds\": 0.12\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Fat\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"percentOfDailyNeeds\": 0.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Saturated Fat\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"percentOfDailyNeeds\": 0.03\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Carbohydrates\",\n" +
                "                    \"amount\": 0.71,\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"percentOfDailyNeeds\": 0.24\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Net Carbohydrates\",\n" +
                "                    \"amount\": 0.71,\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"percentOfDailyNeeds\": 0.26\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Sugar\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"percentOfDailyNeeds\": 0.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Cholesterol\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"percentOfDailyNeeds\": 0.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Sodium\",\n" +
                "                    \"amount\": 7.08,\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"percentOfDailyNeeds\": 0.31\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Caffeine\",\n" +
                "                    \"amount\": 47.2,\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"percentOfDailyNeeds\": 15.73\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Protein\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"percentOfDailyNeeds\": 0.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Manganese\",\n" +
                "                    \"amount\": 0.52,\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"percentOfDailyNeeds\": 25.84\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Folate\",\n" +
                "                    \"amount\": 11.8,\n" +
                "                    \"unit\": \"Âµg\",\n" +
                "                    \"percentOfDailyNeeds\": 2.95\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Potassium\",\n" +
                "                    \"amount\": 87.32,\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"percentOfDailyNeeds\": 2.49\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Vitamin B2\",\n" +
                "                    \"amount\": 0.03,\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"percentOfDailyNeeds\": 1.94\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Magnesium\",\n" +
                "                    \"amount\": 7.08,\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"percentOfDailyNeeds\": 1.77\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Copper\",\n" +
                "                    \"amount\": 0.02,\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"percentOfDailyNeeds\": 1.18\n" +
                "                }\n" +
                "            ],\n" +
                "            \"properties\": [\n" +
                "                {\n" +
                "                    \"name\": \"Glycemic Index\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Glycemic Load\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"flavonoids\": [\n" +
                "                {\n" +
                "                    \"name\": \"Cyanidin\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Petunidin\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Delphinidin\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Malvidin\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Pelargonidin\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Peonidin\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Catechin\",\n" +
                "                    \"amount\": 3.56,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Epigallocatechin\",\n" +
                "                    \"amount\": 19.0,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Epicatechin\",\n" +
                "                    \"amount\": 5.03,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Epicatechin 3-gallate\",\n" +
                "                    \"amount\": 13.83,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Epigallocatechin 3-gallate\",\n" +
                "                    \"amount\": 22.09,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Theaflavin\",\n" +
                "                    \"amount\": 3.73,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Thearubigins\",\n" +
                "                    \"amount\": 191.87,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Eriodictyol\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Hesperetin\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Naringenin\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Apigenin\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Luteolin\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Isorhamnetin\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Kaempferol\",\n" +
                "                    \"amount\": 3.33,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Myricetin\",\n" +
                "                    \"amount\": 1.06,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Quercetin\",\n" +
                "                    \"amount\": 5.17,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Theaflavin-3,3'-digallate\",\n" +
                "                    \"amount\": 4.13,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Theaflavin-3'-gallate\",\n" +
                "                    \"amount\": 3.56,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Theaflavin-3-gallate\",\n" +
                "                    \"amount\": 0.0,\n" +
                "                    \"unit\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Gallocatechin\",\n" +
                "                    \"amount\": 2.95,\n" +
                "                    \"unit\": \"mg\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"caloricBreakdown\": {\n" +
                "                \"percentProtein\": 0.0,\n" +
                "                \"percentFat\": 0.0,\n" +
                "                \"percentCarbs\": 100.0\n" +
                "            },\n" +
                "            \"weightPerServing\": {\n" +
                "                \"amount\": 236,\n" +
                "                \"unit\": \"g\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "]";

        given()
                .queryParam("apiKey", apiKey)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/recipes/parseIngredients")
                .then()
                .statusCode(200)
                .body("[0].id", equalTo(10014355),
                        "[0].original", equalTo("1 cup green tea"),
                        "[0].name", equalTo("tea"),
                        "[0].aisle", equalTo("Tea and Coffee"));
    }
}