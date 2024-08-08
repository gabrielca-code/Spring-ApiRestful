package med.voli.api.domain.consulta;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voli.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voli.api.domain.medico.Medico;
import med.voli.api.domain.medico.MedicoRepository;
import med.voli.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validacoes;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validacoesCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if(!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("ID do paciente informado não existe!");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("ID do médico informado não existe!");
        }

        validacoes.forEach(v -> v.validar(dados));

        var medico =  escolherMedico(dados);

        if(medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data");
        }

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é necessária quando o médico não é escolhido");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.id())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validacoesCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.id());
        consulta.cancelar(dados.motivo());
    }


}