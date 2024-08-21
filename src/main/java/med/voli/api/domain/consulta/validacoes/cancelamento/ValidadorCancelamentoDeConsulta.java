package med.voli.api.domain.consulta.validacoes.cancelamento;

import med.voli.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);

}