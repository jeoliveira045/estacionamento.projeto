package projeto.estacionamento.api.spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
// import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.stereotype.Component;

import projeto.estacionamento.api.spring.DTO.EstacionamentoDTO;
import projeto.estacionamento.api.spring.model.Estacionamento;
@Component
public class EstacionamentoMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public EstacionamentoDTO estacionamentoDTO(Estacionamento estacionamento){
        return MODEL_MAPPER.map(estacionamento, EstacionamentoDTO.class);
    }

    public List<EstacionamentoDTO> toEstacionamentoDTOList(List<Estacionamento> listaEst) {
        return listaEst.stream().map(this::estacionamentoDTO).collect(Collectors.toList());
    }

}
