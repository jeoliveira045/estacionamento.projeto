package projeto.estacionamento.api.spring.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import projeto.estacionamento.api.spring.DTO.EstacionamentoDTO;
import projeto.estacionamento.api.spring.model.Estacionamento;
import projeto.estacionamento.api.spring.service.EstacionamentoService;

@RestController
@RequestMapping("/estacionamento")
@Api(tags = "Controller de estacionamento") // Define o titulo do controller no swagger
public class EstacionamentoController {

    private final EstacionamentoService service;
    private final EstacionamentoMapper mapper;

    public EstacionamentoController(EstacionamentoService estacionamentoService, EstacionamentoMapper estacionamentoMapper){
        this.service = estacionamentoService;
        this.mapper = estacionamentoMapper;
    }

    @GetMapping
    @ApiOperation("Busca por todos os carros estacionados") // Descreve os endpoints da nossa aplicação
    public ResponseEntity<List<EstacionamentoDTO>> findAll(){
        List<Estacionamento> listaEst = service.findAll();
        List<EstacionamentoDTO> resultado = mapper.toEstacionamentoDTOList(listaEst);
        return ResponseEntity.ok().body(resultado);
    }

    @GetMapping("/{id}")
    @ApiOperation("Procura um carro no estacionamento com base no seu ID")
    public ResponseEntity<EstacionamentoDTO> findById(@PathVariable String id){
        Estacionamento estacionamentoObj = service.findById(id);
        return ResponseEntity.ok().body(mapper.estacionamentoDTO(estacionamentoObj));
    }

    @PostMapping("/novo-carro")
    @ApiOperation("Registra um carro novo no estacionamento")
    public ResponseEntity<EstacionamentoDTO> create(@RequestBody EstacionamentoDTO estacionamento){
        Estacionamento est = mapper.estacionamento(estacionamento);
        EstacionamentoDTO estDto = mapper.estacionamentoDTO(service.create(est));
        return ResponseEntity.status(HttpStatus.CREATED).body(estDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Retira algum carro do registro do estacionamento")
    public ResponseEntity delete(@PathVariable("id") String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("atualizar-carro/{id}")
    @ApiOperation("Atualiza os registros do carro estacionado")
    public ResponseEntity<EstacionamentoDTO> update(@PathVariable String id, @RequestBody EstacionamentoDTO estacionamentoDTO){
        Estacionamento estDto = mapper.estacionamento(estacionamentoDTO);
        Estacionamento est = service.update(id, estDto);
        EstacionamentoDTO estDto2 = mapper.estacionamentoDTO(est);
        return ResponseEntity.status(HttpStatus.OK).body(estDto2);

    }

    @PostMapping("saida-carro/{id}")
    @ApiOperation("Define o horario de saida do carro e o valor do estacionamento")
    public ResponseEntity<EstacionamentoDTO> exit(@PathVariable String id){
        EstacionamentoDTO est = mapper.estacionamentoDTO(service.exit(id));
        return ResponseEntity.ok().body(est);
    }


}
