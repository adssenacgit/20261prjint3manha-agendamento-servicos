package br.com.senac.agendamentoapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record ClienteRequest(
        @NotBlank @Size(max = 50) String nome,
        @NotBlank @Size(max = 20) String telefone,
        @NotBlank @Email @Size(max = 50) String email,
        @NotNull LocalDate dataCadastro,
        Integer status
) {
}
