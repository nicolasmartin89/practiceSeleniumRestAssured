package tests;

import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class ExampleRestAssured {

    @Test
    public void getExample() {
        // given
        Response response;
        // when
        response = given().get("https://jsonplaceholder.typicode.com/users");
        // then
        assertEquals(response.getStatusCode(), 200, "Status code 200 OK!");

    }

    @Test
    public void postExample() {
        // Given
        Response response;

        Map<String, String> user = new HashMap<String, String>();
        user.put("name", "nicolas");
        user.put("email", "asd@asd.com");
        // When
        response = given().get("https://654e30bbcbc3253557428020.mockapi.io/api/v1/comment");
        given().contentType(ContentType.JSON)
                .body(user).when()
                .post("https://654e30bbcbc3253557428020.mockapi.io/api/v1/user");

        // Then
        assertEquals(200, response.getStatusCode(), "Esperando un 404");

    }

}
