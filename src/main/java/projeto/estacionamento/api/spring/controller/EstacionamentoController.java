package projeto.estacionamento.api.spring.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto.estacionamento.api.spring.DTO.EstacionamentoDTO;
import projeto.estacionamento.api.spring.model.Estacionamento;
import projeto.estacionamento.api.spring.service.EstacionamentoService;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    private final EstacionamentoService service;
    private final EstacionamentoMapper mapper;

    public EstacionamentoController(EstacionamentoService estacionamentoService, EstacionamentoMapper estacionamentoMapper){
        this.service = estacionamentoService;
        this.mapper = estacionamentoMapper;
    }

    @GetMapping
    public ResponseEntity<List<EstacionamentoDTO>> findAll(){
        List<Estacionamento> listaEst = service.findAll();
        List<EstacionamentoDTO> resultado = mapper.toEstacionamentoDTOList(listaEst);
        return ResponseEntity.ok().body(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstacionamentoDTO> findById(@PathVariable String id){
        return ResponseEntity.ok().body(mapper.estacionamentoDTO(service.findById(id)));
    }

    @PostMapping("/novo-carro")
    public ResponseEntity<Void> create(@RequestBody Estacionamento estacionamento){
        ResponseEntity.ok().build();
    }
}
