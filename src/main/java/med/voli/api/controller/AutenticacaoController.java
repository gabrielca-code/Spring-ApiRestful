package med.voli.api.controller;

import jakarta.validation.Valid;
import med.voli.api.domain.usuario.DadosAutenticacao;
import med.voli.api.domain.usuario.Usuario;
import med.voli.api.infra.security.DadosTokenJWT;
import med.voli.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var autenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var autentication = manager.authenticate(autenticationToken);

        var token = tokenService.gerarToken((Usuario) autentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(token));
    }

}
