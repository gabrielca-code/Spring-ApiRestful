package med.voli.api.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario") //define que a classe é uma entidade a ser mapeada pela JPA e possui o nome descrito
@Table(name = "usuarios") //define qual tabela a entidade irá mapear no BD
@Getter //(lombok) cria os getters da classe
@NoArgsConstructor //(lombok) cria constrututor vazio
@AllArgsConstructor //(lombok) cria construtor com todos argumentos
@EqualsAndHashCode(of = "id") //(lombok) cria equal e hascode, nesse caso, baseado no id
public class Usuario implements UserDetails { //herdando de UserDetails para tornar a classe como classe com os dados de autenticacao dos usuários

    @Id //define que o atributo é o identificador unico
    @GeneratedValue(strategy = GenerationType.IDENTITY) //define que o atributo seja auto incrementado e de qual maneira isso ocorrerá
    private Long id;
    private String login;
    private String senha;

    @Override //De UserDetails, lista todas roles do usuário
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override //De UserDetails, retorna a senha do usuario
    public String getPassword() {
        return senha;
    }

    @Override //De UserDetails, retorna o login do usuario
    public String getUsername() {
        return login;
    }

    @Override //De UserDetails, retorna se a conta não está expirada
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //De UserDetails, retorna se a conta não está bloqueada
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override //De UserDetails, retorna se a conta não está com a credencial expirada
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override //De UserDetails, retorna se a conta está ativa
    public boolean isEnabled() {
        return true;
    }
}
