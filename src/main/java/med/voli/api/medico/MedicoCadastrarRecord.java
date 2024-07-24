package med.voli.api.medico;

import med.voli.api.endereco.EnderecoRecordCadastrarMedico;

public record MedicoCadastrarRecord(String nome, String email, String crm, Especialidade especialidade, EnderecoRecordCadastrarMedico endereco) {
}
