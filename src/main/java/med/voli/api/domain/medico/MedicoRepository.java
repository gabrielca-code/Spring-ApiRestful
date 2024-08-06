package med.voli.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> { //define uma classe da camada de repository, que fará as chamadas com o BD
                                                                        //para isso, necessário extender JpaRepository (que ja possui a annotation @Repository, por isso n se faz necessária
                                                                        //Passando Classe e o tipo do ID da classe

    Page<Medico> findAllByAtivoTrue(Pageable paginacao); // método criado para retornar apenas os usuário ativos
                                                         // criados no padrão de nomes que a JPA mapeia e cria a query sozinha

    @Query("""
        select m from Medico m 
        where
        m.ativo = true
        and
        m.especialidade = :especialidade
        and
        m.id not in(
            select c.medico.id from Consulta c
            where
            c.data = :data
        )
        order by rand()
        limit 1
    """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
        select m.ativo
        from Medico m
        where
        m.id = :id
    """)
    boolean findAtivoById(Long id);
}