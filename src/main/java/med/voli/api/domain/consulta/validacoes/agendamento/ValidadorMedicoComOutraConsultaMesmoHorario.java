package med.voli.api.domain.consulta.validacoes.agendamento;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.ConsultaRepository;
import med.voli.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoOcupadoNoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoIsNull(dados.idMedico(), dados.data());
        if (medicoOcupadoNoHorario) {
            throw new ValidacaoException("Médico já possui uma consulta nesse horário!");
        }
    }

}
