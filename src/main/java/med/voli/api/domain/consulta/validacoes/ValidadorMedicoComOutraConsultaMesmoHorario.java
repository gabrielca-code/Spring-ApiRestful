package med.voli.api.domain.consulta.validacoes;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.ConsultaRepository;
import med.voli.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorMedicoComOutraConsultaMesmoHorario {

    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoOcupadoNoHorario = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (medicoOcupadoNoHorario) {
            throw new ValidacaoException("Médico já possui uma consulta nesse horário!");
        }
    }

}
