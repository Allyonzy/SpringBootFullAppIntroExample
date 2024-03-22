package ru.innopolis.shop.controller;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import ru.innopolis.shop.utils.FileLoader;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static io.restassured.RestAssured.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WireMockTest(httpPort = 8089)
public class CustomersWireMockAPITest {
    @LocalServerPort
    private int port;

    @Test
    @Disabled
    public void should_return_object_by_param() {
        stubFor(get(urlEqualTo("/customers/2"))
                .willReturn(aResponse()
                        .withBody(FileLoader.read("classpath:customersTest.json"))
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        when()
                .get(String.format("http://localhost:%s/customers", port))
                .then()
                .statusCode(is(200))
                .body(containsString("Customer 2"));

    }

}
