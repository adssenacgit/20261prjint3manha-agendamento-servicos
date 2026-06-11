package br.com.senac.agendamentoapi.dto;

import br.com.senac.agendamentoapi.model.AgendamentoSituacao;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoRequest(
        @NotNull LocalDate data,
        @NotNull LocalTime horarioInicio,
        @NotNull LocalTime horarioFim,
        String observacoes,
        AgendamentoSituacao situacao,
        @NotNull Integer servicoId,
        @NotNull Integer horarioDisponivelId,
        @NotNull Integer clienteId,
        Integer status
) {
}
