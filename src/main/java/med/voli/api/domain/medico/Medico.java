package med.voli.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voli.api.domain.endereco.Endereco;

@Entity(name = "Medico") //define que a classe é uma entidade a ser mapeada pela JPA e possui o nome descrito
@Table(name = "medicos") //define qual tabela a entidade irá mapear no BD
@Getter //(lombok) cria os getters da classe
@NoArgsConstructor //(lombok) cria constrututor vazio
@AllArgsConstructor //(lombok) cria construtor com todos argumentos
@EqualsAndHashCode(of = "id") //(lombok) cria equal e hascode, nesse caso, baseado no id
public class Medico {

    @Id //define que o atributo é o identificador unico
    @GeneratedValue(strategy = GenerationType.IDENTITY) //define que o atributo seja auto incrementado e de qual maneira isso ocorrerá
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING) //Define que o atributo é um Enum
    private Especialidade especialidade;
    @Embedded //define que dentro dessa entidade terá tbm os dados dessa classe embbedada juntamente com os atributos da classe atual
    private Endereco endereco;
    private Boolean ativo;

    public Medico(MedicoCadastrarRecord dadosMedico) { //converter DTO em objeto médico
        this.nome = dadosMedico.nome();
        this.email = dadosMedico.email();
        this.telefone = dadosMedico.telefone();
        this.crm = dadosMedico.crm();
        this.especialidade = dadosMedico.especialidade();
        this.endereco = new Endereco(dadosMedico.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(MedicoEditarRecord dadosMedico) { //atualização de cadastro dado o que foi passado no corpo da requisição
        if(dadosMedico.nome() != null)
            this.nome = dadosMedico.nome();
        if(dadosMedico.telefone() != null)
            this.telefone = dadosMedico.telefone();
        if(dadosMedico.endereco() != null)
            this.endereco.atualizarInformacoes(dadosMedico.endereco());
    }

    public void desativarMedico() {
        this.ativo = false;
    } //exclusão lógica
}
