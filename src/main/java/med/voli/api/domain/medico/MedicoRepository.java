package med.voli.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> { //define uma classe da camada de repository, que fará as chamadas com o BD
                                                                        //para isso, necessário extender JpaRepository (que ja possui a annotation @Repository, por isso n se faz necessária
                                                                        //Passando Classe e o tipo do ID da classe

    Page<Medico> findAllByAtivoTrue(Pageable paginacao); //método criado para retornar apenas os usuário ativos
                                                        // criados no padrão de nomes que a JPA mapeia e cria a query sozinha
}