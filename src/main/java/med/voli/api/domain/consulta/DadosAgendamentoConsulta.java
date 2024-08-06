package med.voli.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
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
            @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
            LocalDateTime data,

            Especialidade especialidade) {
    }
