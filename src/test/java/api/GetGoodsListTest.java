package api;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


public class GetGoodsListTest extends BaseApiTest {

    @Tag("api")
    @Test
    void searchGoodsList() {
        String url = BASE_URL + "/goods/list?page=0&size=500";

        HttpHeaders headers = createAuthHeader();
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        log.info(String.valueOf(response.getBody()));
    }
}
