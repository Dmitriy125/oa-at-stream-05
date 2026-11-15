package api;

import com.example.project.model.ProductRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


public class PatchGoodTest extends BaseApiTest {

    private int testId;
    private String testName;
    private String uniqueName;
    private List<String> allTestName;

    private static final Double PRICE = DataHelper.generateRandomDouble();
    String url = BASE_URL + "/goods/{id}";
    private List<String> allTestId;
    private int uniqueId;
    private String testSecondName;

    private String generateUniqueName(List<String> existingNames) {
        String newName;
        do {
            newName = DataHelper.randomString(5);
        } while (existingNames.contains(newName));
        return newName;
    }

    private int generateUniqueId(List<String> existingId) {
        int newId;
        do {
            newId = DataHelper.generateRandomInteger();
        } while (existingId.contains(newId));
        return newId;
    }

    @BeforeEach
    public void searchGoodsList() {
        String url = BASE_URL + "/goods/list?page=0&size=500";

        HttpHeaders headers = createAuthHeader();
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);

        Assertions.assertEquals(200, response.getStatusCodeValue(), "Код ответа должен быть 200");
        log.info("Список всех товаров: " + response.getBody());

        JsonNode responseBody = response.getBody();
        this.testId = responseBody.get("goods").get(0).get("id").asInt();
        this.testName = responseBody.get("goods").get(0).get("name").asText();
        this.testSecondName = responseBody.get("goods").get(1).get("name").asText();
        this.allTestName = responseBody.get("goods").findValuesAsText("name");
        this.uniqueName = generateUniqueName(allTestName);
        this.allTestId = responseBody.get("goods").findValuesAsText("id");
        this.uniqueId = generateUniqueId(allTestId);
        log.info("Id изменяемого товара: " + testId);
        log.info("Имя изменяемого товара: " + testName);
        log.info("Все существующие названия товара: " + allTestName);
        log.info("Новое название товара: " + uniqueName);
    }

    @Tag("api")
    @Test
    void updateGoodInfo() {

        ProductRequest request = new ProductRequest()
                .name(uniqueName)
                .price(PRICE);

        HttpHeaders headers = createAuthHeader();

        HttpEntity<ProductRequest> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, JsonNode.class, testId);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(200, response.getStatusCodeValue(), "Код ответа должен быть 200");
        log.info("Измененные значения товара: " + response.getBody());
    }

    @Tag("api")
    @Test
    void negativeUpdateGood() {

        ProductRequest request = new ProductRequest()
                .name(testSecondName)
                .price(PRICE);
        log.info(request.toString());

        HttpHeaders headers = createAuthHeader();
        HttpEntity<ProductRequest> requestEntity = new HttpEntity<>(request, headers);
        HttpClientErrorException.BadRequest exception = Assertions
                .assertThrows(HttpClientErrorException.BadRequest.class, () -> restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, JsonNode.class, testId));
        log.info(exception.getMessage());

        Assertions.assertEquals(400, exception.getStatusCode().value(), "Код ответа должен быть 400");
        log.info(exception.getResponseBodyAsString());
    }

    @Tag("api")
    @Test
    void negativeUpdateGood2() {

        ProductRequest request = new ProductRequest()
                .name(uniqueName)
                .price(PRICE);
        log.info(request.toString());

        HttpHeaders headers = createAuthHeader();
        HttpEntity<ProductRequest> requestEntity = new HttpEntity<>(request, headers);
        HttpClientErrorException.NotFound exception = Assertions
                .assertThrows(HttpClientErrorException.NotFound.class, () -> restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, JsonNode.class, uniqueId));
        log.info(exception.getMessage());

        Assertions.assertEquals(404, exception.getStatusCode().value(), "Код ответа должен быть 404");
        log.info(exception.getResponseBodyAsString());
    }
}
