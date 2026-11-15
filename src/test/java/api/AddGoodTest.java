package api;

import com.example.project.model.ProductRequest;
import com.example.project.model.ResultMapStringLong;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException.*;


public class AddGoodTest extends BaseApiTest {

    private static final String NAME = DataHelper.randomString(5);
    private static final Double PRICE = DataHelper.generateRandomDouble();
    String url = BASE_URL + "/goods/add";

    @Tag("api")
    @Test
    public void testAddGood() {

        ProductRequest request = new ProductRequest()
                .name(NAME)
                .price(PRICE);

        HttpHeaders headers = createAuthHeader();

        HttpEntity<ProductRequest> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<ResultMapStringLong> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResultMapStringLong.class);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(200, response.getStatusCodeValue(), "Код ответа должен быть 200");
        log.info(response.getBody().toString());
    }

    @Tag("api")
    @Test
    void negativeTestAddGood() {

        ProductRequest request = new ProductRequest()
                .name(null)
                .price(PRICE);


        HttpHeaders headers = createAuthHeader();
        HttpEntity<ProductRequest> requestEntity = new HttpEntity<>(request, headers);

        BadRequest exception = Assertions.assertThrows(BadRequest.class, () -> restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResultMapStringLong.class));

        Assertions.assertEquals(400, exception.getStatusCode().value(), "Код ответа должен быть 400");
        log.info(exception.getResponseBodyAsString());
    }
}


