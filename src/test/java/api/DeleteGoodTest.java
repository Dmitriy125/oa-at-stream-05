package api;

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


public class DeleteGoodTest extends BaseApiTest {

    private int testId;
    String url = BASE_URL + "/goods/{id}";
    private List<String> allTestId;
    private int uniqueId;

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
        log.info(String.valueOf(response.getBody()));

        JsonNode responseBody = response.getBody();
        this.testId = responseBody.get("goods").get(0).get("id").asInt();
        this.allTestId = responseBody.get("goods").findValuesAsText("id");
        this.uniqueId = generateUniqueId(allTestId);
        log.info("ID удаляемого товара: " + testId);
    }

    @Tag("api")
    @Test
    void deleteGoodTest() {

        HttpHeaders headers = createAuthHeader();

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class, testId);

        Assertions.assertEquals(200, response.getStatusCodeValue(), "Не удалось удалить товар");
    }

    @Tag("api")
    @Test
    void deleteDoesNotExistGood() {

        HttpHeaders headers = createAuthHeader();
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        HttpClientErrorException.NotFound exception = Assertions
                .assertThrows(HttpClientErrorException.NotFound.class, () -> restTemplate.exchange(url, HttpMethod.DELETE, entity, JsonNode.class, uniqueId));
        log.info(exception.getMessage());

        Assertions.assertEquals(404, exception.getStatusCode().value(), "Код ответа должен быть 404");
        log.info(exception.getResponseBodyAsString());
    }
}
