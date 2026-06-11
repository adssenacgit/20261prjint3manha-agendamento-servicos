package br.com.senac.agendamentoapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FuncionarioRequest(
        @NotBlank @Size(max = 50) String nome,
        @NotBlank @Email @Size(max = 50) String email,
        @NotBlank @Size(min = 6, max = 100) String senha,
        @NotBlank @Size(max = 20) String telefone,
        Integer status
) {
}
