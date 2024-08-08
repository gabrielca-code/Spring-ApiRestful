package med.voli.api.domain.consulta.validacoes.agendamento;

import med.voli.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
