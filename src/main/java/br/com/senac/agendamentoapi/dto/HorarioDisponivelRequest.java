package br.com.senac.agendamentoapi.dto;

import br.com.senac.agendamentoapi.model.HorarioDisponivelSituacao;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record HorarioDisponivelRequest(
        @NotNull LocalDate data,
        @NotNull LocalTime inicio,
        @NotNull LocalTime fim,
        HorarioDisponivelSituacao situacao,
        @NotNull Integer funcionarioId,
        Integer status
) {
}
