package projeto.estacionamento.api.spring.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import projeto.estacionamento.api.spring.DTO.EstacionamentoDTO;
import projeto.estacionamento.api.spring.exception.EstacionamentoNotFound;
import projeto.estacionamento.api.spring.model.Estacionamento;

@Service
public class EstacionamentoService {
    private static Map<String, Estacionamento> estacionamentoMap = new HashMap();
    

    static {
        var id = getUUID();
        var id2 = getUUID();
        var id3 = getUUID();
        Estacionamento estacionamento = new Estacionamento(id, "DMS-1111", "SC", "CELTA", "PRETO");
//        Estacionamento estacionamento1 = new Estacionamento(id2, "DWV-1112", "RJ", "KWID", "AZUL");
//        Estacionamento estacionamento2 = new Estacionamento(id3, "DWV-1112", "RJ", "KWID", "AZUL", LocalDateTime.parse("2023-01-26T19:00:00"));
        estacionamentoMap.put(id, estacionamento);
//        estacionamentoMap.put(id2, estacionamento1);
//        estacionamentoMap.put(id3, estacionamento2);
    }
    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<Estacionamento> findAll(){
        return estacionamentoMap.values().stream().collect(Collectors.toList());
    }

    public Estacionamento findById(String id){
        Estacionamento est = estacionamentoMap.get(id);
        if(est == null){
            throw new EstacionamentoNotFound(id);
        }
        return est;
    }

    public Estacionamento create(Estacionamento estacionamento){
        estacionamento.setEntryDate(LocalDateTime.now());
        estacionamentoMap.put(getUUID(),estacionamento);
        return estacionamento;
    }

    public void delete(String id){
        Estacionamento estacionamento = findById(id);
        if(estacionamento == null){
            throw new EstacionamentoNotFound(id);
        }
        estacionamentoMap.remove(id);
    }
    public Estacionamento update(String id, Estacionamento estacionamento){
        Estacionamento est = findById(id);
        est.setColor(estacionamento.getColor());
        estacionamentoMap.replace(id, est);
        return est;

    }

    public Estacionamento exit(String id){
        Estacionamento est =  findById(id);
        est.setExitDate(LocalDateTime.now());
        Duration time = Duration.between(est.getEntryDate(), est.getExitDate());
        Double bill = 7.0 * time.toHoursPart();
        est.setBill(bill);
        return est;
    }
}
