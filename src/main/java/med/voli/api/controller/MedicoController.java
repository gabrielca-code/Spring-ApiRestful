package med.voli.api.controller;

import med.voli.api.medico.MedicoCadastrarRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void cadastrar(@RequestBody MedicoCadastrarRecord dadosMedico) {
     System.out.println(dadosMedico);
    }
}
