package med.voli.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoRecordCadastrarMedico(
        @NotBlank //define que o campo n pode ser nulo nem vazio
        String logradouro,
        @NotBlank //define que o campo n pode ser nulo nem vazio
        String bairro,
        @NotBlank //define que o campo n pode ser nulo nem vazio
        @Pattern(regexp = "\\d{8}") //define que o campo deve seguir o regex definido (no caso, 8 caracteres
        String cep,
        @NotBlank //define que o campo n pode ser nulo nem vazio
        String cidade,
        @NotBlank //define que o campo n pode ser nulo nem vazio
        String uf,
        String complemento,
        String numero
) {

}
