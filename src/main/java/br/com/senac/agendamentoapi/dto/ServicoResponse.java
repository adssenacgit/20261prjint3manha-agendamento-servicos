package br.com.senac.agendamentoapi.dto;

import java.math.BigDecimal;

public record ServicoResponse(
        Integer id,
        String nome,
        String descricao,
        BigDecimal valor,
        Integer duracaoMinutos,
        Integer status,
        Integer funcionarioId,
        String funcionarioNome
) {
}
