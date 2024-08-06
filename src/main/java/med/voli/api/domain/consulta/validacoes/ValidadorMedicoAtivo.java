package med.voli.api.domain.consulta.validacoes;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.DadosAgendamentoConsulta;
import med.voli.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorMedicoAtivo {

    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído!");
        }
    }

}
