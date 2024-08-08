package med.voli.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import med.voli.api.domain.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //define a classe a ser mapeada como um advice, ou seja, irá ser sempre consultada pelo spring para verificar se um de seus métodos sera captado, nesse caso, as exceptions
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class) //define que o metodo sera executado caso uma EntityNotFoundException for lançada
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    } //retorna erro 404

    @ExceptionHandler(MethodArgumentNotValidException.class) //define que o metodo sera executado caso uma MethodArgumentNotValidException for lançada
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors(); //pega quais campos estão preenchidos incorretamente

        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList()); //retorna erro 400 e os campos mal preenchidos
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record DadosErroValidacao(String campo, String mensagem) { //record local para converter FieldError em string
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}