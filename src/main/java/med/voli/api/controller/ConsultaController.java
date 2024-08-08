package med.voli.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voli.api.domain.consulta.AgendaDeConsultas;
import med.voli.api.domain.consulta.DadosAgendamentoConsulta;
import med.voli.api.domain.consulta.DadosCancelamentoConsulta;
import med.voli.api.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var consultaDTO = agenda.agendar(dados);
        return ResponseEntity.ok(consultaDTO);
    }

    @PostMapping("/cancelar")
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.ok(dados);
    }

}