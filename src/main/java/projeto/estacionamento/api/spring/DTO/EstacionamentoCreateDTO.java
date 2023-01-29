package projeto.estacionamento.api.spring.DTO;

import lombok.Data;

@Data
public class EstacionamentoCreateDTO {
    private String license;
    private String state;
    private String model;
    private String color;
}
