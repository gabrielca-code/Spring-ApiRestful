package med.voli.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteIdAndDataBetween(Long id, LocalDateTime primerioHorario, LocalDateTime ultimoHorario);
    boolean existsByMedicoIdAndDataAndMotivoIsNull(Long idMedico, LocalDateTime data);

}