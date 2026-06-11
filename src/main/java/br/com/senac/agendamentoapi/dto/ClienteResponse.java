package br.com.senac.agendamentoapi.dto;

import java.time.LocalDate;

public record ClienteResponse(
        Integer id,
        String nome,
        String telefone,
        String email,
        LocalDate dataCadastro,
        Integer status
) {
}
