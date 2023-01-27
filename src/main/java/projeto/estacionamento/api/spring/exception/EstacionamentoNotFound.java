package projeto.estacionamento.api.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)// Fala para spring que essa exceção precisa ser interpretada pelo spring como sendo um 404
public class EstacionamentoNotFound extends RuntimeException{

    public EstacionamentoNotFound(String id){
        super("Carro não encontrado com o id" + id); // Esse contrutor retorna a propriedade da classe pai(RuntimeException) de imprimir uma mensagem quando "jogado"(throw)
    }

}
