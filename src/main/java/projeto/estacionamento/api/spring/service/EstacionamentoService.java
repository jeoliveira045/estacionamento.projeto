package projeto.estacionamento.api.spring.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import projeto.estacionamento.api.spring.DTO.EstacionamentoDTO;
import projeto.estacionamento.api.spring.exception.EstacionamentoNotFound;
import projeto.estacionamento.api.spring.model.Estacionamento;
import projeto.estacionamento.api.spring.repository.EstacionamentoRepository;

@Service
public class EstacionamentoService {

    private EstacionamentoRepository repository;

    public EstacionamentoService(EstacionamentoRepository estacionamento){
        this.repository = estacionamento;
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<Estacionamento> findAll(){
        return repository.findAll();
    }

    public Estacionamento findById(String id){
        return repository.findById(id).orElseThrow(() ->
                new EstacionamentoNotFound(id));
    }

    public Estacionamento create(Estacionamento estacionamento){
        String uuid = getUUID();
        estacionamento.setId(uuid);
        estacionamento.setEntryDate(LocalDateTime.now());
        repository.save(estacionamento);
        return estacionamento;
    }

    public void delete(String id){
        repository.deleteById(id);
    }
    public Estacionamento update(String id, Estacionamento estacionamento){
        Estacionamento est = findById(id);
        est.setColor(estacionamento.getColor());
        repository.save(est);
        return est;

    }

//    public Estacionamento exit(String id){
//        Estacionamento est =  findById(id);
//        est.setExitDate(LocalDateTime.now());
//        Duration time = Duration.between(est.getEntryDate(), est.getExitDate());
//        Double bill = 5.0 + 7.0 * time.toHoursPart();
//        est.setBill(bill);
//        return est;
//    }

    public Estacionamento checkout(String id){
        Estacionamento est = findById(id);
        est.setExitDate(LocalDateTime.now());
        est.setBill(EstacionamentoCheckOut.getBill(est));
        repository.save(est);
        return est;

    }
}
