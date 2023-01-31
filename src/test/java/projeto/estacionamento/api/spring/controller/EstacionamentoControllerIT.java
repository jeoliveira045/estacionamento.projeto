package projeto.estacionamento.api.spring.controller;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito.Then;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import org.springframework.http.MediaType;
import projeto.estacionamento.api.spring.DTO.EstacionamentoDTO;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // É uma boa pratica. É um mecanismo pra realizar os teste web em portas aleatórias
public class EstacionamentoControllerIT extends AbstractContainerBase{

    @LocalServerPort
    private int randomPort; // Aqui é onde as portas são armazenadas

    @BeforeEach
    public void setUpTest(){
        System.out.println(randomPort);
        RestAssured.port = randomPort; // Aqui é onde as portas geradas aleatoriamente realmente usadas, ao receber
        // a anotação BeforeEach do JUnit
    }

    @Test
    void testCreate() {

    }

    @Test
    void whenFindAllCheckResultado() {

//        RestAssured.given()
//                .when()
//                .get("/estacionamento")
//                .then()
//                .statusCode(200)
//                .body("license[0]", Matchers.equalTo("DMS-1111"));

    }

    @Test
    void whenCreateTheCheckCreated(){
        EstacionamentoDTO est= new EstacionamentoDTO();
        est.setId("3");
        est.setLicense("DRT-3471");
        est.setModel("Corolla");
        est.setState("Belém");
        est.setColor("AMARELO");
        est.setEntryDate(LocalDateTime.parse("2023-01-17T15:34:00"));
        est.setExitDate(LocalDateTime.parse("2023-01-17T16:57:00"));
        est.setBill(47.50);

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE) // Configuração é por conta do swagger, que configura o
                // conteudo como MediaType o que força o spring a setar a maneira que o conteudo vai ser repassado
                // para o teste. No caso, o est vai ser repassado como JSON
                .body(est)
                .post("/estacionamento/novo-carro")
                .then()
                .statusCode(201)
                .body("license", Matchers.equalTo("DRT-3471")) // Mais de um body pode ser configurado
                // pra representar os campos do objeto
                .body("color", Matchers.equalTo("AMARELO"));
    }
}
