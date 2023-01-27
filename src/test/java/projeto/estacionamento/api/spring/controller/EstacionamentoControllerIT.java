package projeto.estacionamento.api.spring.controller;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito.Then;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // É uma boa pratica. É um mecanismo pra realizar os teste web em portas aleatórias
public class EstacionamentoControllerIT {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        System.out.println(randomPort);
        RestAssured.port = randomPort;
    }

    @Test
    void testCreate() {

    }

    @Test
    void whenFindAllCheckResultado() {
        // RestAssured.given()
        // .when()
        // .get("/estacionamento")
        // .then()
        // .statusCode(200)
        // .body()

        // ou

        RestAssured.given()
        .when()
        .get("/estacionamento")
        .body("license[0]", Matchers.equalTo("DMS-1111"))
        .then().extract().response().body().prettyPrint();

    }
}
