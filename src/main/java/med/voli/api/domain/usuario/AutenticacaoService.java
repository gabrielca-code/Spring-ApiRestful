package med.voli.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //define a classe a ser mapeada como um serviço do spring
public class AutenticacaoService implements UserDetailsService { //herda da classe padrão do spring para serviço de autenticacao

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override //retorna o usuario pela busca no BD pelo login
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }
}