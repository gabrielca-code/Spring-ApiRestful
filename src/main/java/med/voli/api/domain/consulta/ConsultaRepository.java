package med.voli.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndData(Long aLong, LocalDateTime data);

    boolean existsByPacienteAndDataBetween(Long id, LocalDateTime primerioHorario, LocalDateTime ultimoHorario);
}