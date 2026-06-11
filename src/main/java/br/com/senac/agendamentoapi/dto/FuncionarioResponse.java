package br.com.senac.agendamentoapi.dto;

public record FuncionarioResponse(
        Integer id,
        String nome,
        String email,
        String telefone,
        Integer status
) {
}
