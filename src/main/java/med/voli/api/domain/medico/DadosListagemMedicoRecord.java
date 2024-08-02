package med.voli.api.domain.medico;

public record DadosListagemMedicoRecord(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {

    public DadosListagemMedicoRecord(Medico medico) { //Construtor para converter um médico nessa classe
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
