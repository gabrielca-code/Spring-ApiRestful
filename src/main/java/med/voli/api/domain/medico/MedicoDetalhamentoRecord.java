package med.voli.api.domain.medico;

import med.voli.api.domain.endereco.Endereco;

public record MedicoDetalhamentoRecord(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public MedicoDetalhamentoRecord(Medico medico) { //construtor para converter um objeto médico nesse DTO
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }

}