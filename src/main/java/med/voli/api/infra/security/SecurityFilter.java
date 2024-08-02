package med.voli.api.infra.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voli.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter { //herda da classe que define que o filtro sera executado OncePerRequest

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { //metodo padrao sobrescrito
        System.out.println("Chamando filtro");
        var tokenJWT = recuperarToken(request); //pegando o token pela requisição

        if(tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT); //retorna quem é o dono do token
            var usuario = usuarioRepository.findByLogin(subject); //busca a referencia do dono do token no BD
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); //cria a autenticação via classe padrão de autenticação UsernamePasswordETC
            SecurityContextHolder.getContext().setAuthentication(authentication); //mantem a autenticação dado o contexto de autencação do usuario
            System.out.println("Logado");
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var autorizationHeader = request.getHeader("Authorization"); //pega do cabeçalho da requisição o "Authorization"
        if(autorizationHeader != null)
            return autorizationHeader.replace("Bearer ", "");

        return null;
    }
}