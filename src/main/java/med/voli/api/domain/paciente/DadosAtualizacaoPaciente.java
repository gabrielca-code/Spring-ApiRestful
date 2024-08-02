package med.voli.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voli.api.domain.endereco.EnderecoRecordCadastrarMedico;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRecordCadastrarMedico endereco) {
}
