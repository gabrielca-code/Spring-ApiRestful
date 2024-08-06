package med.voli.api.domain.consulta.validacoes;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.ConsultaRepository;
import med.voli.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorPacienteSemOutraConsultaNoDIa {

    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var primerioHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteAndDataBetween(dados.idPaciente(), primerioHorario, ultimoHorario);
        if(pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("O paciente já possui consulta agendada para esse dia");
        }
    }

}
