package med.voli.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voli.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController //define a classe como uma controller de uma API Rest
@RequestMapping("/medicos") //define qual o mapeamento de requisição para essa controller
@EnableMethodSecurity(securedEnabled = true) //configura a classe para ter segurança nos métodos dado as ROLES settadas nos métodos anotados
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired //injeção de dependencia
    private MedicoRepository medicoRepository;

    @PostMapping //mapeia o método para as requisições POST para essa Controller
    @Transactional // (JPA) Define uma transação que é commitada ao final caso bem sucedida ou rollback em caso de exceção
    public ResponseEntity cadastrar(@RequestBody @Valid MedicoCadastrarRecord dadosMedico, UriComponentsBuilder uriBuilder) {
                                    //RequestBody define que o parametro virá do corpo da requisição
                                    //Valid fará com que o DTO seja validado conforme as configurações do record
                                    //UriComponentsBuilder retorna a uri raiz do projeto
        var medico = new Medico(dadosMedico); //cria o novo objeto médico a partir do DTO
        medicoRepository.save(medico); //salva o médico no DB
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico).toUri(); //setta a Uri para que o ID seja o ID do médico

        return ResponseEntity.created(uri).body(new MedicoDetalhamentoRecord(medico)); //retorna código 201, qual a URI do novo medico cadastrado e o json dos dados do médico
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoRecord>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
                                    //Page: serve para devolver a requisição de listagem paginada, limitando a quantidade de itens retornados por page
                                    //PageableDefault setta o padrão da paginação devolvida, como size (quantidade de itens por page), page (pagina padrão a ser exibido primeiro), sort (ordenação)
        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoRecord::new);
                                    //cria a pagina a ser exibida dada a consulta feita pelos padrão de busca JPA, mapeia todos itens retornados da pesquisa e por meio de lambda ele altera item a item para o DTO

        return ResponseEntity.ok(page); //retorna código 200 e a página a ser exibida
    }

    @PutMapping
    @Transactional // (JPA) Define uma transação que é commitada ao final caso bem sucedida ou rollback em caso de exceção
    public ResponseEntity atualizar(@RequestBody @Valid MedicoEditarRecord dadosMedico) {
                                    //requestBody indica que o parametro será passado pelo corpo da requisicao
                                    //valid faz com que os dados passem pela validação configurada no DTO
        var medico = medicoRepository.getReferenceById(dadosMedico.id()); //pega o medico a ser atualizado pelo id
        medico.atualizarInformacoes(dadosMedico); //atualiza as informações do médico

        return ResponseEntity.ok(new MedicoDetalhamentoRecord(medico)); //retorna código 200 e o json dos dados do médico agora atualizado
    }

    @DeleteMapping("/{id}") //mapeia o método para as requisições DEL do qual tenha um ID sendo passado juntamente para essa Controller
    @Transactional // (JPA) Define uma transação que é commitada ao final caso bem sucedida ou rollback em caso de exceção
    @Secured("ROLE_ADMIN") //define que a função pode ser executada apenas por usuários na ROLE ADMIN
    public ResponseEntity excluir(@PathVariable Long id) { //PathVariable mapeia que a variável virá na URL da requisição
        //medicoRepository.deleteById(id); //exclusão física
        var medico = medicoRepository.getReferenceById(id); //pegando qual médico será excluido
        medico.desativarMedico(); //mudando o status dele para inativo, exclusão lógica

        return ResponseEntity.noContent().build(); //retornando código 204
    }

    @GetMapping("/{id}") //mapeia o método para as requisições GET do qual tenha um ID sendo passado juntamente
    public ResponseEntity detalhar(@PathVariable Long id) { //PathVariable mapeia que a variável virá na URL da requisição
        var medico = medicoRepository.getReferenceById(id); //pegando qual medico será detalhado pelo ID

        return ResponseEntity.ok(new MedicoDetalhamentoRecord(medico)); //retornando código 200 e o DTO do médico a ser detalhado
    }
}