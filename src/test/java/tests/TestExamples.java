package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExamples {

    public static final String BASE_URL = "https://reqres.in/api";

    public static final String GET_PATH = "/users?page=2";

    public static final String CONTENT_TYPE_HEADER = "content-type";
    public static final String SUPPORT_URL = "https://reqres.in/#support-heading";


    // test_1 GET request
    @Test
    public void testGetStatusCode() {

        Response response = get(BASE_URL + GET_PATH);

        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader(CONTENT_TYPE_HEADER));

        int actualStatusCode = response.getStatusCode();

        Assert.assertEquals(actualStatusCode, 200);

    }

    //test_2
    @Test
    public void testGetId() {

        given().get(BASE_URL + GET_PATH)
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data.id[0]", equalTo(7))
                .log().all();
    }

    //test_3
    @Test
    public void testGetSupport() {

        given().get(BASE_URL + GET_PATH)
                .then()
                .statusCode(200)
                .body("support.url", equalTo(SUPPORT_URL));
    }
}
