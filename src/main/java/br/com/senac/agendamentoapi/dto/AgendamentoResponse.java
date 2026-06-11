package br.com.senac.agendamentoapi.dto;

import br.com.senac.agendamentoapi.model.AgendamentoSituacao;
import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoResponse(
        Integer id,
        LocalDate data,
        LocalTime horarioInicio,
        LocalTime horarioFim,
        String observacoes,
        AgendamentoSituacao situacao,
        Integer status,
        Integer servicoId,
        String servicoNome,
        Integer horarioDisponivelId,
        Integer clienteId,
        String clienteNome
) {
}
