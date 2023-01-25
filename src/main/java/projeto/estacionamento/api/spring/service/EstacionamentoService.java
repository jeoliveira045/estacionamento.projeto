package projeto.estacionamento.api.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import projeto.estacionamento.api.spring.model.Estacionamento;

@Service
public class EstacionamentoService {
    private static Map<String, Estacionamento> estacionamentoMap = new HashMap();

    static {
        var id = getUUID();
        Estacionamento estacionamento = new Estacionamento(id, "DMS-1111", "SC", "CELTA", "PRETO");
        estacionamentoMap.put(id, estacionamento);
    }
    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<Estacionamento> findAll(){
        return estacionamentoMap.values().stream().collect(Collectors.toList());
    }
}
