package med.voli.api.controller;

import jakarta.validation.Valid;
import med.voli.api.domain.usuario.DadosAutenticacaoRecord;
import med.voli.api.domain.usuario.Usuario;
import med.voli.api.infra.security.DadosTokenJWTRecord;
import med.voli.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //define a classe como uma controller de uma API Rest
@RequestMapping("/login") //define qual o mapeamento de requisição para essa controller
public class AutenticacaoController {

    @Autowired //injeção de dependencia | Classe padrão do spring security para fazer gerenciamento de autenticação
    private AuthenticationManager manager;

    @Autowired //injeção de dependencia | Classe criada para criar e validar tokens do nosso sistema
    private TokenService tokenService;

    @PostMapping //Mapeia o método para as requisições POST dessa restController
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoRecord dados) { //requestBody sinaliza que o parâmetro virá do corpo da requisição
                                                                                      //valid (JPA) sinaliza que o parâmetro vai passar por validação declarada na sua DTO
        try {
            //cria um objeto padrão da classe DTO do spring security para login e senha
            var autenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
            //cria uma autenticação a partir do objeto DTO padrão do spring security para login e senha
            var autentication = manager.authenticate(autenticationToken);
            //gera um token a partir da autenticação criada
            var token = tokenService.gerarToken((Usuario) autentication.getPrincipal());
            //retorna código 200 e seu token
            return ResponseEntity.ok(new DadosTokenJWTRecord(token));
        } catch(Exception e) {
            e.printStackTrace();
            //caso a autenticaçã falhe, retorna código 400 bad request
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
