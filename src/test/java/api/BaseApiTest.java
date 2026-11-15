package api;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

public abstract class BaseApiTest {
    protected static final String BASE_URL = "http://localhost:8080";
    protected static final Logger log = LoggerFactory.getLogger(GetGoodsListTest.class);
    protected RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplateBuilder()
                .rootUri(BASE_URL)
                .additionalMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    protected HttpHeaders createAuthHeader() {
        final String USERNAME = "admin";
        final String PASSWORD = "secret123";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);
        String auth = USERNAME + ":" + PASSWORD;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        headers.set("Authorization", "Basic " + encodedAuth);

        return headers;
    }
}


