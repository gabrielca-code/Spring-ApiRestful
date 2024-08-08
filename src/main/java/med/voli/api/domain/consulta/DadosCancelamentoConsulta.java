package med.voli.api.domain.consulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(@NotNull Long id, @NotBlank String motivo) {}
