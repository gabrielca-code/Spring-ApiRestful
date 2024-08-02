package med.voli.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voli.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

    // Trecho de código suprimido
    public record DadosAgendamentoConsulta(
            Long idMedico,

            @NotNull
            Long idPaciente,

            @NotNull
            @Future
            LocalDateTime data,

            Especialidade especialidade) {
    }
