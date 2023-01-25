package projeto.estacionamento.api.spring.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<EstacionamentoDTO> findAll(){
        List<Estacionamento> listaEst = service.findAll();
        List<EstacionamentoDTO> resultado = mapper.toEstacionamentoDTOList(listaEst);
        return resultado;
        // for(Estacionamento e: service.findAll()){
        //     EstacionamentoDTO dto = new EstacionamentoDTO();
        //     dto.setId(e.getId());
        //     dto.setLicense(e.getLicense());
        //     dto.setState(e.getState());
        //     dto.setModel(e.getModel());
        //     dto.setColor(e.getColor());
        //     dto.setEntryDate(e.getEntryDate());
        //     dto.setExitDate(e.getExitDate());
        //     dto.setBill(e.getBill());
        //     listaDTO.add(dto);
        // }
        // return listaDTO;
    }
}

// private String id;
// private String license;
// private String state;
// private String model;
// private String color;
// private LocalDateTime entryDate;
// private LocalDateTime exitDate;
// private Double bill;
