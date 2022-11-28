package Lesson3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class Test1 extends AbstractTest {

    @BeforeAll
    static void setUp(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @Test
    void getPasta() {
        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "query=pasta&apiKey=" +getApiKey())
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void getGluten() {
        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "intolerances=gluten&maxCarbs=100&apiKey=" +getApiKey())
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void getVegetarian() {
        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "diet=vegetarian&apiKey=" +getApiKey())
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void getTomato() {
        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "includeIngredients=tomato&apiKey=" +getApiKey())
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void getNoTomato() {
        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "excludeIngredients=tomato&apiKey=" +getApiKey())
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void getBurger() {
        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"title\": \"burger\",\n"
                        + "}")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response.get("cuisine"), equalTo("Mediterranean"));
        //assertThat(response.get("confidence"), equalTo(0.85));
    }

    @Test
    void getPizza() {
        given()
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"title\": \"pizza\",\n"
                        + "}")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void getSushi() {
        given()
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"title\": \"sushi\",\n"
                        + "}")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void getShawarma() {
        given()
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"title\": \"shawarma\",\n"
                        + "}")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void getWings() {
        given()
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"title\": \"chicken wings\",\n"
                        + "}")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200);
    }

}
