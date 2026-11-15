import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;

public class RestAssuredTest {

    public record Request1(String name, Double price) {
    }

    private RequestSpecification basicRQ = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080")
            .log(LogDetail.ALL)
            .addQueryParam("page", 0)
            .build();


    @Test
    void testGivenWhenThen() {
        given()
                .baseUri("http://localhost:8080")
                .log().all()
                .queryParam("page", 0)
                .queryParam("size", 100)
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("isEmpty()", is(false))
                ;
    }

    @Test
    void testRequestSpecification() {
        given()
                .spec(basicRQ)
                .queryParam("size", 200)
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("isEmpty()", is(false));
    }

    @Test
    void testAddProductAndCheckingUsingRestAssured() {
    given()
            .spec(basicRQ)
            .auth()
            .basic("admin", "secret123")
            .contentType(ContentType.JSON)
            .body(new Request1("Window", 79.99))
            .post("/goods/add")
            .then()
            .log().all()
            .statusCode(anyOf(is(200), is(201)))
            .contentType(ContentType.JSON);

    given()
            .spec(basicRQ)
            .queryParam("size", 200)
            .get("/goods/list")
            .then()
            .log().all()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("goods.name", hasItem("Window"))
            .body("goods.price", hasItems(79.99f));
    }

    @Test
    void testAddProductAndCheckingUsingAssertj() {
        given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(new Request1("Door", 59.99))
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(anyOf(is(200), is(201)))
                .contentType(ContentType.JSON);

        Response response = given()
                .spec(basicRQ)
                .queryParam("size", 200)
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.statusCode())
                .as("Код ответа должен быть 200")
                .isEqualTo(200);
        assertThat(response.jsonPath().getList("goods.name", String.class))
                .as("Поле \"name\" должно быть равно \"Door\"")
                .contains("Door");
        assertThat(response.jsonPath().getList("goods.price", Double.class))
                .as("Поле \"price\" должно быть равно \"59.99\"")
                .contains(59.99);
    }
}

