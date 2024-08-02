package med.voli.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voli.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service //define a classe a ser mapeada na camada de serviço do spring
public class TokenService {

    @Value("${api.security.token.secret}") //define que o valor será pego em determinado local, no caso, no application.properties dado o caminha passado
                                           // (no caso tbm, será uma variável de ambiente)
    private String secret;

    private static final String ISSUER = "API Voll.med";

    public String gerarToken(Usuario usuario) { //Gera um token a ser utilizado para autenticar o usuário nas requisições
        try {
            var algoritmo = Algorithm.HMAC256(secret); //define o algoritmo para criar o token
            return JWT.create() //cria um JWT
                    .withIssuer(ISSUER) //define o emissor do JWT
                    .withSubject(usuario.getLogin()) //define o dono do JWT
                    .withExpiresAt(dataExpiracao()) //define um prazo de expiração do JWT
                    .sign(algoritmo); //Define qual algoritmo criação o JWT seguirá
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        //String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
        //DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); //define qual algoritmo foi usado pra criar o token
            return JWT.require(algorithm) //verifica um JWT dado um algoritmo
                    .withIssuer(ISSUER) //passa o emissor do JWT para validar juntamente
                    .build() //builda o verificador
                    .verify(tokenJWT) //passa qual token será validado
                    .getSubject(); //pega qual o dono do token
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() { //retorna quanto tempo o token irá demorar para expirar
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}