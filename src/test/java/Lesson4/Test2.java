package Lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test2 extends AbstractTest{

    @BeforeAll
    static void setUp(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @Test
    void getPasta() {
        given().spec(getRequestSpecification())
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "query=pasta&apiKey=" +getApiKey())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getGluten() {
        given().spec(getRequestSpecification())
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "intolerances=gluten&maxCarbs=100&apiKey=" +getApiKey())
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getVegetarian() {
        given().spec(getRequestSpecification())
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "diet=vegetarian&apiKey=" +getApiKey())
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getTomato() {
        given().spec(getRequestSpecification())
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "includeIngredients=tomato&apiKey=" +getApiKey())
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getNoTomato() {
        given().spec(getRequestSpecification())
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "excludeIngredients=tomato&apiKey=" +getApiKey())
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getBurger(){
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","Burger")
                .post(getBaseUrl()+"recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("American"));
    }

    @Test
    void getPizza() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","pizza")
                .post(getBaseUrl()+"recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Mediterranean"));

    }

    @Test
    void getSushi() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","sushi")
                .post(getBaseUrl()+"recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Japanese"));
    }

    @Test
    void getShawarma(){
        given().spec(requestSpecification)
                .when()
                .formParam("title","shawarma")
                .post(getBaseUrl()+"recipes/cuisine").prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getWings(){
        given().spec(requestSpecification)
                .when()
                .formParam("title","chicken wings")
                .formParam("language", "en")
                .post(getBaseUrl()+"recipes/cuisine").prettyPeek()
                .then()
                .spec(responseSpecification);
    }

}

