package med.voli.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voli.api.endereco.EnderecoRecordCadastrarMedico;

public record MedicoEditarRecord(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRecordCadastrarMedico endereco
) {

}