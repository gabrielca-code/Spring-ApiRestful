package med.voli.api.domain.consulta.validacoes;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.DadosAgendamentoConsulta;
import med.voli.api.domain.medico.MedicoRepository;
import med.voli.api.domain.paciente.PacienteRepository;

public class ValidadorPacienteAtivo {

    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído!");
        }
    }
}
