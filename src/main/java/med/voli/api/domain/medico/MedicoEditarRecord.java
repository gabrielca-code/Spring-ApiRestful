package med.voli.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voli.api.domain.endereco.EnderecoRecordCadastrarMedico;

public record MedicoEditarRecord(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRecordCadastrarMedico endereco
) {

}