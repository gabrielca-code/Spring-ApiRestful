package med.voli.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //mapeia pelo spring como uma classe de configuração
@EnableWebSecurity //permite a classe realizar ações sobre web security
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter; //classe criada para validar o token nos requests

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //cria um canal de filtro de segurança
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //muda a forma que a sessão é mantida, no caso stateless
                .authorizeHttpRequests(req -> { //define quais regras para requisições
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll(); //define que para o POST em /login seja permito requisições de todos
                    req.anyRequest().authenticated(); //define que para qualquer outro, seja necessario autenticar
                }).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //define que o filtro executado posteriormente seja do SecurityFilter passando um objeto UsernamePasswordETC
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception { //retorna um gerenciador de autenticação
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //retorna um encoder para encriptar a senha
        return new BCryptPasswordEncoder();
    }

}