package med.voli.api.domain.usuario;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> { //define uma classe da camada de repository, que fará as chamadas com o BD
                                                                            //para isso, necessário extender JpaRepository (que ja possui a annotation @Repository, por isso n se faz necessária
                                                                            //Passando Classe e o tipo do ID da classe

    UserDetails findByLogin(String login); //metodo criado no padrão JPA para criar a query sozinha
                                           //retornando a classe UserDetails, classe padrão pra detalhamento de usuário na autenticacao
}