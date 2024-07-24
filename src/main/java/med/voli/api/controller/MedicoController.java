package med.voli.api.controller;

import jakarta.validation.Valid;
import med.voli.api.medico.DadosListagemMedico;
import med.voli.api.medico.Medico;
import med.voli.api.medico.MedicoCadastrarRecord;
import med.voli.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoCadastrarRecord dadosMedico) {
        medicoRepository.save(new Medico(dadosMedico));
    }

    @GetMapping
    public List<DadosListagemMedico> listar() {
        return medicoRepository.findAll().stream().map(DadosListagemMedico::new).toList();
    }
}
