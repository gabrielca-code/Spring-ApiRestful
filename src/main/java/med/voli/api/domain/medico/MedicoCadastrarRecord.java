package med.voli.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voli.api.domain.endereco.EnderecoRecordCadastrarMedico;

public record MedicoCadastrarRecord(
        @NotBlank //define que o campo n pode ser nulo nem vazio
        String nome,
        @NotBlank //define que o campo n pode ser nulo nem vazio
        @Email //define que o texto vai ter que ter o padrão de um email
        String email,
        @NotBlank //define que o campo n pode ser nulo nem vazio
        String telefone,
        @NotBlank //define que o campo n pode ser nulo nem vazio
        @Pattern(regexp = "\\d{4,6}") //define que o campo seguir a regra do regex (no caso, de 4 a 6 carecteres)
        String crm,
        @NotNull //define que o campo não pode ser nulo
        Especialidade especialidade,
        @NotNull //define que o campo não pode ser nulo
        @Valid //define que o atributo tbm deve ser validado na classe dele
        EnderecoRecordCadastrarMedico endereco
) {

}
