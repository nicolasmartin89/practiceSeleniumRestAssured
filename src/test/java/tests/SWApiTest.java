package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SWApiTest {
    /**
     * Test the endpoint people/2/ and check the success response,
     * the skin color to be gold, and the amount of films it appears on (should be
     * 6).
     */
    @Test
    public void swapiExc1() {
        Response response;
        response = given().get("https://swapi.dev/api/people/2/");

        assertEquals(200, response.getStatusCode(), "Status OK 200");

        String skinColor = response.path("skin_color");
        assertEquals("gold", skinColor, "Sking color should be gold");

        List<String> films = response.path("films");
        Integer filmsCount = films.size();
        assertEquals(6, filmsCount, "Films should be 6");
    }

    /**
     * Request the endpoint of the second movie in which people/2/
     * was present (using the response from people/2/).
     * Check the release date to be in the correct date format,
     * and the response to include characters, planets, starships,
     * vehicles and species, each element including more than 1 element.
     */
    @Test
    public void swapiExc2() {
        Response response;
        response = given().get("https://swapi.dev/api/people/2/");
        List<String> films = response.path("films");
        String secondMovie = films.get(1);

        Response responseSecondMovie;
        responseSecondMovie = given().get(secondMovie);
        String releaseDateMovie = responseSecondMovie.path("release_date");

        assert isValidDateFormat(releaseDateMovie, "yyyy-MM-dd");

    }

    private boolean isValidDateFormat(String date, String format) {
        try {
            LocalDate.parse(date, java.time.format.DateTimeFormatter.ofPattern(format));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void assertHasMultipleElements(Response response, String elementName) {
        List<Object> elements = response.path(elementName);
        assert elements.size() > 1 : "Expected more than 1 " + elementName;
    }
}
