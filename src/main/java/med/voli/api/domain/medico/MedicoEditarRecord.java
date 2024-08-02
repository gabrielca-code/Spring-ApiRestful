package med.voli.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voli.api.domain.endereco.EnderecoRecordCadastrarMedico;

public record MedicoEditarRecord(
        @NotNull //define que o campo não pode ser nulo
        Long id,
        String nome,
        String telefone,
        EnderecoRecordCadastrarMedico endereco
) {

}